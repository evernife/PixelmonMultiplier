package br.com.finalcraft.pixelmonmultiplier.listeners;

import br.com.finalcraft.evernifecorespongy.fancytext.FancyText;
import br.com.finalcraft.pixelmonmultiplier.MultiplierUtil;
import br.com.finalcraft.pixelmonmultiplier.PixelmonMultiplier;
import br.com.finalcraft.pixelmonmultiplier.config.ConfigManager;
import br.com.finalcraft.pixelmonmultiplier.config.playerdata.PMPlayerData;
import com.pixelmonmod.pixelmon.api.events.ExperienceGainEvent;
import com.pixelmonmod.pixelmon.config.PixelmonConfig;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.spongepowered.api.entity.living.player.Player;

public class PixelmonForgeListener {

    @SubscribeEvent
    public void onExperienceGain(ExperienceGainEvent event) {
        try {
            if (event.pokemon.isEgg() || event.pokemon.getLevel() == PixelmonConfig.maxLevel) return;

            Player player = (Player) event.pokemon.getPlayerOwner();

            int oldExp = event.getExperience();
            PMPlayerData pmPlayerData = PMPlayerData.getOrCreate(player.getName());
            double multiplier = ConfigManager.getGlobalExpMultiplier() + pmPlayerData.getPersonalMultiplier() + MultiplierUtil.getByVipPermission(player) + MultiplierUtil.getByRankPermission(player);
            int newExp = (int)(oldExp + oldExp * multiplier);

            //Não possui nenhum multiplicador
            if (newExp == oldExp){
                return;
            }
            event.setExperience(newExp);
            if (PixelmonMultiplier.debugMode) {
                broadcastTheEvent(event,player,pmPlayerData,oldExp,newExp);
            }
        } catch (Exception e) {
            PixelmonMultiplier.getPluginLogger().error("PixelmonMultiplier has thrown an exception!", e);
        }
    }

    public static void broadcastTheEvent(ExperienceGainEvent event, Player player, PMPlayerData pmPlayerData, int oldExp, int newExp){
        int personalMultiplier = (int) (pmPlayerData.getPersonalMultiplier() * 100);
        int globalMultiplier = (int) (ConfigManager.getGlobalExpMultiplier() * 100);
        int vipMultiplier = (int) (MultiplierUtil.getByRankPermission(player) * 100);
        int rankMultiplier = (int) (MultiplierUtil.getByVipPermission(player) * 100);

        int result = personalMultiplier + globalMultiplier + vipMultiplier + rankMultiplier;

        String lore =
                "§b ◈ §d§n" + player.getName() + " Multipliers Info\n" +
                        "§2 ◈ §a + Multiplicador Pessoal: §e" + personalMultiplier + "%\n" +
                        "§2 ◈ §a + Multiplicador Global: §e" + globalMultiplier + "%\n" +
                        "§2 ◈ §a + Multiplicador por Vip: §e" + vipMultiplier + "%\n" +
                        "§2 ◈ §a + Multiplicador por Rank: §e" + rankMultiplier + "%\n" +
                        "\n" +
                        "§2 ◈ §aMultiplicador Final: §e§l" + result + "§6§l%\n" +
                        "\n" +
                        "\n§6 ◈ OLD EXP : §e" + oldExp + "\n" +
                        "\n§6 ◈ NEW EXP : §e" + newExp + "\n";

        FancyText.tellRawBroadcast(new FancyText("§3[FinalCraftDebug]§3§l ▶ §eO pokemon do jogador " + player.getName() + ", ☾" + event.pokemon.getNickname() + "☽ recebeu " + newExp + " EXP").setHoverText(lore));
    }
}
