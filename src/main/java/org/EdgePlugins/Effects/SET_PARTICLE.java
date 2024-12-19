package org.EdgePlugins.Effects;

import org.EdgePlugins.EdgeEnchants;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class SET_PARTICLE {
    public static void setParticle(LivingEntity player, String particle, int count, double offsetX, double offsetY, double offsetZ, double size) {
        Location location = player.getLocation();
        Particle particle1;

        try {
            particle1 = Particle.valueOf(particle.toUpperCase());
        } catch (IllegalArgumentException e) {
            Bukkit.getLogger().log(Level.SEVERE, "[VextraEnchants]: Particle " + particle + " does not exist");
            return;
        }
        player.getWorld().spawnParticle(particle1, location, count, offsetX, offsetY, offsetZ, size);
    }
}
