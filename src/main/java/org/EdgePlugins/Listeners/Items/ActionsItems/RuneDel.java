package org.EdgePlugins.Listeners.Items.ActionsItems;

import org.EdgePlugins.Configs.CustomCategory;
import org.EdgePlugins.Configs.Enchants;
import org.EdgePlugins.Configs.TemporaryEnchants;
import org.EdgePlugins.EdgeEnchants;
import org.EdgePlugins.Utils.Message;
import org.EdgePlugins.enchantmentsBooks.PrincipalBook;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

public class RuneDel {
    public static void delItem(ItemStack itemStack){
        itemStack.setAmount(itemStack.getAmount()-1);
    }

    public static void giveRandomTemporary(EdgeEnchants plugin, Player player){
        int percentToFall = plugin.getTemporaryEnchants().getGetPercentToFall();
        int percentToRealize = Math.abs(percentToFall - 100);
        List<String> listEnchants = plugin.getTemporaryEnchants().getGetTemporaryEnchantList();
        ItemStack finalBook;
        Boolean ismessage = plugin.getconfigCustom().getLostorwonmessageBoolean();
        int random = new Random().nextInt(percentToFall) + percentToRealize;
        int random1;
        if (random > percentToFall){
            if (!(listEnchants.size() > 0)){
                random1 = new Random().nextInt(listEnchants.size()) + 0;
            }else{
                random1 = 0;
            }
            String nameEnchant = listEnchants.get(random1);
            int randomLevel = new Random().nextInt(1) + Enchants.getEnchantMaxLevels(nameEnchant);
            ItemStack book = PrincipalBook.getBookEnchant(nameEnchant, 1, player, plugin, randomLevel);
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
            String category = Enchants.getCategoryEnchant(nameEnchant);
            String colorCode = CustomCategory.getCategoryColor(category);
            if (ismessage) {
                player.sendMessage(Message.getTranslate(plugin.getconfigCustom().getWon5050() + colorCode + Enchants.getNameOfEnchant(nameEnchant)));
            }
            finalBook = book;
        } else{
            List<String> listenchantsfull = plugin.getTemporaryEnchants().getBlackListOfEnchants();
            int random2;
            if (!(listenchantsfull.size() > 0)){
                random2 = new Random().nextInt(listenchantsfull.size()) + 0;
            }else{
                random2 = 0;
            }
            String nameEnchant = listenchantsfull.get(random2);
            Bukkit.getConsoleSender().sendMessage(nameEnchant);
            int randomLevel = new Random().nextInt(1) + Enchants.getEnchantMaxLevels(nameEnchant);
            ItemStack book = PrincipalBook.getBookEnchant(nameEnchant, 1, player, plugin, randomLevel);
            String category = Enchants.getCategoryEnchant(nameEnchant);
            String colorCode = CustomCategory.getCategoryColor(category);
            if (ismessage) {
                player.sendMessage(Message.getTranslate(plugin.getconfigCustom().getLost5050() + colorCode + Enchants.getNameOfEnchant(nameEnchant)));
            }
            finalBook = book;
        }
        player.getInventory().addItem(finalBook);
    }
    public static void giveRnadomCategory(EdgeEnchants plugin, Player player, Integer integer){
        List<String> listEnchants = plugin.getEnchants().getCategory1List();
        if (integer == 1){
            listEnchants = plugin.getEnchants().getCategory1List();
        } else if (integer == 2) {
            listEnchants = plugin.getEnchants().getCategory2List();
        } else if (integer == 3) {
            listEnchants = plugin.getEnchants().getCategory3List();
        } else if (integer == 4) {
            listEnchants = plugin.getEnchants().getCategory4List();
        } else if (integer == 5) {
            listEnchants = plugin.getEnchants().getCategory5List();
        } else if (integer == 6) {
            listEnchants = plugin.getEnchants().getCategory6List();
        }
        ItemStack finalBook;
        int random1;
        if (!(listEnchants.size() > 0)){
            random1 = new Random().nextInt(listEnchants.size()) + 0;
        }else{
            random1 = 0;
        }
            String nameEnchant = listEnchants.get(random1);
            int randomLevel = new Random().nextInt(1) + Enchants.getEnchantMaxLevels(nameEnchant);
            ItemStack book = PrincipalBook.getBookEnchant(nameEnchant, 1, player, plugin, randomLevel);
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
            String category = Enchants.getCategoryEnchant(nameEnchant);
            finalBook = book;
            player.getInventory().addItem(finalBook);
    }
}
