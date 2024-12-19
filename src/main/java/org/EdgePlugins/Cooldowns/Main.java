package org.EdgePlugins.Cooldowns;

import org.EdgePlugins.EdgeEnchants;
import org.EdgePlugins.Listeners.Items.ActionsItems.Enchant;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;


public class Main implements Listener {
    public static NamespacedKey FINAL_LIST_CD_MAINHAND = new NamespacedKey(EdgeEnchants.getPlugin(), "CdInMainHand");
    public static NamespacedKey FINAL_LIST_CD_MAINHAND_CD = new NamespacedKey(EdgeEnchants.getPlugin(), "CdInMainHandCd");
    public static NamespacedKey FINAL_LIST_CD_MAINHAND_CD_HELMET = new NamespacedKey(EdgeEnchants.getPlugin(), "CdInHelmet");
    public static NamespacedKey FINAL_LIST_CD_MAINHAND_CD_CHESTPLATE = new NamespacedKey(EdgeEnchants.getPlugin(), "CdInChestplaye");
    public static NamespacedKey FINAL_LIST_CD_MAINHAND_CD_LEGGINS = new NamespacedKey(EdgeEnchants.getPlugin(), "CdInLeggins");
    public static NamespacedKey FINAL_LIST_CD_MAINHAND_CD_BOOTS = new NamespacedKey(EdgeEnchants.getPlugin(), "CdInBoots");
    private void updateMainHandItem(Player player, ItemStack newItem) {
        if (newItem != null && newItem.getType() != Material.AIR) {
            if (newItem.getItemMeta().getPersistentDataContainer().has(Enchant.CUSTOM_ENCHANT_LIST_CD)) {
                String cdStringList = newItem.getItemMeta().getPersistentDataContainer().get(Enchant.CUSTOM_ENCHANT_LIST_CD, PersistentDataType.STRING);
                String finalString = getFinalEnchantList(getoldEnchants(player.getPersistentDataContainer().get(FINAL_LIST_CD_MAINHAND, PersistentDataType.STRING)), getnewEnchants(cdStringList), cdStringList);
                player.getPersistentDataContainer().set(FINAL_LIST_CD_MAINHAND, PersistentDataType.STRING, finalString);
            } else {
                player.getPersistentDataContainer().set(FINAL_LIST_CD_MAINHAND, PersistentDataType.STRING, "");
            }
        } else {
            player.getPersistentDataContainer().set(FINAL_LIST_CD_MAINHAND, PersistentDataType.STRING, "");
        }
    }

    public boolean isaHelmet(String itemStack){
        if (itemStack.equalsIgnoreCase("helmet")){
            return true;
        }
        return false;
    }
    public boolean isaChestplate(String itemStack) {
        if (itemStack.equalsIgnoreCase("chestplate")){
            return true;
        }
        return false;
    }

    public boolean isaLeggings(String itemStack) {
        if (itemStack.equalsIgnoreCase("leggins")){
            return true;
        }
        return false;
    }

