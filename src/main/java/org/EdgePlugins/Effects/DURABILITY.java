package org.EdgePlugins.Effects;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DURABILITY {
    public static void repairInMainHand(LivingEntity player1, int amount) {
        if (player1 instanceof Player) {
            Player player = (Player) player1;
            ItemStack itemStack = player.getInventory().getItemInMainHand();
            if (itemStack != null && itemStack.getType().getMaxDurability() > 0) {
                short newDurability = (short) Math.max(0, itemStack.getDurability() - amount);
                itemStack.setDurability(newDurability);
            }
        }
    }

    public static void repairAllArmor(LivingEntity player1, int amount) {
        if (player1 instanceof Player) {
            Player player = (Player) player1;
            ItemStack[] armorContents = player.getInventory().getArmorContents();
            for (ItemStack armor : armorContents) {
                if (armor != null && armor.getType().getMaxDurability() > 0) {
                    short newDurability = (short) Math.max(0, armor.getDurability() - amount);
                    armor.setDurability(newDurability);
                }
            }
        }
    }
    public static void damageInMainHand(LivingEntity player1, int amount) {
        if (player1 instanceof Player) {
            Player player = (Player) player1;
            ItemStack itemStack = player.getInventory().getItemInMainHand();
            if (itemStack != null && itemStack.getType().getMaxDurability() > 0) {
                short newDurability = (short) Math.min(itemStack.getType().getMaxDurability(), itemStack.getDurability() + amount);
                itemStack.setDurability(newDurability);
            }
        }
    }

    public static void damageAllArmor(LivingEntity player1, int amount) {
        if (player1 instanceof Player) {
            Player player = (Player) player1;
            ItemStack[] armorContents = player.getInventory().getArmorContents();
            for (ItemStack armor : armorContents) {
                if (armor != null && armor.getType().getMaxDurability() > 0) {
                    short newDurability = (short) Math.min(armor.getType().getMaxDurability(), armor.getDurability() + amount);
                    armor.setDurability(newDurability);
                }
            }
        }
    }
}
