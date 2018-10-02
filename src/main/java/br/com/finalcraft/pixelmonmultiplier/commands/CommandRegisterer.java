package br.com.finalcraft.pixelmonmultiplier.commands;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.Text;


public class CommandRegisterer {

    public static void registerCommands(PluginContainer pluginInstance) {

        CommandSpec commandPixelmonMultiplier = CommandSpec.builder()
                .description(Text.of("Pixelmon Multiplier Main Command"))
                .arguments(GenericArguments.none(),
                        GenericArguments.optional(GenericArguments.remainingRawJoinedStrings(Text.of("allArgs"))))
                .executor(new CoreCommand())
                .build();

        Sponge.getCommandManager().register(pluginInstance, commandPixelmonMultiplier, "pmultiplier","pixelmonmultiplier","pixelm","pmm");
    }

}
