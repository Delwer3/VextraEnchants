package org.EdgePlugins.Effects;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class R_A_DAMAGE {
    public static void reduceDamage(LivingEntity player, int amount, Event eventer) {
        if (eventer instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) eventer;
            if (event.getEntity() instanceof Player && event.getEntity().equals(player)) {
                double damage = event.getDamage();
                double reducedDamage = damage * (1 - amount / 100.0);
                event.setDamage(reducedDamage);
            }
        }
    }
    public static void amplifyDamage(LivingEntity player, int amount, Event eventer) {
        if (eventer instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent event = (EntityDamageByEntityEvent) eventer;
            if (event.getEntity() instanceof Player && event.getEntity().equals(player)) {
                double damage = event.getDamage();
                double amplifiedDamage = damage * (1 + amount / 100.0);
                event.setDamage(amplifiedDamage);
            }
        }
    }
}
