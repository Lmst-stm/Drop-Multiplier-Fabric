package lmstmyh.dropmultiplier.mixin;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lmstmyh.dropmultiplier.DropMultiplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LootTable.class)
public class LootTableMixin {

    @Inject(method = "getRandomItems(Lnet/minecraft/world/level/storage/loot/LootContext;)Lit/unimi/dsi/fastutil/objects/ObjectArrayList;",
            at = @At("RETURN"), cancellable = true)
    private void dropmultiplier$multiply(LootContext context, CallbackInfoReturnable<ObjectArrayList<ItemStack>> cir) {
        if (!DropMultiplier.enabled || DropMultiplier.multiplier <= 0.0D || DropMultiplier.multiplier == 1.0D) {
            return;
        }
        ObjectArrayList<ItemStack> original = cir.getReturnValue();
        if (original == null || original.isEmpty()) {
            return;
        }
        ObjectArrayList<ItemStack> scaled = new ObjectArrayList<>();
        for (ItemStack stack : original) {
            int count = stack.getCount();
            int target = (int) Math.max(count, Math.round(count * DropMultiplier.multiplier));
            int remaining = target;
            while (remaining > 0) {
                int batch = Math.min(remaining, stack.getMaxStackSize());
                ItemStack copy = stack.copy();
                copy.setCount(batch);
                scaled.add(copy);
                remaining -= batch;
            }
        }
        cir.setReturnValue(scaled);
    }
}
