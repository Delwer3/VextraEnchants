package org.EdgePlugins.Effects;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.logging.Level;

public class PotionSet {
    public static void setPotionEffect(LivingEntity player, String potion, int duration, int effectNumber) {
        if (potion == null) {
            Bukkit.getLogger().log(Level.WARNING, "[VextraEnchants] The potion effect name is null");
            return;
        }

        PotionEffectType potionType = PotionEffectType.getByName(potion);
        if (potionType == null) {
            Bukkit.getLogger().log(Level.WARNING, "[VextraEnchants] The potion effect " + potion + " does not exist");
            return;
        }

        try {
            PotionEffect effect = new PotionEffect(potionType, duration, effectNumber);
            player.addPotionEffect(effect);
        } catch (IllegalArgumentException e) {
            Bukkit.getLogger().log(Level.SEVERE, "[VextraEnchants] Error applying potion effect: " + e.getMessage());
        }
    }

    public static void removePotionEffect(LivingEntity player, String potion) {
        PotionEffectType potionType = PotionEffectType.getByName(potion);
        if (potionType != null) {
            player.removePotionEffect(potionType);
        } else {
            Bukkit.getLogger().log(Level.WARNING, "[VextraEnchants] The potion effect " + potion + " does not exist");
        }
    }
}
