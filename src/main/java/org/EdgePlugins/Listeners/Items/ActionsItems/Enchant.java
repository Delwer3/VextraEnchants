package org.EdgePlugins.Listeners.Items.ActionsItems;

import com.google.common.base.Strings;
import org.EdgePlugins.Configs.CustomCategory;
import org.EdgePlugins.Configs.Enchants;
import org.EdgePlugins.EdgeEnchants;
import org.EdgePlugins.Utils.Message;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.ListPersistentDataType;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.persistence.PersistentDataType;

public class Enchant {

    public static final NamespacedKey CUSTOM_ENCHANT_LIST_NAME = new NamespacedKey(EdgeEnchants.getPlugin(), "EnchantList");
    public static final NamespacedKey CUSTOM_ENCHANT_LIST_CD = new NamespacedKey(EdgeEnchants.getPlugin(), "EnchantCd");

    public static void applyEnchantBook(String nameBook, Integer level, ItemStack itemToApply) {

        loreEnchant(nameBook, level, itemToApply);
    }

    public static void loreEnchant(String namebook, int level, ItemStack itemToApply) {
        ItemMeta meta = itemToApply.getItemMeta();
        List<String> loreList = meta != null && meta.hasLore() ? meta.getLore() : new ArrayList<>();
        String nameDisplayEnchantColor = CustomCategory.getCategoryColor(Enchants.getCategoryEnchant(namebook));
        String nameDisplayName = Enchants.getNameOfEnchant(namebook);

        if (loreList != null) {
            if (!loreList.isEmpty()) {
                List<String> newLoreList = new ArrayList<>();
                newLoreList.add(Message.getTranslate(nameDisplayEnchantColor + nameDisplayName + " " + level));

                for (String lore : loreList) {
                    newLoreList.add(Message.getTranslate(lore));
                }

                meta.setLore(newLoreList);
            } else {
                loreList.add(Message.getTranslate(nameDisplayEnchantColor + nameDisplayName + " " + level));
                meta.setLore(loreList);
            }
            if (itemToApply.getItemMeta().getPersistentDataContainer().has(CUSTOM_ENCHANT_LIST_NAME)){
                String listaStrings = itemToApply.getItemMeta().getPersistentDataContainer().get(CUSTOM_ENCHANT_LIST_NAME, PersistentDataType.STRING);
                String newList = listaStrings+(namebook + ":" + level+",");
                meta.getPersistentDataContainer().set(CUSTOM_ENCHANT_LIST_NAME, PersistentDataType.STRING, newList);
            }else {

                meta.getPersistentDataContainer().set(CUSTOM_ENCHANT_LIST_NAME, PersistentDataType.STRING, (namebook + ":" + level+","));
            }
            if (itemToApply.getItemMeta().getPersistentDataContainer().has(CUSTOM_ENCHANT_LIST_CD)){
                String listaStrings = itemToApply.getItemMeta().getPersistentDataContainer().get(CUSTOM_ENCHANT_LIST_CD, PersistentDataType.STRING);
                String newList = listaStrings+(namebook + ":" + Enchants.getEnchantCd(namebook, level)+":"+level+":"+"true"+",");
                meta.getPersistentDataContainer().set(CUSTOM_ENCHANT_LIST_CD, PersistentDataType.STRING, newList);
            }else {

                meta.getPersistentDataContainer().set(CUSTOM_ENCHANT_LIST_CD, PersistentDataType.STRING, (namebook + ":" + Enchants.getEnchantCd(namebook, level)+":"+level+":"+"true"+","));
            }
            itemToApply.setItemMeta(meta);
        }
    }
}

