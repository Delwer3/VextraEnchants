package org.EdgePlugins.Effects;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class PUSH {
    public static void pushEntity(LivingEntity entity, int amount) {
        if (entity != null) {
            Location location = entity.getLocation();
            Vector direction = location.getDirection().normalize();

            Vector velocity = direction.multiply(amount);

            entity.setVelocity(velocity);
        }
    }
}
