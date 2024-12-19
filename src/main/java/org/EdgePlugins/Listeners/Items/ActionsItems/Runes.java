package org.EdgePlugins.Listeners.Items.ActionsItems;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Runes {
    public static void onReclamRune(Player player, Integer xpToRemove){
        removeXp(player, xpToRemove);
    }
    private static int getExpToNext(int level) {
        if (level >= 30) {
            return level * 9 - 158;
        }
        if (level >= 15) {
            return level * 5 - 38;
        }
        return level * 2 + 7;
    }
    public static int getExpToReachLevel(int level) {
        int totalExp = 0;
        for (int i = 0; i < level; i++) {
            totalExp += getExpToNext(i);
        }
        return totalExp;
    }
    public static void removeXp(Player player, Integer xpToRemove) {
        player.giveExp(-(getExpToReachLevel(xpToRemove)));
    }




}

