package org.EdgePlugins.Configs;


import org.EdgePlugins.EdgeEnchants;
import org.EdgePlugins.Utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Steerable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomCategory {
    private ConfigCrudo configcrudo;
    private EdgeEnchants plugin;
    private String prefix = EdgeEnchants.prefixPlugin;
    //materials category
    private String materialCategory1;
    private static String nameCategory1;
    private static String colorCategory1;
    private List<String> loreCategory1;
    private String materialCategory2;
    private static String nameCategory2;
    private static String colorCategory2;
    private List<String> loreCategory2;
    private String materialCategory3;
    private static String nameCategory3;
    private static String colorCategory3;
    private List<String> loreCategory3;

    private String materialCategory4;
    private static String nameCategory4;
    private static String colorCategory4;
    private List<String> loreCategory4;

    private String materialCategory5;
    private static String nameCategory5;
    private static String colorCategory5;
    private List<String> loreCategory5;

    private String materialCategory6;
    private static String nameCategory6;
    private static String colorCategory6;
    private List<String> loreCategory6;
    ///
    private List<Integer> categorysCost;
    private ArrayList<List<String>> finallistlore = new ArrayList<>();
    private ArrayList<String> finallistnames = new ArrayList<>();
    private ArrayList<String> finallistmaterials = new ArrayList<>();

    ///
    private HashMap<String, String> category1ListEnchants = null;
    private HashMap<String, String> category2ListEnchants = null;
    private HashMap<String, String> category3ListEnchants = null;
    private HashMap<String, String> category4ListEnchants = null;
    private HashMap<String, String> category5ListEnchants = null;
    private HashMap<String, String> category6ListEnchants = null;

    public CustomCategory(EdgeEnchants plugin){
        this.plugin = plugin;
        configcrudo = new ConfigCrudo("categorys.yml", null, plugin);
        configcrudo.registerConfig();
        loadConfig();
    }

    public ItemStack OneItemCategory(){
        if (Material.getMaterial(materialCategory1) == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(prefix+" &eCategory 1 material is null, check categorys.yml"));
            return null;
        }
        Material material = Material.getMaterial(materialCategory1);
        ItemStack itemCategoryDisplay = new ItemStack(material, 1);
        ItemMeta meta = itemCategoryDisplay.getItemMeta();
        String display1NameCategory = Message.getTranslate(colorCategory1+nameCategory1);
        if (display1NameCategory.equalsIgnoreCase("")){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(prefix+" &eCategory 1 name is null, check categorys.yml"));
            return null;
        }
        if (loreCategory1.isEmpty() || loreCategory1 == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(prefix+" &eCategory 1 lore is null, check categorys.yml"));
            return null;
        }
        for (int i = 0; i < loreCategory1.size(); i++) {
            String line = loreCategory1.get(i);
            loreCategory1.set(i, Message.getTranslate(line));
        }
        meta.setDisplayName(display1NameCategory);
        meta.setLore(loreCategory1);

        itemCategoryDisplay.setItemMeta(meta);

        return itemCategoryDisplay;
    }

    public static String getCategoryColor(String enchantCategory){
        if (enchantCategory.equalsIgnoreCase(nameCategory1)){
            return colorCategory1;
        } else if (enchantCategory.equalsIgnoreCase(nameCategory2)) {
            return colorCategory2;
        } else if (enchantCategory.equalsIgnoreCase(nameCategory3)) {
            return colorCategory3;
        } else if (enchantCategory.equalsIgnoreCase(nameCategory4)) {
            return colorCategory4;
        } else if (enchantCategory.equalsIgnoreCase(nameCategory5)) {
            return colorCategory5;
        } else if (enchantCategory.equalsIgnoreCase(nameCategory6)) {
            return colorCategory6;
        }
        return null;
    }
    public ItemStack TwoItemCategory(){
        if (Material.getMaterial(materialCategory2) == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(prefix+" &eCategory 2 material is null, check categorys.yml"));
            return null;
        }
        Material material = Material.getMaterial(materialCategory2);
        ItemStack itemCategoryDisplay = new ItemStack(material, 1);
        ItemMeta meta = itemCategoryDisplay.getItemMeta();
        String display1NameCategory = Message.getTranslate(colorCategory2+nameCategory2);
        if (display1NameCategory.equalsIgnoreCase("")){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(prefix+" &eCategory 2 name is null, check categorys.yml"));
            return null;
        }
        if (loreCategory2.isEmpty() || loreCategory2 == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(prefix+" &eCategory 2 lore is null, check categorys.yml"));
            return null;
        }
        for (int i = 0; i < loreCategory2.size(); i++) {
            String line = loreCategory2.get(i);
            loreCategory2.set(i, Message.getTranslate(line));
        }
        meta.setDisplayName(display1NameCategory);
        meta.setLore(loreCategory2);


        itemCategoryDisplay.setItemMeta(meta);

        return itemCategoryDisplay;
    }
    public ItemStack TreeItemCategory(){
        if (Material.getMaterial(materialCategory3) == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(prefix+" &eCategory 3 material is null, check categorys.yml"));
            return null;
        }
        Material material = Material.getMaterial(materialCategory3);
        ItemStack itemCategoryDisplay = new ItemStack(material, 1);
        ItemMeta meta = itemCategoryDisplay.getItemMeta();
        String display1NameCategory = Message.getTranslate(colorCategory3+nameCategory3);
        if (display1NameCategory.equalsIgnoreCase("")){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(prefix+" &eCategory 3 name is null, check categorys.yml"));
            return null;
        }
        if (loreCategory3.isEmpty() || loreCategory3 == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(prefix+" &eCategory 3 lore is null, check categorys.yml"));
            return null;
        }
        for (int i = 0; i < loreCategory3.size(); i++) {
            String line = loreCategory3.get(i);
            loreCategory3.set(i, Message.getTranslate(line));
        }
        meta.setDisplayName(display1NameCategory);
        meta.setLore(loreCategory3);


        itemCategoryDisplay.setItemMeta(meta);

        return itemCategoryDisplay;
    }
    public ItemStack FortItemCategory(){
        if (Material.getMaterial(materialCategory4) == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(prefix+" &eCategory 4 material is null, check categorys.yml"));
            return null;
        }
        Material material = Material.getMaterial(materialCategory4);
        ItemStack itemCategoryDisplay = new ItemStack(material, 1);
        ItemMeta meta = itemCategoryDisplay.getItemMeta();
        String display1NameCategory = Message.getTranslate(colorCategory4+nameCategory4);
        if (display1NameCategory.equalsIgnoreCase("")){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(prefix+" &eCategory 4 name is null, check categorys.yml"));
            return null;
        }
        if (loreCategory4.isEmpty() || loreCategory4 == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(prefix+" &eCategory 4 lore is null, check categorys.yml"));
            return null;
        }
        for (int i = 0; i < loreCategory4.size(); i++) {
            String line = loreCategory4.get(i);
            loreCategory4.set(i, Message.getTranslate(line));
        }
        meta.setDisplayName(display1NameCategory);
        meta.setLore(loreCategory4);


        itemCategoryDisplay.setItemMeta(meta);

        return itemCategoryDisplay;
    }
    public ItemStack FivtiItemCategory(){
        if (Material.getMaterial(materialCategory5) == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(prefix+" &eCategory 5 material is null, check categorys.yml"));
            return null;
        }
        Material material = Material.getMaterial(materialCategory5);
        ItemStack itemCategoryDisplay = new ItemStack(material, 1);
        ItemMeta meta = itemCategoryDisplay.getItemMeta();
        String display1NameCategory = Message.getTranslate(colorCategory5+nameCategory5);
        if (display1NameCategory.equalsIgnoreCase("")){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(prefix+" &eCategory 5 name is null, check categorys.yml"));
            return null;
        }
        if (loreCategory5.isEmpty() || loreCategory5 == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(prefix+" &eCategory 5 lore is null, check categorys.yml"));
            return null;
        }
        for (int i = 0; i < loreCategory5.size(); i++) {
            String line = loreCategory5.get(i);
            loreCategory5.set(i, Message.getTranslate(line));
        }
        meta.setDisplayName(display1NameCategory);
        meta.setLore(loreCategory5);


        itemCategoryDisplay.setItemMeta(meta);

        return itemCategoryDisplay;
    }
    public ItemStack SixItemCategory(){
        if (Material.getMaterial(materialCategory6) == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(prefix+" &eCategory 6 material is null, check categorys.yml"));
            return null;
        }
        Material material = Material.getMaterial(materialCategory6);
        ItemStack itemCategoryDisplay = new ItemStack(material, 1);
        ItemMeta meta = itemCategoryDisplay.getItemMeta();
        String display1NameCategory = Message.getTranslate(colorCategory6+nameCategory6);
        if (display1NameCategory.equalsIgnoreCase("")){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(prefix+" &eCategory 6 name is null, check categorys.yml"));
            return null;
        }
        if (loreCategory6.isEmpty() || loreCategory6 == null){
            Bukkit.getConsoleSender().sendMessage(Message.getTranslate(prefix+" &eCategory 6 lore is null, check categorys.yml"));
            return null;
        }
        for (int i = 0; i < loreCategory6.size(); i++) {
            String line = loreCategory6.get(i);
            loreCategory6.set(i, Message.getTranslate(line));
        }
        meta.setDisplayName(display1NameCategory);
        meta.setLore(loreCategory6);


        itemCategoryDisplay.setItemMeta(meta);

        return itemCategoryDisplay;
    }

    public ItemStack get1ItemCategory(){
        return OneItemCategory();
    }
    public ItemStack get2ItemCategory(){
        return TwoItemCategory();
    }
    public ItemStack get3ItemCategory(){
        return TreeItemCategory();
    }
    public ItemStack get4ItemCategory(){
        return FortItemCategory();
    }
    public ItemStack get5ItemCategory(){
        return FivtiItemCategory();
    }
    public ItemStack get6ItemCategory(){
        return SixItemCategory();
    }
    public ArrayList listCategorys = null;

    public ArrayList getListCategorys() {
        return listCategorys;
    }

    public void reloadConfig(){
        configcrudo.reloadConfig();
        loadConfig();
        loadListCategorys();
    }


    public void loadListCategorys() {
        FileConfiguration config = configcrudo.getConfig();
        ArrayList<String> listcategorys = new ArrayList<>();
        ArrayList<Integer> listofcost = new ArrayList<>();
        ArrayList<String> listnames = new ArrayList<>();
        ArrayList<String> listmaterials = new ArrayList<>();
        ArrayList<List<String>> listlore = new ArrayList<>();
        for (String path : config.getKeys(false)) {
            if (!(listcategorys.contains(config.getString(path+".category_name")))) {
                listcategorys.add(Message.getTranslate(config.getString(path + ".category_name")));
            }else{
                Bukkit.getConsoleSender().sendMessage(Message.getTranslate(prefix+" &cError. Category &4"+config.getString(path+".category_name")+" &cit's duplicated"));
            }
            listofcost.add(config.getInt(path+".cost-of-category"));
            listnames.add(config.getString(path+".rune.display-name"));
            listmaterials.add(config.getString(path+".rune.material"));
            listlore.add(config.getStringList(path+".rune.lore"));
        }
        categorysCost = listofcost;
        listCategorys = listcategorys;
        finallistlore = listlore;
        finallistmaterials = listmaterials;
        finallistnames = listnames;
        Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &aloading "+listcategorys.size()+" &acategorys:"));
        Bukkit.getConsoleSender().sendMessage(Message.getTranslate("&e"+listcategorys));
    }
    public void loadConfig(){
        FileConfiguration config = configcrudo.getConfig();
        materialCategory1 = config.getString("category1.material_display");
        nameCategory1 = config.getString("category1.category_name");
        colorCategory1 = config.getString("category1.category_color");
        loreCategory1 = config.getStringList("category1.category_itemlore");
        //
        materialCategory2 = config.getString("category2.material_display");
        nameCategory2 = config.getString("category2.category_name");
        colorCategory2 = config.getString("category2.category_color");
        loreCategory2 = config.getStringList("category2.category_itemlore");
        //
        materialCategory3 = config.getString("category3.material_display");
        nameCategory3 = config.getString("category3.category_name");
        colorCategory3 = config.getString("category3.category_color");
        loreCategory3 = config.getStringList("category3.category_itemlore");
        //
        materialCategory4 = config.getString("category4.material_display");
        nameCategory4 = config.getString("category4.category_name");
        colorCategory4 = config.getString("category4.category_color");
        loreCategory4 = config.getStringList("category4.category_itemlore");
        //
        materialCategory5 = config.getString("category5.material_display");
        nameCategory5 = config.getString("category5.category_name");
        colorCategory5 = config.getString("category5.category_color");
        loreCategory5 = config.getStringList("category5.category_itemlore");
        //
        materialCategory6 = config.getString("category6.material_display");
        nameCategory6 = config.getString("category6.category_name");
        colorCategory6 = config.getString("category6.category_color");
        loreCategory6 = config.getStringList("category6.category_itemlore");
    }

    public String getNameCategory1() {
        return nameCategory1;
    }
    public String getNameCategory2() {
        return nameCategory2;
    }
    public String getNameCategory3() {
        return nameCategory3;
    }
    public String getNameCategory4() {
        return nameCategory4;
    }
    public String getNameCategory5() {
        return nameCategory5;
    }
    public String getNameCategory6() {
        return nameCategory6;
    }

    public ArrayList<String> getFinallistmaterials() {
        return finallistmaterials;
    }

    public ArrayList<String> getFinallistnames() {
        return finallistnames;
    }

    public ArrayList<List<String>> getFinallistlore() {
        return finallistlore;
    }

    public List<Integer> getCategorysCost() {return categorysCost;}
}