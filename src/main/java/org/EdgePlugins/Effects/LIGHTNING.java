package org.EdgePlugins.Effects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class LIGHTNING {
    public static void ligting(LivingEntity player) {
        Location location = player.getLocation();
        player.getWorld().strikeLightning(location);
    }

    public static void ligtingEffect(LivingEntity player) {
        Location location = player.getLocation();
        player.getWorld().strikeLightningEffect(location);

    }
}
