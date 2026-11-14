package lmstmyh.dropmultiplier.mixin;

import java.util.ArrayList;
import java.util.List;
import lmstmyh.dropmultiplier.DropMultiplier;
import net.minecraft.item.ItemStack;
import net.minecraft.world.loot.LootSupplier;
import net.minecraft.world.loot.context.LootContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LootSupplier.class)
public class LootTableMixin {

    @Inject(method = "getDrops(Lnet/minecraft/world/loot/context/LootContext;)Ljava/util/List;",
            at = @At("RETURN"), cancellable = true)
    private void dropmultiplier$multiply(LootContext context, CallbackInfoReturnable<List<ItemStack>> cir) {
        if (!DropMultiplier.enabled || DropMultiplier.multiplier <= 0.0D || DropMultiplier.multiplier == 1.0D) {
            return;
        }
        List<ItemStack> original = cir.getReturnValue();
        if (original == null || original.isEmpty()) {
            return;
        }
        List<ItemStack> scaled = new ArrayList<>();
        for (ItemStack stack : original) {
            int count = stack.getCount();
            int target = (int) Math.max(count, Math.round(count * DropMultiplier.multiplier));
            int remaining = target;
            while (remaining > 0) {
                int batch = Math.min(remaining, stack.getMaxCount());
                ItemStack copy = stack.copy();
                copy.setCount(batch);
                scaled.add(copy);
                remaining -= batch;
            }
        }
        cir.setReturnValue(scaled);
    }
}
