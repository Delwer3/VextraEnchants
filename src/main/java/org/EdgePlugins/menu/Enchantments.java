package org.EdgePlugins.menu;

import org.EdgePlugins.EdgeEnchants;
import org.EdgePlugins.Utils.ExtraItems;
import org.EdgePlugins.Utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Enchantments {
    public static Inventory getInventoryEnchantments(EdgeEnchants plugin, Player player){
        Inventory menuenchant = Bukkit.createInventory(player, 9 * 3, "");
        ItemStack item1Category = plugin.getCustomCategory().get1ItemCategory();
        ItemStack item2Category = plugin.getCustomCategory().get2ItemCategory();
        ItemStack item3Category = plugin.getCustomCategory().get3ItemCategory();
        ItemStack item4Category = plugin.getCustomCategory().get4ItemCategory();
        ItemStack item5Category = plugin.getCustomCategory().get5ItemCategory();
        ItemStack item6Category = plugin.getCustomCategory().get6ItemCategory();
        ItemStack panelItem = ExtraItems.getPanel(plugin);
        if (panelItem == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. Invalid filled item"));
        }
        if (item1Category == null || item2Category == null || item3Category == null || item4Category == null || item5Category == null || item6Category == null){
            return null;
        }
        for (int i = 0; i < menuenchant.getSize(); i++){
            menuenchant.setItem(i, panelItem);
        }
        menuenchant.setItem(10, item1Category);
        menuenchant.setItem(11, item2Category);
        menuenchant.setItem(12, item3Category);
        menuenchant.setItem(13, item4Category);
        menuenchant.setItem(14, item5Category);
        menuenchant.setItem(15, item6Category);
        menuenchant.setItem(16, ExtraItems.getRandomItemEnchantCategory(plugin));
        menuenchant.setItem(26, ExtraItems.getTemporaryEnchantsItem(plugin));
        return menuenchant;
    }
    public static void openMenu(EdgeEnchants plugin,Player player){
        Inventory invMenu = getInventoryEnchantments(plugin, player);
        if (invMenu == null){
            return;
        }
        if (plugin.getCustomCategory().getListCategorys().size() == 6) {
            player.setMetadata("OpenMenuEdge", new FixedMetadataValue(plugin, invMenu));
            player.openInventory(invMenu);
        }else{
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError loading categories"));
        }
    }
}
