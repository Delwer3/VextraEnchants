package org.EdgePlugins.Listeners;

import org.EdgePlugins.Configs.GeneralTime;
import org.EdgePlugins.EdgeEnchants;
import org.EdgePlugins.ItemsHandler.Runes;
import org.EdgePlugins.Utils.ExtraItems;
import org.EdgePlugins.Utils.Message;
import org.EdgePlugins.Voids.GiveRunes;
import org.EdgePlugins.menu.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;


public class GuiListener implements Listener {
    private EdgeEnchants plugin = (EdgeEnchants) EdgeEnchants.getPlugin();
    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();


        if (player.hasMetadata("OpenMenuEdge")){
            if (event.getSlot() == 10){
                if (event.getClick().isLeftClick()) {
                    if (player.getLevel() >= plugin.getCustomCategory().getCategorysCost().get(0)) {
                        GiveRunes.giveRune(player, Runes.ItemRune(plugin, 0), plugin.getCustomCategory().getCategorysCost().get(0));
                    } else {
                        player.sendMessage(Message.getTranslate(plugin.getconfigCustom().getNoXpPlayer()));
                    }
                }else if (event.getClick().isRightClick()) {
                    player.closeInventory();
                    CategoryOne.openMenuCategoryOne(plugin, player, null);
                }
            } else if (event.getSlot() == 11){
                if (event.getClick().isLeftClick()) {
                    if (player.getLevel() >= plugin.getCustomCategory().getCategorysCost().get(1)) {
                        GiveRunes.giveRune(player, Runes.ItemRune(plugin, 1), plugin.getCustomCategory().getCategorysCost().get(1));
                    } else {
                        player.sendMessage(Message.getTranslate(plugin.getconfigCustom().getNoXpPlayer()));
                    }
                }else if (event.getClick().isRightClick()) {
                    player.closeInventory();
                    CategoryTwo.openMenuCategoryTwo(plugin, player, null);
                }
            } else if (event.getSlot() == 12){
                if (event.getClick().isLeftClick()) {
                    if (player.getLevel() >= plugin.getCustomCategory().getCategorysCost().get(2)) {
                        GiveRunes.giveRune(player, Runes.ItemRune(plugin, 2), plugin.getCustomCategory().getCategorysCost().get(2));
                    } else {
                        player.sendMessage(Message.getTranslate(plugin.getconfigCustom().getNoXpPlayer()));
                    }
                }else if (event.getClick().isRightClick()) {
                    player.closeInventory();
                    CategoryThree.openMenuCategoryThree(plugin, player, null);
                }
            } else if (event.getSlot() == 13){
                if (event.getClick().isLeftClick()) {
                    if (player.getLevel() >= plugin.getCustomCategory().getCategorysCost().get(3)) {
                        GiveRunes.giveRune(player, Runes.ItemRune(plugin, 3), plugin.getCustomCategory().getCategorysCost().get(3));
                    } else {
                        player.sendMessage(Message.getTranslate(plugin.getconfigCustom().getNoXpPlayer()));
                    }
                    }else if (event.getClick().isRightClick()) {
                        player.closeInventory();
                        CategoryFort.openMenuCategoryFort(plugin, player, null);
                    }
            } else if (event.getSlot() == 14){
                if (event.getClick().isLeftClick()) {
                    if (player.getLevel() >= plugin.getCustomCategory().getCategorysCost().get(4)) {
                        GiveRunes.giveRune(player, Runes.ItemRune(plugin, 4), plugin.getCustomCategory().getCategorysCost().get(4));
                    } else {
                        player.sendMessage(Message.getTranslate(plugin.getconfigCustom().getNoXpPlayer()));
                    }
                }else if (event.getClick().isRightClick()) {
                    player.closeInventory();
                    CategoryFive.openMenuCategoryFive(plugin, player, null);
                }
            } else if (event.getSlot() == 15){
                if (event.getClick().isLeftClick()) {
                    if (player.getLevel() >= plugin.getCustomCategory().getCategorysCost().get(5)) {
                        GiveRunes.giveRune(player, Runes.ItemRune(plugin, 5), plugin.getCustomCategory().getCategorysCost().get(5));
                    } else {
                        player.sendMessage(Message.getTranslate(plugin.getconfigCustom().getNoXpPlayer()));
                    }
                } else if (event.getClick().isRightClick()) {
                    player.closeInventory();
                    CategorySix.openMenuCategorySix(plugin, player, null);
                }
            } else if (event.getSlot() == 16){
                if (plugin.getconfigCustom().getGetEnableBooleanRandomEnchant()) {
                    player.closeInventory();
                    CategorySix.openMenuCategorySix(plugin, player, null);
                }
            } else if (event.getSlot() == 26){
                if (plugin.getconfigCustom().getGetEnableBooleanPasalEnchants()) {
                    player.closeInventory();
                    TemporaryEnchants.openMenuTemporaryEnchants(plugin, player);
                }
            }
            event.setCancelled(true);
        }
        if (player.hasMetadata("OpenMenuEnchant1")){
            if (event.getSlot() == 26){
                player.closeInventory();
                CategoryOne.openMenuCategoryOne(plugin, player, event.getCurrentItem().getAmount());
            }
            event.setCancelled(true);
        } else if ((player.hasMetadata("OpenMenuEnchant2"))){
            if (event.getSlot() == 26){
                player.closeInventory();
                CategoryTwo.openMenuCategoryTwo(plugin, player, event.getCurrentItem().getAmount());
            }
            event.setCancelled(true);
        } else if ((player.hasMetadata("OpenMenuEnchant3"))){
            if (event.getSlot() == 26){
                player.closeInventory();
                CategoryThree.openMenuCategoryThree(plugin, player, event.getCurrentItem().getAmount());
            }
            event.setCancelled(true);
        } else if ((player.hasMetadata("OpenMenuEnchant4"))){
            if (event.getSlot() == 26){
                player.closeInventory();
                CategoryFort.openMenuCategoryFort(plugin, player, event.getCurrentItem().getAmount());
            }
            event.setCancelled(true);
        } else if ((player.hasMetadata("OpenMenuEnchant5"))){
            if (event.getSlot() == 26){
                player.closeInventory();
                CategoryFive.openMenuCategoryFive(plugin, player, event.getCurrentItem().getAmount());
            }
            event.setCancelled(true);
        } else if ((player.hasMetadata("OpenMenuEnchant6"))){
            if (event.getSlot() == 26){
                player.closeInventory();
                CategorySix.openMenuCategorySix(plugin, player, event.getCurrentItem().getAmount());
            }
            event.setCancelled(true);
        }

