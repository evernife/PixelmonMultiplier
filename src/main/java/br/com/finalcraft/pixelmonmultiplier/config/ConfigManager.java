package br.com.finalcraft.pixelmonmultiplier.config;

import br.com.finalcraft.evernifecorespongy.config.Config;
import org.spongepowered.api.plugin.PluginContainer;

public class ConfigManager {

    public static Config mainConfig;
    public static Config getMainConfig(){
        return mainConfig;
    }

    private static double globalExpMultiplier = 0D;
    public static double getGlobalExpMultiplier(){
        return globalExpMultiplier;
    }

    public static void initialize(PluginContainer instance){
        mainConfig  = new Config(instance,"config.yml"      ,false);

        globalExpMultiplier = mainConfig.getDouble("Global.ExpMultiplier",0D);
        everythingNeedsToBePositive();
    }

    //CheckPositive
    private static void everythingNeedsToBePositive(){
        globalExpMultiplier = globalExpMultiplier < 0 ? 0 : globalExpMultiplier;
    }

}