    public boolean isaBoots(String itemStack) {
        if (itemStack.equalsIgnoreCase("boots")){
            return true;
        }
        return false;
    }
    private void updateArmorItem(Player player, ItemStack newItem, String whatis) {
        if (newItem != null && newItem.getType() != Material.AIR) {
            ItemMeta meta = newItem.getItemMeta();
            if (isaHelmet(whatis)){
                ItemStack helmet = player.getInventory().getHelmet();
                if (helmet != null){
                    PersistentDataContainer container = helmet.getItemMeta().getPersistentDataContainer();
                 if (container.has(Enchant.CUSTOM_ENCHANT_LIST_CD)){
                     String cdString = container.get(Enchant.CUSTOM_ENCHANT_LIST_CD, PersistentDataType.STRING);
                     player.getPersistentDataContainer().set(FINAL_LIST_CD_MAINHAND_CD_HELMET, PersistentDataType.STRING, cdString);
                 }else{
                     player.getPersistentDataContainer().set(FINAL_LIST_CD_MAINHAND_CD_HELMET, PersistentDataType.STRING, "");
                 }
                }else{
                    player.getPersistentDataContainer().set(FINAL_LIST_CD_MAINHAND_CD_HELMET, PersistentDataType.STRING, "");
                }
                return;
            }
            if (isaChestplate(whatis)){
                ItemStack chestplate = player.getInventory().getChestplate();
                if (chestplate != null){
                    PersistentDataContainer container = chestplate.getItemMeta().getPersistentDataContainer();
                    if (container.has(Enchant.CUSTOM_ENCHANT_LIST_CD)){
                        String cdString = container.get(Enchant.CUSTOM_ENCHANT_LIST_CD, PersistentDataType.STRING);
                        player.getPersistentDataContainer().set(FINAL_LIST_CD_MAINHAND_CD_CHESTPLATE, PersistentDataType.STRING, cdString);
                    }else{
                        player.getPersistentDataContainer().set(FINAL_LIST_CD_MAINHAND_CD_CHESTPLATE, PersistentDataType.STRING, "");
                    }
                }else{
                    player.getPersistentDataContainer().set(FINAL_LIST_CD_MAINHAND_CD_CHESTPLATE, PersistentDataType.STRING, "");
                }
            }
            if (isaLeggings(whatis)){
                ItemStack leggins = player.getInventory().getLeggings();
                if (leggins != null){
                    PersistentDataContainer container = leggins.getItemMeta().getPersistentDataContainer();
                    if (container.has(Enchant.CUSTOM_ENCHANT_LIST_CD)){
                        String cdString = container.get(Enchant.CUSTOM_ENCHANT_LIST_CD, PersistentDataType.STRING);
                        player.getPersistentDataContainer().set(FINAL_LIST_CD_MAINHAND_CD_LEGGINS, PersistentDataType.STRING, cdString);
                    }else{
                        player.getPersistentDataContainer().set(FINAL_LIST_CD_MAINHAND_CD_LEGGINS, PersistentDataType.STRING, "");
                    }
                }else{
                    player.getPersistentDataContainer().set(FINAL_LIST_CD_MAINHAND_CD_LEGGINS, PersistentDataType.STRING, "");
                }
            }
            if (isaBoots(whatis)){
                ItemStack boots = player.getInventory().getBoots();
                if (boots != null){
                    PersistentDataContainer container = boots.getItemMeta().getPersistentDataContainer();
                    if (container.has(Enchant.CUSTOM_ENCHANT_LIST_CD)){
                        String cdString = container.get(Enchant.CUSTOM_ENCHANT_LIST_CD, PersistentDataType.STRING);
                        player.getPersistentDataContainer().set(FINAL_LIST_CD_MAINHAND_CD_BOOTS, PersistentDataType.STRING, cdString);
                    }else{
                        player.getPersistentDataContainer().set(FINAL_LIST_CD_MAINHAND_CD_BOOTS, PersistentDataType.STRING, "");
                    }
                }else{
                    player.getPersistentDataContainer().set(FINAL_LIST_CD_MAINHAND_CD_BOOTS, PersistentDataType.STRING, "");
                }
            }
        }
    }

