package br.com.finalcraft.pixelmonmultiplier.listeners;

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
            double multiplier = ConfigManager.getGlobalExpMultiplier() + pmPlayerData.getPersonalMultiplier() + MultiplierUtil.getPermissionMultiplier(player);
            double newExp = oldExp + oldExp * multiplier;

            //Não possui nenhum multiplicador
            if (newExp == oldExp){
                return;
            }
            event.setExperience((int) newExp);
        } catch (Exception e) {
            PixelmonMultiplier.getPluginLogger().error("PixelmonMultiplier has thrown an exception!", e);
        }
    }
}
