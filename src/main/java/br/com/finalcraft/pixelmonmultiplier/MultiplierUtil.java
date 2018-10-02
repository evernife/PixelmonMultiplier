package br.com.finalcraft.pixelmonmultiplier;

import org.spongepowered.api.entity.living.player.Player;

public class MultiplierUtil {

    public static double getPermissionMultiplier(Player player){
        if (player.hasPermission(PermissionNodes.passiveVipAqua))   return 0.20D;
        if (player.hasPermission(PermissionNodes.passiveVipMagma))   return 0.20D;
        if (player.hasPermission(PermissionNodes.passiveVipGalactic))   return 0.30D;
        if (player.hasPermission(PermissionNodes.passiveVipPlasma))   return 0.35D;
        if (player.hasPermission(PermissionNodes.passiveVipNeoPlasma))   return 0.40D;
        if (player.hasPermission(PermissionNodes.passiveVipMithiril))   return 0.50D;
        if (player.hasPermission(PermissionNodes.passiveVipGandalf))   return 0.70D;
        return 0D;
    }


}
