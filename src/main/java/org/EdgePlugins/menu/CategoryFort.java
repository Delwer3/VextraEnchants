package org.EdgePlugins.menu;

import org.EdgePlugins.EdgeEnchants;
import org.EdgePlugins.Utils.Message;
import org.EdgePlugins.enchantmentsBooks.PrincipalBook;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;

import static org.EdgePlugins.menu.CategoryOne.createNewInv;

public class CategoryFort {
    public static List<Inventory> getCategoryFortInv(EdgeEnchants plugin, Player player){
        List<Inventory> inventories = new ArrayList<>();
        ArrayList<ItemStack> enchantlist = new ArrayList<>();
        for (int i = 0; i < plugin.getEnchants().getEnchantlist().size(); i++){
            String enchantName = plugin.getEnchants().getEnchantlist().get(i).toString();
            List<String> categorylist = plugin.getEnchants().getCategory4List();
            if(categorylist.contains(enchantName)) {
                ItemStack bookEnchantMenu = new ItemStack(Material.PAPER);
                ItemMeta meta = bookEnchantMenu.getItemMeta();
                List<String> lore = plugin.getEnchants().getLoreEnchant(enchantName);
                for (int e = 0; e < lore.size(); e++){
                    lore.set(e, Message.getTranslate(lore.get(e)));
                }
                enchantlist.add(PrincipalBook.getBookEnchantvit(enchantName, 1, player, (EdgeEnchants) EdgeEnchants.getPlugin()));
            }
        }


        if (!enchantlist.isEmpty()) {
            int sizeMenu = 27 - 1;
            double numberOfInvs = Math.ceil((double) enchantlist.size() / sizeMenu);
            for (int i = 0; i < numberOfInvs; i++) {
                Inventory invEx = createNewInv(player, i);
                for (int f = i * sizeMenu; f < Math.min((i + 1) * sizeMenu, enchantlist.size()); f++) {
                    ItemStack itemStack = enchantlist.get(f);
                    invEx.setItem(f - i * sizeMenu, itemStack);
                }
                Material material;
                String color;
                if (!((i+1) == ((int) numberOfInvs))){
                    material = Material.LIME_STAINED_GLASS_PANE;
                    color = "&aNext Page";
                }else{
                    material = Material.RED_STAINED_GLASS_PANE;
                    color = "&c";
                }
                ItemStack itemStack1 = new ItemStack(material, i+1);
                ItemMeta meta = itemStack1.getItemMeta();
                meta.setDisplayName(Message.getTranslate(color));
                itemStack1.setItemMeta(meta);
                invEx.setItem(26, itemStack1);
                inventories.add(invEx);
            }
        }

        return inventories;
    }



    public static void openMenuCategoryFort(EdgeEnchants plugin,Player player, Integer number){
        List<Inventory> invMenu = getCategoryFortInv(plugin, player);
        if (invMenu == null || invMenu.isEmpty()){
            return;
        }
        player.setMetadata("OpenMenuEnchant4", new FixedMetadataValue(plugin, invMenu));
        if (number != null){
            number = number;
        }else{
            number = 0;
        }
        if (!(number == invMenu.size())){
            player.openInventory(invMenu.get(number));
        }
    }
}
