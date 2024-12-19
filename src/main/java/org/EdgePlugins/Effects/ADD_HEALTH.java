package org.EdgePlugins.Effects;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class ADD_HEALTH {
    public static void addHealh(LivingEntity player, int health){
        double newHealth = player.getHealth() + health;
        double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        if (newHealth > maxHealth) {
            newHealth = maxHealth;
        }
        player.setHealth(newHealth);
    }
}
