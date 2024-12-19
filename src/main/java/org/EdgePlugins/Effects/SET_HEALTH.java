package org.EdgePlugins.Effects;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

public class SET_HEALTH {
    public static void setHealth(LivingEntity player, int health){
        double newHealth = health;
        double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        if (newHealth > maxHealth) {
            newHealth = maxHealth;
        }
        player.setHealth(newHealth);
    }
}
