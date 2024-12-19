package org.EdgePlugins.ItemsHandler;

import org.EdgePlugins.Configs.CustomCategory;
import org.EdgePlugins.EdgeEnchants;
import org.EdgePlugins.Utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class Runes {
    public static final NamespacedKey CUSTOM_RUNE_TEMPORARY = new NamespacedKey(EdgeEnchants.getPlugin(), "RuneTemporary");
    public static final NamespacedKey CUSTOM_RUNE = new NamespacedKey(EdgeEnchants.getPlugin(), "RuneItem");
    public static ItemStack ItemRuneListener(EdgeEnchants plugin){
            Material material = Material.matchMaterial(plugin.getconfigCustom().getGetMaterialRune());
            String name = plugin.getconfigCustom().getGetNameDisplayRune();
            List<String> loreRef = plugin.getconfigCustom().getLoreDisplayRune();
            if (name == null){
                Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. Invalid temporary-rune (temporary items) &4Name Display"));
                return null;
            }
            if (material == null){
                Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. Invalid temporary-rune (temporary items) &4Material"));
                return null;
            }
            if (loreRef == null || loreRef.isEmpty()){
                Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. Invalid temporary-rune (temporary items) &4Lore"));
                return null;
            }

            ItemStack panelaItem = new ItemStack(material, 1);
            ItemMeta meta = panelaItem.getItemMeta();
            meta.getPersistentDataContainer().set(CUSTOM_RUNE_TEMPORARY, PersistentDataType.STRING, "nullStringer");
            meta.setDisplayName(Message.getTranslate(Message.getTranslate(name)));
            List<String> translatedLore = new ArrayList<>();
            for (String line : loreRef) {
             translatedLore.add(Message.getTranslate(line));
            }
             meta.setLore(translatedLore);
             panelaItem.setItemMeta(meta);
            return panelaItem;
    }
    public static ItemStack ItemRune(EdgeEnchants plugin, Integer integer){
        Material material = Material.matchMaterial(plugin.getCustomCategory().getFinallistmaterials().get(integer));
        String name = plugin.getCustomCategory().getFinallistnames().get(integer);
        List<String> loreRef = plugin.getCustomCategory().getFinallistlore().get(integer);
        if (name == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. Invalid rune Category "+integer+" &4Name Display"));
            return null;
        }
        if (material == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. rune [Category "+integer+"] &4Material"));
            material = Material.PAPER;
        }
        if (loreRef == null || loreRef.isEmpty()){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. rune [Category "+integer+"] &4Lore"));
            return null;
        }

        ItemStack panelaItem = new ItemStack(material, 1);
        ItemMeta meta = panelaItem.getItemMeta();
        meta.getPersistentDataContainer().set(CUSTOM_RUNE, PersistentDataType.STRING, ""+(integer+1));
        meta.setDisplayName(Message.getTranslate(Message.getTranslate(name)));
        List<String> translatedLore = new ArrayList<>();
        for (String line : loreRef) {
            translatedLore.add(Message.getTranslate(line));
        }
        meta.setLore(translatedLore);
        panelaItem.setItemMeta(meta);
        return panelaItem;
    }
}
