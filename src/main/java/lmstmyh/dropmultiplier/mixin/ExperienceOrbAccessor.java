package lmstmyh.dropmultiplier.mixin;

import net.minecraft.world.entity.ExperienceOrb;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ExperienceOrb.class)
public interface ExperienceOrbAccessor {
    @Accessor("value")
    void dropmultiplier$setValue(int value);

    @Accessor("value")
    int dropmultiplier$getValue();
}
