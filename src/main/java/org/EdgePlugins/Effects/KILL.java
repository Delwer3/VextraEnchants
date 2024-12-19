package org.EdgePlugins.Effects;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class KILL {
    public static void killPlayer(LivingEntity player){
        player.setHealth(0.0);
    }
}
