package br.com.finalcraft.pixelmonmultiplier.commands;

import br.com.finalcraft.evernifecorespongy.FCSpongeUtil;
import br.com.finalcraft.evernifecorespongy.fancytext.FancyText;
import br.com.finalcraft.pixelmonmultiplier.MultiplierUtil;
import br.com.finalcraft.pixelmonmultiplier.PermissionNodes;
import br.com.finalcraft.pixelmonmultiplier.PixelmonMultiplier;
import br.com.finalcraft.pixelmonmultiplier.config.ConfigManager;
import br.com.finalcraft.pixelmonmultiplier.config.playerdata.PMPlayerData;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import java.util.ArrayList;
import java.util.List;

public class CoreCommand implements CommandExecutor {

    @Override
    public CommandResult execute(CommandSource sender, CommandContext args) throws CommandException {

        List<String> argumentos = FCSpongeUtil.parseSpongeArgsToList(args,4);

        switch (argumentos.get(0).toLowerCase()){
            case "":
            case "help":
            case "?":
                return help(sender,argumentos);
            case "reload":
                return reload(sender,argumentos);
            case "info":
                return info(sender,argumentos);
        }

        sender.sendMessage(Text.of("§cErro de parametros, por favor use /rankup help"));
        return CommandResult.success();
    }

    // -----------------------------------------------------------------------------------------------------------------------------//
    // Command Help
    // -----------------------------------------------------------------------------------------------------------------------------//
    public static CommandResult help(CommandSource sender, List<String> argumentos){
        sender.sendMessage(Text.of("§6§m------------------§6( §a▧▨▧▨ §e§lPMultiplier§e §a▧▨▧▨ §6)§m------------------"));
        if (sender instanceof Player){
            Player player = (Player) sender;
            FancyText.sendTo(player,       new FancyText("§3§l ▶ §a/pixelm info","§bMostra as informações do seu Multiplicador de EXP atual","/pixelm info"));
            if (sender.hasPermission(PermissionNodes.commandPMultiplierReload)){
                FancyText.sendTo(player,       new FancyText("§3§l ▶ §a/pixelm reload","§bRecarrega as configurações do plugin!","/pixelm reload ",true));
            }
            sender.sendMessage(Text.of(""));
            sender.sendMessage(Text.of("§3§oPasse o mouse em cima dos comandos para ver a descrição!"));
        }else {
            sender.sendMessage(Text.of("§3§l ▶ §a/pixelm reload"));
            sender.sendMessage(Text.of(""));
        }
        sender.sendMessage(Text.of("§6§m-----------------------------------------------------"));
        return CommandResult.success();
    }


    // -----------------------------------------------------------------------------------------------------------------------------//
    // Command Reload
    // -----------------------------------------------------------------------------------------------------------------------------//
    public static CommandResult reload(CommandSource sender, List<String> argumentos){

        if (FCSpongeUtil.isNotPlayer(sender)){
            return CommandResult.success();
        }

        if (!FCSpongeUtil.hasThePermission(sender, PermissionNodes.commandPMultiplierReload)){
            return CommandResult.success();
        }

        ConfigManager.initialize(PixelmonMultiplier.instance);
        sender.sendMessage(Text.of("§a§lPlugin recarregado com sucesso!"));
        return CommandResult.success();
    }


    // -----------------------------------------------------------------------------------------------------------------------------//
    // Command Info
    // -----------------------------------------------------------------------------------------------------------------------------//
    public static CommandResult info(CommandSource sender, List<String> argumentos){

        if (FCSpongeUtil.isNotPlayer(sender)){
            return CommandResult.success();
        }

        /*
        if (!FCSpongeUtil.hasThePermission(sender, PermissionNodes.commandPMultiplierInfo)){
            return CommandResult.success();
        }
        */

        Player player = (Player) sender;

        PMPlayerData pmPlayerData = PMPlayerData.getOrCreate(player.getName());
        double personalMultiplier = pmPlayerData.getPersonalMultiplier();
        double globalMultiplier = ConfigManager.getGlobalExpMultiplier();
        double permissionMultiplier = MultiplierUtil.getPermissionMultiplier(player);

        double result = personalMultiplier + globalMultiplier + permissionMultiplier;
        List<FancyText> fancyText = new ArrayList<FancyText>();

        String lore =
                "§b ◈ §d§n" + player.getName() + " Multipliers Info\n" +
                        "§2 ◈ §a + Multiplicador Pessoal: " + personalMultiplier + "%\n" +
                        "§2 ◈ §a + Multiplicador por Permissão: " + globalMultiplier + "%\n" +
                        "§2 ◈ §a + Multiplicador Global: " + permissionMultiplier + "%\n" +
                        "\n" +
                        "§2 ◈ §aMultiplicador Final: " + result + "%\n";

        fancyText.add(new FancyText("§a§lSeu MultiplicadorEXP possui o valor de: §e§l" + result + "§6§l%").setHoverText(lore));
        return CommandResult.success();
    }
}
