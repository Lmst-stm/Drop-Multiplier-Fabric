package lmstmyh.dropmultiplier;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import lmstmyh.dropmultiplier.mixin.ExperienceOrbAccessor;
import net.minecraft.world.entity.ExperienceOrb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DropMultiplier implements ModInitializer {
    public static final String MODID = "dropmultiplier";
    public static final Logger LOGGER = LoggerFactory.getLogger("DropMultiplier");

    public static double multiplier = 2.0D;
    public static boolean enabled = true;

    @Override
    public void onInitialize() {
        LOGGER.info("DropMultiplier Initializing...");
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
                dispatcher.register(CommandHandler.build()));
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof ExperienceOrb orb) {
                ExperienceOrbAccessor accessor = (ExperienceOrbAccessor) orb;
                int old = accessor.dropmultiplier$getValue();
                int scaled = scale(old);
                if (scaled != old) {
                    accessor.dropmultiplier$setValue(scaled);
                }
            }
        });
    }

    public static int scale(int value) {
        if (!enabled || multiplier <= 0.0D || multiplier == 1.0D || value <= 0) {
            return value;
        }
        double scaled = value * multiplier;
        if (scaled >= Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return Math.max(0, (int) Math.round(scaled));
    }
}
