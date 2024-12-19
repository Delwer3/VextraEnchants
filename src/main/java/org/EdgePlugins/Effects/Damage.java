package org.EdgePlugins.Effects;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Damage {
    public static void setDamage(LivingEntity player, int damage){
        player.damage(damage);
    }
}
