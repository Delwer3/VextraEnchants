package org.EdgePlugins.enchantmentsBooks;

import org.EdgePlugins.Configs.CustomCategory;
import org.EdgePlugins.Configs.Enchants;
import org.EdgePlugins.EdgeEnchants;
import org.EdgePlugins.Utils.ExtraItems;
import org.EdgePlugins.Utils.Message;
import org.EdgePlugins.menu.Enchantments;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class PrincipalBook implements Listener {

  public static final NamespacedKey CUSTOM_ENCHANT_NAME = new NamespacedKey(EdgeEnchants.getPlugin(), "EnchantNameClave");
  public static final NamespacedKey CUSTOM_ENCHANT_LEVEL = new NamespacedKey(EdgeEnchants.getPlugin(), "EnchantLevel");
    public static ItemStack getBookEnchant(String enchant, Integer amount, Player player, EdgeEnchants plugin, Integer level) {
    Material material = Material.matchMaterial(plugin.getconfigCustom().getMaterialOfBook());
      if (material == null){
        material = Material.PAPER;
        Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. Invalid item-material of &4enchant-item"));
      }
      if (level < 0){
        level = 0;
      }
    ItemStack finalItem = new ItemStack(material, amount);
      if (Enchants.enchantlist.contains(enchant)){
        ItemMeta meta = finalItem.getItemMeta();
        String realName = Enchants.getNameOfEnchant(enchant);
        String category = Enchants.getCategoryEnchant(enchant);
        String colorCode = CustomCategory.getCategoryColor(category);
        meta.getPersistentDataContainer().set(CUSTOM_ENCHANT_NAME, PersistentDataType.STRING, enchant);
        meta.getPersistentDataContainer().set(CUSTOM_ENCHANT_LEVEL, PersistentDataType.INTEGER, level);
        List<String> lore = Enchants.getLoreEnchant(enchant);
        List<String> loreNew = new ArrayList<>();
        for (int i = 0; i < lore.size(); i++){
          loreNew.add(Message.getTranslate(lore.get(i)));
        }
        meta.setDisplayName(Message.getTranslate(colorCode+realName));
        meta.setLore(loreNew);
        finalItem.setItemMeta(meta);
        }else{
        if (player != null) {
          player.sendMessage(Message.getTranslate(""));
          return null;
        }else{
          return ExtraItems.getPanel(plugin);
        }
      }
      return finalItem;
    }
  public static ItemStack getBookEnchantvit(String enchant, Integer amount, Player player, EdgeEnchants plugin) {
    Material material = Material.matchMaterial(plugin.getconfigCustom().getMaterialOfBook());
    if (material == null){
      material = Material.PAPER;
      Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. Invalid item-material of &4enchant-item"));
    }
    ItemStack finalItem = new ItemStack(material, amount);
    if (Enchants.enchantlist.contains(enchant)){
      ItemMeta meta = finalItem.getItemMeta();
      String realName = Enchants.getNameOfEnchant(enchant);
      String category = Enchants.getCategoryEnchant(enchant);
      String colorCode = CustomCategory.getCategoryColor(category);
      List<String> lore = Enchants.getLoreEnchant(enchant);
      List<String> loreNew = new ArrayList<>();
      for (int i = 0; i < lore.size(); i++){
        loreNew.add(Message.getTranslate(lore.get(i)));
      }
      meta.setDisplayName(Message.getTranslate(colorCode+realName));
      meta.setLore(loreNew);
      finalItem.setItemMeta(meta);
    }else{
      if (player != null) {
        player.sendMessage(Message.getTranslate(""));
        return null;
      }else{
        return ExtraItems.getPanel(plugin);
      }
    }
    return finalItem;
  }
}
