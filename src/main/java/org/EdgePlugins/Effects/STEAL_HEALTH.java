package org.EdgePlugins.Effects;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class STEAL_HEALTH {
    public static void stealHealth(LivingEntity aSteal, LivingEntity aGive, int healthASteal){
        double currentHealthASteal = aSteal.getHealth();
        double currentHealthAGive = aGive.getHealth();
        double maxHealthAGive = aGive.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

        if (currentHealthASteal - healthASteal < 0) {
            healthASteal = (int) currentHealthASteal;
            aSteal.setHealth(0);
        } else {
            aSteal.setHealth(currentHealthASteal - healthASteal);
        }

        double newHealthAGive = currentHealthAGive + healthASteal;
        if (newHealthAGive > maxHealthAGive) {
            newHealthAGive = maxHealthAGive;
        }
        aGive.setHealth(newHealthAGive);
    }
}
