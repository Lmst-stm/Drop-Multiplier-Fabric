package lmstmyh.dropmultiplier;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class CommandHandler {

    public static LiteralArgumentBuilder<CommandSourceStack> build() {
        return Commands.literal("dropmultiplier")
                .then(Commands.literal("info").executes(ctx -> info(ctx.getSource())))
                .then(Commands.literal("enable").executes(ctx -> setEnabled(ctx.getSource(), true)))
                .then(Commands.literal("disable").executes(ctx -> setEnabled(ctx.getSource(), false)))
                .then(Commands.literal("toggle").executes(ctx -> setEnabled(ctx.getSource(), !DropMultiplier.enabled)))
                .then(Commands.literal("reset").executes(ctx -> set(ctx.getSource(), 2.0D)))
                .then(Commands.argument("multiplier", DoubleArgumentType.doubleArg(0.01))
                        .executes(ctx -> set(ctx.getSource(), DoubleArgumentType.getDouble(ctx, "multiplier"))));
    }

    private static int set(CommandSourceStack source, double value) {
        DropMultiplier.multiplier = value;
        source.sendSuccess(Component.literal("Drop multiplier set to: " + value), true);
        return 1;
    }

    private static int setEnabled(CommandSourceStack source, boolean value) {
        DropMultiplier.enabled = value;
        source.sendSuccess(Component.literal("Drop multiplier " + (value ? "enabled" : "disabled")), true);
        return 1;
    }

    private static int info(CommandSourceStack source) {
        source.sendSuccess(Component.literal("=== Drop Multiplier Info ==="), false);
        source.sendSuccess(Component.literal("Current multiplier: " + DropMultiplier.multiplier), false);
        source.sendSuccess(Component.literal("Enabled: " + DropMultiplier.enabled), false);
        return 1;
    }
}
