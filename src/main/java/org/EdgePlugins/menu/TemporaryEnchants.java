package org.EdgePlugins.menu;

import org.EdgePlugins.EdgeEnchants;
import org.EdgePlugins.Utils.ExtraItems;
import org.EdgePlugins.enchantmentsBooks.PrincipalBook;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;

public class TemporaryEnchants {
    public static Inventory getTemporaryMenu(EdgeEnchants plugin, Player player){
        Inventory generalMenu = Bukkit.createInventory(player, 9 * 3, "");
        ItemStack panel = ExtraItems.getPanel(plugin);
        for (int rfull = 0; rfull < generalMenu.getSize(); rfull++){
            generalMenu.setItem(rfull, panel);
        }
        List<String> enchants = plugin.getTemporaryEnchants().getGetTemporaryEnchantList();
        if (enchants == null || enchants.isEmpty()) {
            for (int d = 0; d < generalMenu.getSize(); d++) {
                generalMenu.setItem(d, panel);
            }
            generalMenu.setItem(13, ExtraItems.getTimerNoMoreItem(plugin));
            return generalMenu;
        }
        List<ItemStack> enchantsItemStack = new ArrayList<>();
        if (!(enchants.size() == 7)){
            Bukkit.getConsoleSender().sendMessage(EdgeEnchants.prefixPlugin+" &cError. List temporary enchants is invalid");
            return null;
        }
        if (enchants != null) {
            for (int i = 0; i < enchants.size(); i++) {
                enchantsItemStack.add((PrincipalBook.getBookEnchantvit(enchants.get(i), 1, player, (EdgeEnchants) EdgeEnchants.getPlugin())));
            }
        }else{
            for (int i = 0; i < enchants.size(); i++) {
                enchantsItemStack.add(panel);
            }
        }
        for (int f = 10, a = 0; f < 17; f++, a++){
            generalMenu.setItem(f, enchantsItemStack.get(a));
        }

        generalMenu.setItem(22, ExtraItems.getRemeaningItem(plugin));
        generalMenu.setItem(2, ExtraItems.getBotton1Item(plugin));
        generalMenu.setItem(6, ExtraItems.getBotton2Item(plugin));
        return generalMenu;
    }


    public static void openMenuTemporaryEnchants(EdgeEnchants plugin, Player player){
        Inventory invMenu = getTemporaryMenu(plugin, player);
        player.setMetadata("OpenMenuTemporaryEnchant", new FixedMetadataValue(plugin, invMenu));
        player.openInventory(invMenu);
    }
}
