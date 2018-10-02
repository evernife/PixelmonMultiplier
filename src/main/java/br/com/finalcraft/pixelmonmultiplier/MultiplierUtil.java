package br.com.finalcraft.pixelmonmultiplier;

import org.spongepowered.api.entity.living.player.Player;

public class MultiplierUtil {

    public static double getByVipPermission(Player player){
        if (player.hasPermission(PermissionNodes.passiveVipAqua))               return 0.20D;
        if (player.hasPermission(PermissionNodes.passiveVipMagma))              return 0.20D;
        if (player.hasPermission(PermissionNodes.passiveVipGalactic))           return 0.30D;
        if (player.hasPermission(PermissionNodes.passiveVipPlasma))             return 0.35D;
        if (player.hasPermission(PermissionNodes.passiveVipNeoPlasma))          return 0.40D;
        if (player.hasPermission(PermissionNodes.passiveVipMithiril))           return 0.50D;
        if (player.hasPermission(PermissionNodes.passiveVipGandalf))            return 0.70D;

        if (player.hasPermission(PermissionNodes.passiveVipFundadorBronze))     return 0.40D;
        if (player.hasPermission(PermissionNodes.passiveVipFundadorOuro))       return 0.50D;
        if (player.hasPermission(PermissionNodes.passiveVipFundadorDiamante))   return 0.70D;
        return 0D;
    }

    public static double getByRankPermission(Player player){
        if (player.hasPermission("be.lenda"))       return 10.00D;
        if (player.hasPermission("be.mito"))        return 6.00D;
        if (player.hasPermission("be.genio"))       return 3.00D;
        if (player.hasPermission("be.mestre"))      return 1.50D;
        if (player.hasPermission("be.prodigio"))    return 0.80D;
        if (player.hasPermission("be.veterano"))    return 0.50D;
        if (player.hasPermission("be.aprendiz"))    return 0.30D;
        if (player.hasPermission("be.membro"))      return 0.15D;
        if (player.hasPermission("be.novato"))      return 0.05D;
        if (player.hasPermission("be.visitante"))   return 0.00D;
        return 0D;
    }


}
