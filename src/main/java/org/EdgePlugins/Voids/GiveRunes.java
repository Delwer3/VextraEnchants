package org.EdgePlugins.Voids;

import org.EdgePlugins.Listeners.Items.ActionsItems.Runes;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveRunes {
    public static void giveRune(Player player, ItemStack rune, Integer cost){
            Runes.onReclamRune(player, cost);
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            player.getInventory().addItem(rune);
    }

}
