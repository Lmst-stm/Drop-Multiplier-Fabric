package lmstmyh.dropmultiplier;

import net.fabricmc.api.ModInitializer;

public class DropMultiplier implements ModInitializer {
    public static final String MODID = "dropmultiplier";

    public static double multiplier = 2.0D;
    public static boolean enabled = true;

    @Override
    public void onInitialize() {
        // 1.14 / 1.14.1 / 1.14.2 / 1.14.3: Fabric API in this era has no command API,
        // so the mod only provides the loot multiplier here.
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
