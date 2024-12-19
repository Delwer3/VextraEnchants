package org.EdgePlugins.Listeners;

import org.EdgePlugins.Configs.ConfigCustom;
import org.EdgePlugins.Configs.Enchants;
import org.EdgePlugins.EdgeEnchants;
import org.EdgePlugins.Listeners.Items.ActionsItems.Enchant;
import org.EdgePlugins.Utils.Getter;
import org.EdgePlugins.Utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class ApplyEnchantListener implements Listener {
    public static final NamespacedKey CUSTOM_ENCHANT_NAME = new NamespacedKey(EdgeEnchants.getPlugin(), "EnchantNameClave");
    public static final NamespacedKey CUSTOM_ENCHANT_LEVEL = new NamespacedKey(EdgeEnchants.getPlugin(), "EnchantLevel");
    public static EdgeEnchants plugin = (EdgeEnchants) EdgeEnchants.getPlugin();
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
            ItemStack bookItem = event.getCurrentItem();
            ItemStack itemToApply = event.getCursor();
        if (bookItem == null || itemToApply == null) {
            return;
        }

        ItemMeta bookmeta = bookItem.getItemMeta();
        ItemMeta itemmetaToApply = itemToApply.getItemMeta();
        if (itemmetaToApply != null && bookmeta != null && itemToApply.getType() != Material.AIR) {
            if (bookmeta.getPersistentDataContainer().has(CUSTOM_ENCHANT_NAME)){
                if (Getter.getListEnchantName(itemToApply) == null){
                    int level = bookmeta.getPersistentDataContainer().get(CUSTOM_ENCHANT_LEVEL, PersistentDataType.INTEGER);
                    String enchantName = bookmeta.getPersistentDataContainer().get(CUSTOM_ENCHANT_NAME, PersistentDataType.STRING);
                    if (plugin.getEnchants().getEnchantlist().contains(enchantName)) {
                        if (plugin.getEnchants().getMaterial(Enchants.getMapEnchantsApplicable().get(enchantName), enchantName).contains(itemToApply.getType())) {
                        bookItem.setAmount(bookItem.getAmount() - 1);
                        Enchant.applyEnchantBook(enchantName, level, itemToApply);
                    }else{
                        event.getWhoClicked().sendMessage(Message.getTranslate(plugin.getconfigCustom().getIsNotApplicable()));
                    }
                        return;
                    }
                }
                if (!(Getter.getListEnchantName(itemToApply).contains(bookmeta.getPersistentDataContainer().get(CUSTOM_ENCHANT_NAME, PersistentDataType.STRING)))) {
                    Bukkit.getConsoleSender().sendMessage(Getter.getListEnchantName(itemToApply).toString());
                    int level = bookmeta.getPersistentDataContainer().get(CUSTOM_ENCHANT_LEVEL, PersistentDataType.INTEGER);
                    String enchantName = bookmeta.getPersistentDataContainer().get(CUSTOM_ENCHANT_NAME, PersistentDataType.STRING);
                    if (plugin.getEnchants().getEnchantlist().contains(enchantName)) {
                        if (plugin.getEnchants().getMaterial(Enchants.getMapEnchantsApplicable().get(enchantName), enchantName).contains(itemToApply.getType())) {
                            bookItem.setAmount(bookItem.getAmount() - 1);
                            Enchant.applyEnchantBook(enchantName, level, itemToApply);
                        }else{
                            event.getWhoClicked().sendMessage(Message.getTranslate(plugin.getconfigCustom().getIsNotApplicable()));
                        }
                    }
                }else{
                    event.getWhoClicked().sendMessage(Message.getTranslate("&cThis enchant is contains in this item!"));
                }
            }
        }
    }

}