        if ((player.hasMetadata("OpenMenuTemporaryEnchant"))){
            if (GeneralTime.isTimeCorrect == true) {
                if (event.getSlot() == 6) {
                    if (player.getLevel() >= plugin.getconfigCustom().getCategoryTemporaryCost()) {
                        GiveRunes.giveRune(player, Runes.ItemRuneListener(plugin), plugin.getconfigCustom().getCategoryTemporaryCost());
                    } else {
                        player.sendMessage(Message.getTranslate(plugin.getconfigCustom().getNoXpPlayer()));
                    }
                }
            }
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onIventoryClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();

        if (player.hasMetadata("OpenMenuEdge")){
            player.removeMetadata("OpenMenuEdge", EdgeEnchants.getPlugin());
        } else if (player.hasMetadata("OpenMenuEnchants")) {
            player.removeMetadata("OpenMenuEnchants", EdgeEnchants.getPlugin());
        } else if (player.hasMetadata("OpenMenuEnchant1")) {
            player.removeMetadata("OpenMenuEnchant1", EdgeEnchants.getPlugin());
        } else if (player.hasMetadata("OpenMenuEnchant2")) {
            player.removeMetadata("OpenMenuEnchant2", EdgeEnchants.getPlugin());
        } else if (player.hasMetadata("OpenMenuEnchant3")) {
            player.removeMetadata("OpenMenuEnchant3", EdgeEnchants.getPlugin());
        } else if (player.hasMetadata("OpenMenuEnchant4")) {
            player.removeMetadata("OpenMenuEnchant4", EdgeEnchants.getPlugin());
        } else if (player.hasMetadata("OpenMenuEnchant5")) {
            player.removeMetadata("OpenMenuEnchant5", EdgeEnchants.getPlugin());
        } else if (player.hasMetadata("OpenMenuEnchant6")) {
            player.removeMetadata("OpenMenuEnchant6", EdgeEnchants.getPlugin());
        } else if (player.hasMetadata("OpenMenuTemporaryEnchant")) {
            player.removeMetadata("OpenMenuTemporaryEnchant", EdgeEnchants.getPlugin());
        }

    }

}
