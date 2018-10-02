package br.com.finalcraft.pixelmonmultiplier.config.playerdata;

import br.com.finalcraft.evernifecorespongy.config.playerdata.PDSection;
import br.com.finalcraft.evernifecorespongy.config.playerdata.PlayerController;
import br.com.finalcraft.evernifecorespongy.config.playerdata.PlayerData;

public class PMPlayerData implements PDSection {

    public static PMPlayerData getOrCreate(String playerName) {
        PlayerData playerData = PlayerController.getPlayerData(playerName);
        if (playerData == null){
            return null;
        }
        return getOrCreate(playerData);
    }
    public static PMPlayerData getOrCreate(PlayerData playerData){
        PDSection pdSection = playerData.getConfigSection(PMPlayerData.class);
        if (pdSection != null){
            return (PMPlayerData)pdSection;
        }
        return new PMPlayerData(playerData);
    }

    //------------------------------------------------------------------------------------------------------------------
    //  PixelmonMultiplier PlayerData
    //------------------------------------------------------------------------------------------------------------------

    private PlayerData playerData;
    private Double personalMultiplier = 0D;

    public PMPlayerData(PlayerData playerData) {
        this.playerData = playerData;
        this.personalMultiplier = playerData.getConfig().getDouble("PixelmonMultiplier.personalMultiplier", personalMultiplier);
    }

    @Override
    public void save(PlayerData playerData) {
        playerData.getConfig().setValue("PixelmonMultiplier.personalMultiplier",this.personalMultiplier);
    }

    public void setRecentChanged() {
        this.playerData.setRecentChanged();
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    public Double getPersonalMultiplier() {
        return personalMultiplier;
    }

}