    @EventHandler
    public void onItemHeldChange(PlayerItemHeldEvent event) {
        Bukkit.getScheduler().runTaskLater(EdgeEnchants.getPlugin(), () -> updateMainHandItem(event.getPlayer(), event.getPlayer().getInventory().getItem(event.getNewSlot())), 1L);
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent event) {
        Bukkit.getScheduler().runTaskLater(EdgeEnchants.getPlugin(), () -> updateMainHandItem(event.getPlayer(), event.getPlayer().getInventory().getItemInMainHand()), 1L);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getSlot() == player.getInventory().getHeldItemSlot()) {
            Bukkit.getScheduler().runTaskLater(EdgeEnchants.getPlugin(), () -> updateMainHandItem(player, player.getInventory().getItemInMainHand()), 1L);
        }
    }

    @EventHandler
    public void onSwapHandItems(PlayerSwapHandItemsEvent event) {
        Bukkit.getScheduler().runTaskLater(EdgeEnchants.getPlugin(), () -> updateMainHandItem(event.getPlayer(), event.getMainHandItem()), 1L);
    }

    @EventHandler
    public void onInventoryClicked(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;

        Player player = (Player) event.getWhoClicked();
        int slot = event.getSlot();
        ItemStack rellen = new ItemStack(Material.PAPER);

        if (slot == 36 || slot == 37 || slot == 38 || slot == 39) {
            Bukkit.getScheduler().runTaskLater(EdgeEnchants.getPlugin(), () -> {
                ItemStack changedItem = event.getInventory().getItem(slot);
                if (changedItem != null) {
                    updateArmorItem(player, changedItem, getArmorType(slot));
                } else {
                    updateArmorItem(player, rellen, getArmorType(slot));
                }
            }, 1);
        }
    }
    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (event.getInventory() == null || event.getWhoClicked() instanceof Player) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        Set<Integer> rawSlots = event.getRawSlots();
        ItemStack rellen = new ItemStack(Material.PAPER);

        for (int slot : rawSlots) {
            if (slot == 36 || slot == 37 || slot == 38 || slot == 39) {
                Bukkit.getScheduler().runTaskLater(EdgeEnchants.getPlugin(), () -> {
                    ItemStack changedItem = event.getInventory().getItem(slot);
                    if (changedItem != null) {
                        updateArmorItem(player, changedItem, getArmorType(slot));
                    } else {
                        updateArmorItem(player, rellen, getArmorType(slot));
                    }
                }, 1);
            }
        }
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getItemInHand();
        int[] armorSlots = {36, 37, 38, 39};

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (itemInHand != null) {
                for (int slot : armorSlots) {
                    Bukkit.getScheduler().runTaskLater(EdgeEnchants.getPlugin(), () -> {
                        ItemStack changedItem = player.getInventory().getItem(slot);
                        ItemStack rellen = new ItemStack(Material.PAPER);

                        if (changedItem != null) {
                            updateArmorItem(player, changedItem, getArmorType(slot));
                        } else {
                            updateArmorItem(player, rellen, getArmorType(slot));
                        }
                    }, 1);

                }
            }
        }
    }

    private String getArmorType(int slot) {
        switch (slot) {
            case 36:
                return "boots";
            case 37:
                return "leggins";
            case 38:
                return "chestplate";
            case 39:
                return "helmet";
            default:
                return "";
        }
    }

    public boolean isContainEnchant(String enchant, List<String> listenchants) {
        return listenchants.contains(enchant);
    }

    public String getFinalEnchantList(HashMap<String, Integer> old, HashMap<String, Integer> neW, String s) {
        StringBuilder finalString = new StringBuilder();
        for (String enchant : neW.keySet()) {
            if (old.containsKey(enchant)) {
                finalString.append(enchant).append(":1:").append(old.get(enchant)).append(":true,");
            }
        }
        return s + finalString.toString();
    }

    public HashMap<String, Integer> getoldEnchants(String s) {
        HashMap<String, Integer> enchantsList = new HashMap<>();
        if (s != null && !s.isEmpty()) {
            String[] enchantsFull = s.split(",");
            if (enchantsFull.length > 0) {
                for (String enchants : enchantsFull) {
                    String[] enchants1 = enchants.split(":");
                    if (enchants1.length >= 3) {
                        try {
                            enchantsList.put(enchants1[0], Integer.parseInt(enchants1[2]));
                        } catch (NumberFormatException e) {
                        }
                    }
                }
            }
        }
        return enchantsList;
    }

    public HashMap<String, Integer> getnewEnchants(String s) {
        HashMap<String, Integer> enchantsList = new HashMap<>();
        if (s != null && !s.isEmpty()) {
            String[] enchantsFull = s.split(",");
            if (enchantsFull.length > 0) {
                for (String enchants : enchantsFull) {
                    String[] enchants1 = enchants.split(":");
                    if (enchants1.length >= 3) {
                        try {
                            enchantsList.put(enchants1[0], Integer.parseInt(enchants1[2]));
                        } catch (NumberFormatException e) {
                            // Manejo de excepción
                        }
                    }
                }
            }
        }
        return enchantsList;
    }

}
