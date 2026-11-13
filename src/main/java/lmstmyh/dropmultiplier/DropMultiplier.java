package lmstmyh.dropmultiplier;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

public class DropMultiplier implements ModInitializer {
    public static final String MODID = "dropmultiplier";

    public static double multiplier = 2.0D;
    public static boolean enabled = true;

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) ->
                dispatcher.register(CommandHandler.build()));
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
