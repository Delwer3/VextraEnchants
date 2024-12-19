package org.EdgePlugins.Utils;

import org.EdgePlugins.Configs.CustomCategory;
import org.EdgePlugins.Configs.GeneralTime;
import org.EdgePlugins.EdgeEnchants;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ExtraItems {
    public static ItemStack Panel(EdgeEnchants plugin){
        Material material = Material.matchMaterial(plugin.getconfigCustom().getGetMaterialfillItem());
        String name = plugin.getconfigCustom().getGetNameDisplayfillItem();
        if (name == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. Invalid Filler item Category &4Name Display"));
            return null;
        }
        if (material == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. Invalid Filler item Category &4Material"));
            return null;
        }
        ItemStack panelItem = new ItemStack(material, 1);
        ItemMeta meta = panelItem.getItemMeta();
        meta.setDisplayName(Message.getTranslate(name));
        panelItem.setItemMeta(meta);
        return panelItem;
    }
    public static ItemStack RandomItemStack(EdgeEnchants plugin){
        if (plugin.getconfigCustom().getGetEnableBooleanRandomEnchant() == false){
            return getPanel(plugin);
        }
        Material material = Material.matchMaterial(plugin.getconfigCustom().getGetMaterialRandomEnchant());
        String name = plugin.getconfigCustom().getGetNameDisplayRandomEnchant();
        if (name == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. Invalid Random Enchant Category &4Name Display"));
            return getPanel(plugin);
        }
        if (material == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. Invalid Random Enchant Category &4Material"));
            return getPanel(plugin);
        }

        ItemStack panelItem = new ItemStack(material, 1);
        ItemMeta meta = panelItem.getItemMeta();
        meta.setDisplayName(Message.getTranslate(Message.getTranslate(name)));
        panelItem.setItemMeta(meta);
        return panelItem;
    }
    public static ItemStack PasalEnchants(EdgeEnchants plugin){
        if (plugin.getconfigCustom().getGetEnableBooleanPasalEnchants() == false){
            return getPanel(plugin);
        }
        Material material = Material.matchMaterial(plugin.getconfigCustom().getGetMaterialPasalEnchants());
        String name = plugin.getconfigCustom().getGetNameDisplayPasalEnchants();
        if (name == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. Invalid Temporary Enchantments Category &4Name Display"));
            return getPanel(plugin);
        }
        if (material == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. Invalid Temporary Enchantments Category &4Material"));
            return getPanel(plugin);
        }

        ItemStack panelItem = new ItemStack(material, 1);
        ItemMeta meta = panelItem.getItemMeta();
        meta.setDisplayName(Message.getTranslate(Message.getTranslate(name)));
        panelItem.setItemMeta(meta);
        return panelItem;
    }

    public static ItemStack TimerItem(EdgeEnchants plugin){
        Material material = Material.matchMaterial(plugin.getconfigCustom().getGetMaterialreamening());
        String name = plugin.getconfigCustom().getGetNameDisplayreamening();
        if (name == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. Invalid temporary-temporizer &4Name Display"));
            return getPanel(plugin);
        }
        if (material == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. Invalid temporary-temporizer &4Material"));
            return getPanel(plugin);
        }

        ItemStack panelItem = new ItemStack(material, 1);
        ItemMeta meta = panelItem.getItemMeta();
        meta.setDisplayName(Message.getTranslate(Message.getTranslate(name)));
        List<String> lore = new ArrayList<>();
        int totalMinutes = GeneralTime.getRemeningTime();
        int hours = totalMinutes / 60;
        int minutes = totalMinutes % 60;
        lore.add(Message.getTranslate("&7"+hours+"&7h "+minutes+"&7m"));

        meta.setLore(lore);
        panelItem.setItemMeta(meta);
        return panelItem;
    }

    public static ItemStack TimerNoMore(EdgeEnchants plugin){
        Material material = Material.matchMaterial(plugin.getconfigCustom().getGetMaterialNoMoreEnchants());
        String name = plugin.getconfigCustom().getGetNameDisplayNoMoreEnchants();
        if (name == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. Invalid No more enchants item (temporary items) &4Name Display"));
            return getPanel(plugin);
        }
        if (material == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. Invalid No more enchants item (temporary items) &4Material"));
            return getPanel(plugin);
        }

        ItemStack panelItem = new ItemStack(material, 1);
        ItemMeta meta = panelItem.getItemMeta();
        meta.setDisplayName(Message.getTranslate(Message.getTranslate(name)));
        panelItem.setItemMeta(meta);
        return panelItem;
    }
    public static ItemStack Botton2Item(EdgeEnchants plugin){
        Material material = Material.matchMaterial(plugin.getconfigCustom().getGetMaterialBotton2());
        String name = plugin.getconfigCustom().getGetNameDisplayBotton2();
        if (name == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. Invalid temporary-claim-item-2 (temporary items) &4Name Display"));
            return getPanel(plugin);
        }
        if (material == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. Invalid temporary-claim-item-2 (temporary items) &4Material"));
            return getPanel(plugin);
        }
        String message1 = plugin.getconfigCustom().getMessageCostOfCategoryTemporary();
        if (message1 == null) {
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. Invalid temporary-claim-item-2 (temporary items) &4Cost Message"));
            return getPanel(plugin);
        }
        ItemStack panelItem = new ItemStack(material, 1);
        ItemMeta meta = panelItem.getItemMeta();
        meta.setDisplayName(Message.getTranslate(Message.getTranslate(name)));
        List<String> lore = new ArrayList<>();
        lore.add(Message.getTranslate(message1+plugin.getconfigCustom().getCategoryTemporaryCost()));
        meta.setLore(lore);
        panelItem.setItemMeta(meta);
        return panelItem;
    }
    public static ItemStack Botton1Item(EdgeEnchants plugin){
        Material material = Material.matchMaterial(plugin.getconfigCustom().getGetMaterialBotton1());
        String name = plugin.getconfigCustom().getGetNameDisplayBotton1();
        List<String> lore = plugin.getconfigCustom().getLoreBotton1();
        if (name == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. Invalid temporary-claim-item-1 (temporary items) &4Name Display"));
            return getPanel(plugin);
        }
        if (material == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. Invalid temporary-claim-item-1 (temporary items) &4Material"));
            return getPanel(plugin);
        }
        if (lore == null || lore.isEmpty()){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. Invalid temporary-claim-item-1 (temporary items) &4Lore"));
            return getPanel(plugin);
        }
        int fall = plugin.getTemporaryEnchants().getGetPercentToFall();
        int succes = Math.abs(fall - 100);
        lore.set(0, Message.getTranslate(lore.get(0)+fall+"%"));
        lore.set(1, Message.getTranslate(lore.get(1)+succes+"%"));
        ItemStack panelItem = new ItemStack(material, 1);
        ItemMeta meta = panelItem.getItemMeta();
        meta.setLore(lore);
        meta.setDisplayName(Message.getTranslate(Message.getTranslate(name)));
        panelItem.setItemMeta(meta);
        return panelItem;
    }




    public static ItemStack getRandomItemEnchantCategory(EdgeEnchants plugin){
        return RandomItemStack(plugin);
    }
    public static ItemStack getTemporaryEnchantsItem(EdgeEnchants plugin){
        return PasalEnchants(plugin);
    }
    public static ItemStack getPanel(EdgeEnchants plugin){
        return Panel(plugin);
    }
    public static ItemStack getTimerNoMoreItem(EdgeEnchants plugin){
        return TimerNoMore(plugin);
    }
    public static ItemStack getRemeaningItem(EdgeEnchants plugin){
        return TimerItem(plugin);
    }
    public static ItemStack getBotton1Item(EdgeEnchants plugin){
        return Botton1Item(plugin);
    }
    public static ItemStack getBotton2Item(EdgeEnchants plugin){
        return Botton2Item(plugin);
    }
}
