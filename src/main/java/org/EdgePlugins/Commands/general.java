package org.EdgePlugins.Commands;

import org.EdgePlugins.Configs.Enchants;
import org.EdgePlugins.Configs.GeneralTime;
import org.EdgePlugins.EdgeEnchants;
import org.EdgePlugins.Listeners.Items.ActionsItems.Enchant;
import org.EdgePlugins.Utils.Message;
import org.EdgePlugins.enchantmentsBooks.PrincipalBook;
import org.EdgePlugins.menu.Enchantments;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class general implements CommandExecutor {
    public EdgeEnchants plugin;
    public general(EdgeEnchants plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)) {
            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (!(sender.hasPermission("ve.reload"))) {
                        return true;
                    }
                    subCommandReload(sender);
                    return true;
                }else if (args[0].equalsIgnoreCase("reloadtime")) {
                    if (!(sender.hasPermission("ve.reloadtime"))) {
                        return true;
                    }
                    GeneralTime.GeneralTimeMethod(plugin);
                    sender.sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &eReloaded Remaining time!"));
                    return true;
                } else if (args[0].equalsIgnoreCase("list")) {
                    if (!(sender.hasPermission("ve.list"))) {
                        return true;
                    }
                    sender.sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cList of enchants on server:"));
                    sender.sendMessage(plugin.getEnchants().getEnchantlist().toString()+Message.getTranslate("&f"));
                    return true;
                } else if (args[0].equalsIgnoreCase("give")) {
                    if (!(sender.hasPermission("ve.give"))) {
                        return true;
                    }
                    if (args.length > 3) {
                        subCommandGiveConsole(args, sender);
                        return true;
                    } else {
                        sender.sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin + " &eYou have lacked arguments"));
                        return true;
                    }
                } else {
                    sender.sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin + " &eUse /ve help"));
                    return true;
                }
            }
            //console
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin + " &eCan´t use command in console!"));
            return true;
        }
        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("enchantment")) {
                if (!(sender.hasPermission("ve.enchantment"))) {
                    return true;
                }
                Enchantments.openMenu(plugin, ((Player) sender).getPlayer());
            } else if (args[0].equalsIgnoreCase("check")) {
                Player player = ((Player) sender).getPlayer();
                if (player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(Enchant.CUSTOM_ENCHANT_LIST_NAME)){
                   String cadenalista = (player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(Enchant.CUSTOM_ENCHANT_LIST_NAME, PersistentDataType.STRING));
                   player.sendMessage(cadenalista);
                   String[] partes = cadenalista.split(",");
                    ArrayList<List<String>> listEnchantsFull = new ArrayList<>();
                    for (String parte : partes) {
                        Bukkit.getConsoleSender().sendMessage("1");
                        List<String> newpart = new ArrayList<>();
                        String[] partes1 = parte.split(":");
                        newpart.add(partes1[0]);
                        newpart.add(partes1[1]);
                        listEnchantsFull.add(newpart);
                    }
                    player.sendMessage(listEnchantsFull.toString());
                    Bukkit.getConsoleSender().sendMessage(listEnchantsFull.toString());

                }
            } else if (args[0].equalsIgnoreCase("reload")) {
                if (!(sender.hasPermission("ve.reload"))) {
                    return true;
                }
                    subCommandReload(((Player) sender).getPlayer());
            } else if (args[0].equalsIgnoreCase("reloadtime")) {
                if (!(sender.hasPermission("ve.reloadtime"))) {
                    return true;
                }
                GeneralTime.GeneralTimeMethod(plugin);
                sender.sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &eReloaded Remaining time!"));

            } else if (args[0].equalsIgnoreCase("list")) {
                if (!(sender.hasPermission("ve.time"))) {
                    return true;
                }
                sender.sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cList of enchants on server:"));
                sender.sendMessage(plugin.getEnchants().getEnchantlist().toString()+Message.getTranslate("&f"));
            } else if (args[0].equalsIgnoreCase("give")) {
                if (!(sender.hasPermission("ve.give"))) {
                    return true;
                }
                if (args.length > 3) {
                    subCommandGive(args, ((Player) sender).getPlayer());
                } else {
                    sender.sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin + " &eYou have lacked arguments"));
                }
            } else {
                sender.sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin + " &eUse /eden help"));
            }
        }
        return true;
    }
    public void subCommandReload (CommandSender player){
        if (!(player.hasPermission("ve.reload"))) {
            return;
        }
        plugin.getCustomCategory().reloadConfig();
        plugin.getEnchants().reloadConfig();
        plugin.getconfigCustom().reloadConfig();
        plugin.getTemporaryEnchants().reloadConfig();
        player.sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &eReloaded!"));
    }
    public void subCommandGive(String[] args, Player player1) {
        Player player = Bukkit.getPlayerExact(args[1]);
        if (!(player == null)) {
            if (args.length > 4) {
                int maxlevel = Enchants.getEnchantMaxLevels(args[2]);
                if (!plugin.getEnchants().getEnchantlist().contains(args[2])){
                    player1.sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin + " &cThe enchant not exist!"));
                    return;
                }
                if (Integer.parseInt(args[4]) > maxlevel){
                    player1.sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin + " &cThe enchant level not exist!"));
                    return;
                }
                ItemStack book = PrincipalBook.getBookEnchant(args[2], Integer.parseInt(args[3]), player, plugin, Integer.parseInt(args[4]));

                player.getInventory().addItem(book);

                player1.sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin + " &eThe book &b" + args[2] + " &ehas been delivered to &4" + player.getDisplayName()));
            } else {
                player1.sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin + " &eYou have lacked arguments"));
            }
        }else{
            player1.sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin + " &eThat player is invalid!"));
        }
    }
    public void subCommandGiveConsole(String[] args, CommandSender player1) {
        Player player = Bukkit.getPlayerExact(args[1]);
        if (!(player == null)) {
            if (args.length > 4) {
                int maxlevel = Enchants.getEnchantMaxLevels(args[2]);
                if (!plugin.getEnchants().getEnchantlist().contains(args[2])){
                    player1.sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin + " &cThe enchant not exist!"));
                    return;
                }
                if (Integer.parseInt(args[4]) > maxlevel){
                    player1.sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin + " &cThe enchant level not exist!"));
                    return;
                }
                ItemStack book = PrincipalBook.getBookEnchant(args[2], Integer.parseInt(args[3]), player, plugin, Integer.parseInt(args[4]));

                player.getInventory().addItem(book);

                player1.sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin + " &eThe book &b" + args[2] + " &ehas been delivered to &4" + player.getDisplayName()));
            } else {
                player1.sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin + " &eYou have lacked arguments"));
            }
        }else{
            player1.sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin + " &eThat player is invalid!"));
        }
    }

    public boolean onCommand2(CommandSender sender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("enchantment")){
            if (sender instanceof Player) {
                if (args.length >= 1) {
                    if (!sender.hasPermission("ve.enchantment")) {
                        return true;
                    }
                    Enchantments.openMenu(plugin, ((Player) sender).getPlayer());
                    return true;
                }
            } else {
                sender.sendMessage("This command can only be used by players.");
                return true;
            }
        }
        return false;
    }
}
