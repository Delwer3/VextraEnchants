package org.EdgePlugins.Effects;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class EXPLOSION {
    public static void setExplosionReal(LivingEntity player, float potence){
        if (player.getLocation() != null){
            player.getWorld().createExplosion(player.getLocation(), potence);
        }
    }
    public static void setExplosionEffect(LivingEntity player, float potence){
        if (player.getLocation() != null){
            player.getWorld().createExplosion(player.getLocation(), potence, false, false);
        }
    }
}
