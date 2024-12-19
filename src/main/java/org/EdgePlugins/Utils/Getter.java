package org.EdgePlugins.Utils;

import org.EdgePlugins.Listeners.Items.ActionsItems.Enchant;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class Getter {
    public static List<List<String>> getListEnchant(ItemStack item) {
        if (item.getItemMeta().getPersistentDataContainer().has(Enchant.CUSTOM_ENCHANT_LIST_NAME, PersistentDataType.STRING)) {
            String cadenalista = (item.getItemMeta().getPersistentDataContainer().get(Enchant.CUSTOM_ENCHANT_LIST_NAME, PersistentDataType.STRING));
            String[] partes = cadenalista.split(",");
            ArrayList<List<String>> listEnchantsFull = new ArrayList<>();
            for (String parte : partes) {
                List<String> newpart = new ArrayList<>();
                String[] partes1 = parte.split(":");
                newpart.add(partes1[0]);
                newpart.add(partes1[1]);
                listEnchantsFull.add(newpart);
            }
            return listEnchantsFull;
        }
        return null;
    }

    public static List<String> getListEnchantName(ItemStack item) {
        if (item.getItemMeta().getPersistentDataContainer().has(Enchant.CUSTOM_ENCHANT_LIST_NAME, PersistentDataType.STRING)) {
            String cadenalista = (item.getItemMeta().getPersistentDataContainer().get(Enchant.CUSTOM_ENCHANT_LIST_NAME, PersistentDataType.STRING));
            String[] partes = cadenalista.split(",");
            ArrayList<List<String>> listEnchantsFull = new ArrayList<>();
            ArrayList<String> listname = new ArrayList<>();
            for (String parte : partes) {
                List<String> newpart = new ArrayList<>();
                String[] partes1 = parte.split(":");
                listname.add(partes1[0]);
                listEnchantsFull.add(newpart);
            }
            return listname;
        }
        return null;
    }
}
