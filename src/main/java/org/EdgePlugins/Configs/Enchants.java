package org.EdgePlugins.Configs;

import org.EdgePlugins.EdgeEnchants;
import org.EdgePlugins.Utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class Enchants {
    private ConfigCrudo configCrudo;
    private EdgeEnchants plugin;
    public static List<String> enchantlist = null;
    public static HashMap<String, String> enchantListCategory = null;
    public static HashMap<String, Integer> enchantListCooldown;

    public Enchants(EdgeEnchants plugin) {
        this.plugin = plugin;
        configCrudo = new ConfigCrudo("enchants.yml", null, plugin);
        configCrudo.registerConfig();
        loadConfig();
    }

    public void reloadConfig() {
        configCrudo.reloadConfig();
        loadConfig();
        loadList();
        fullListEnchants();
        refullListCategorys();
        refullLoreAllEnchants();
        refullSkillEnchant();
        getEnchantAppicable();
    }

    public static HashMap<String, String> getMapEnchantsApplicable() {
        return mapEnchantsApplicable;
    }

    public void loadConfig() {
        FileConfiguration config = configCrudo.getConfig();
    }

    public void loadList() {
        FileConfiguration config = configCrudo.getConfig();
        ArrayList<String> listenchants = new ArrayList<>();
        for (String path : config.getKeys(false)) {
            listenchants.add(Message.getTranslate(path));
        }
        enchantlist = listenchants;

    }

    public void fullListEnchants() {
        FileConfiguration config = configCrudo.getConfig();
        HashMap<String, String> finalList = new HashMap<>();
        for (String path : config.getKeys(false)) {
            String category = config.getString(path + ".category");
            if (plugin.getCustomCategory().listCategorys.contains(category)) {
                finalList.put(path, category);
            } else {
                Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin + " &c" + path + " enchant category name is invalid, &4" + category));

                return;
            }
        }
        enchantListCategory = finalList;
    }

    public List<String> Category1List;
    public List<String> Category2List;
    public List<String> Category3List;
    public List<String> Category4List;
    public List<String> Category5List;
    public List<String> Category6List;

    public List<String> getCategory1List() {
        return Category1List;
    }

    public List<String> getCategory2List() {
        return Category2List;
    }

    public List<String> getCategory3List() {
        return Category3List;
    }

    public List<String> getCategory4List() {
        return Category4List;
    }

    public List<String> getCategory5List() {
        return Category5List;
    }

    public List<String> getCategory6List() {
        return Category6List;
    }

    public static ArrayList<List<List<String>>> arrayList = new ArrayList<>();
    public static List<List<String>> Cooldowns = new ArrayList<>();
    public static List<List<String>> Chances = new ArrayList<>();
    public static List<List<String>> Types = new ArrayList<>();
    public static List<List<List<String>>> Effects = new ArrayList<>();

    public void refullSkillEnchant() {
        FileConfiguration config = configCrudo.getConfig();
        List<List<String>> cooldownsfinal = new ArrayList<>();
        List<List<String>> chancesfinal = new ArrayList<>();
        List<List<String>> typesfinal = new ArrayList<>();
        List<List<List<String>>> effectsfinal = new ArrayList<>();
        for (String nameEnchant : enchantlist) {
            List<Map<?, ?>> skillsList = config.getMapList(nameEnchant + ".skills");
            List<String> cooldowns = new ArrayList<>();
            List<String> chances = new ArrayList<>();
            List<String> type = new ArrayList<>();
            List<List<String>> effects = new ArrayList<>();
            ArrayList<String> name = new ArrayList<>();
            name.add(nameEnchant);
            effects.add(name);
            chances.add(nameEnchant);
            cooldowns.add(nameEnchant);
            type.add(nameEnchant);
            for (Map<?, ?> skillMap : skillsList) {
                for (Object key : skillMap.keySet()) {
                    Object value = skillMap.get(key);
                    if (key.equals("cooldown")) {
                        if (value instanceof Integer) {
                            cooldowns.add(String.valueOf(value));
                        }
                    } else if (key.equals("chance")) {
                        if (value instanceof Integer) {
                            chances.add(String.valueOf(value));
                        }
                    } else if (key.equals("effects")) {
                        effects.add((List<String>) value);
                    }
                }
            }
            cooldownsfinal.add(cooldowns);
            chancesfinal.add(chances);
            effectsfinal.add(effects);
        }
        Cooldowns = cooldownsfinal;
        Chances = chancesfinal;
        Effects = effectsfinal;
    }


    public static double getEnchantChance(String nameEnchant, int level) {
        for (List<String> enchantCooldownList : Chances){
            if (enchantCooldownList.get(0).equalsIgnoreCase(nameEnchant)){
                int e = Integer.parseInt(enchantCooldownList.get(level));
                return e;
            }
        }
        return 0;
    }
    public static List<String> getEnchantEffects(String nameEnchant, int level) {
        for (List<List<String>> enchantCooldownList : Effects){
            if (enchantCooldownList.get(0).get(0).equalsIgnoreCase(nameEnchant)){
                if (enchantCooldownList.size()-1 >= level) {
                    List<String> e = enchantCooldownList.get(level);
                    return e;
                }else{
                    Bukkit.getLogger().log(Level.WARNING,"The effect in "+enchantCooldownList.get(0).get(0)+" (lvl "+level+") not exist or is null! " + enchantCooldownList);
                }
            }
        }
        List<String> nuller = new ArrayList<>();
        return nuller;
    }
    public static int getEnchantCd(String nameEnchant, Integer level){
        for (List<String> enchantCooldownList : Cooldowns){
            if (enchantCooldownList.get(0).equalsIgnoreCase(nameEnchant)){
                try {
                    int e = Integer.parseInt(enchantCooldownList.get(level));
                    return e;
                } catch (NumberFormatException ex) {
                    return 0;
                }
            }
        }
        return 0;
    }
    public static int getEnchantMaxLevels(String nameEnchant){
        Bukkit.getConsoleSender().sendMessage(Cooldowns.toString());
        for (List<String> enchantCooldownList : Cooldowns){
            if (enchantCooldownList.get(0).equalsIgnoreCase(nameEnchant)){
                return enchantCooldownList.size()-1;
            }
        }
        return 0;
    }

    public void refullLoreAllEnchants(){
        FileConfiguration config = configCrudo.getConfig();
        ArrayList<List<List<String>>> arrayComun = new ArrayList<>();
        for (int i = 0; i < enchantlist.size(); i++){
            List<List<String>> saveList = new ArrayList<>();
            List<String> simpleStrings = new ArrayList<>();
            String nameEnchant = enchantlist.get(i);
            simpleStrings.add(nameEnchant); // name
            simpleStrings.add(config.getString(nameEnchant+".category")); // category
            simpleStrings.add(config.getString(nameEnchant+".name"));
            simpleStrings.add(config.getString(nameEnchant+".type")); //type
            saveList.add(simpleStrings);
            saveList.add(config.getStringList(nameEnchant+".lore")); //lore
            arrayComun.add(saveList);
        }
        arrayList = arrayComun;
    }
    public static HashMap<String, String> mapEnchantsApplicable = new HashMap<>();
    public void getEnchantAppicable(){
        FileConfiguration config = configCrudo.getConfig();
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < enchantlist.size(); i++){
            String enchantname = enchantlist.get(i);
            String enchantTypes = config.getString(enchantname+".applicable");
            if (!(enchantTypes == null)){
                map.put(enchantname, enchantTypes);
            }else{
                Bukkit.getLogger().log(Level.SEVERE, "[VextraEnchants] The types of applicability could not be found in: "+enchantname);
            }
        }
        mapEnchantsApplicable = map;
    }

    public List<Material> getMaterial(String material, String nameenchant) {
        String[] materials = material.split(",");
        List<Material> materialsList = new ArrayList<>();
        for (String materialN : materials) {
            if (materialN.equalsIgnoreCase("swords")) {
                materialsList.add(Material.WOODEN_SWORD);
                materialsList.add(Material.STONE_SWORD);
                materialsList.add(Material.IRON_SWORD);
                materialsList.add(Material.GOLDEN_SWORD);
                materialsList.add(Material.DIAMOND_SWORD);
                materialsList.add(Material.NETHERITE_SWORD);
            } else if (materialN.equalsIgnoreCase("axes")) {
                materialsList.add(Material.WOODEN_AXE);
                materialsList.add(Material.STONE_AXE);
                materialsList.add(Material.GOLDEN_AXE);
                materialsList.add(Material.IRON_AXE);
                materialsList.add(Material.DIAMOND_AXE);
                materialsList.add(Material.NETHERITE_AXE);
            } else if (materialN.equalsIgnoreCase("boots")) {
                materialsList.add(Material.LEATHER_BOOTS);
                materialsList.add(Material.IRON_BOOTS);
                materialsList.add(Material.GOLDEN_BOOTS);
                materialsList.add(Material.CHAINMAIL_BOOTS);
                materialsList.add(Material.DIAMOND_BOOTS);
                materialsList.add(Material.NETHERITE_BOOTS);
            } else if (materialN.equalsIgnoreCase("chestplates")) {
                materialsList.add(Material.LEATHER_CHESTPLATE);
                materialsList.add(Material.IRON_CHESTPLATE);
                materialsList.add(Material.GOLDEN_CHESTPLATE);
                materialsList.add(Material.CHAINMAIL_CHESTPLATE);
                materialsList.add(Material.DIAMOND_CHESTPLATE);
                materialsList.add(Material.NETHERITE_CHESTPLATE);
            } else if (materialN.equalsIgnoreCase("leggings")) {
                materialsList.add(Material.LEATHER_LEGGINGS);
                materialsList.add(Material.IRON_LEGGINGS);
                materialsList.add(Material.GOLDEN_LEGGINGS);
                materialsList.add(Material.CHAINMAIL_LEGGINGS);
                materialsList.add(Material.DIAMOND_LEGGINGS);
                materialsList.add(Material.NETHERITE_LEGGINGS);
            } else if (materialN.equalsIgnoreCase("helmets")) {
                materialsList.add(Material.LEATHER_HELMET);
                materialsList.add(Material.IRON_HELMET);
                materialsList.add(Material.GOLDEN_HELMET);
                materialsList.add(Material.CHAINMAIL_HELMET);
                materialsList.add(Material.DIAMOND_HELMET);
                materialsList.add(Material.NETHERITE_HELMET);
                materialsList.add(Material.TURTLE_HELMET);
            } else if (materialN.equalsIgnoreCase("bow")) {
                materialsList.add(Material.BOW);
            } else if (materialN.equalsIgnoreCase("crossbow")) {
                materialsList.add(Material.CROSSBOW);
            } else if (materialN.equalsIgnoreCase("trident")) {
                materialsList.add(Material.TRIDENT);
            } else if (materialN.equalsIgnoreCase("pickaxes")) {
                materialsList.add(Material.WOODEN_PICKAXE);
                materialsList.add(Material.STONE_PICKAXE);
                materialsList.add(Material.IRON_PICKAXE);
                materialsList.add(Material.GOLDEN_PICKAXE);
                materialsList.add(Material.DIAMOND_PICKAXE);
                materialsList.add(Material.NETHERITE_PICKAXE);
            }else{
                Bukkit.getLogger().log(Level.SEVERE, "[VextraEnchants] There is no applicable type of:"+ materialN+ " in " + nameenchant);
            }
        }
        return materialsList;
    }

    public static List<String> getLoreEnchant(String name){
        for (int i = 0; i < arrayList.size(); i++){
            if (arrayList.get(i).get(0).get(0).equalsIgnoreCase(name)){
                if (arrayList.get(i).get(1) == null){
                    Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. &4"+name+" &cInvalid lore."));
                    return null;
                }
                return arrayList.get(i).get(1);
            }
        }
        Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. &4"+name+" &cInvalid enchant structure."));
        return null;
    }

    //15.5.2024

    public static String getEnchantType(String nameClave){
        for(int i = 0; i < arrayList.size(); i++){
            if (arrayList.get(i).get(0).get(0).equalsIgnoreCase(nameClave)){
                return arrayList.get(i).get(0).get(3);
            }
        }
        Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. &4"+nameClave+" &cInvalid enchant structure. [type of enchant]"));
        return null;
    }
    public void refullListCategorys(){
        List<String> category1 = new ArrayList<>();
        List<String> category2 = new ArrayList<>();
        List<String> category3 = new ArrayList<>();
        List<String> category4 = new ArrayList<>();
        List<String> category5 = new ArrayList<>();
        List<String> category6 = new ArrayList<>();
        for(int i = 0; i < enchantlist.size(); i++){
            String enchantName = enchantlist.get(i);
            if (enchantListCategory.containsKey(enchantName)){
                String category = (String) enchantListCategory.get(enchantName);
                if(category.equalsIgnoreCase(plugin.getCustomCategory().getNameCategory1())){
                    category1.add(enchantName);
                } else if (category.equalsIgnoreCase(plugin.getCustomCategory().getNameCategory2())) {
                    category2.add(enchantName);
                } else if (category.equalsIgnoreCase(plugin.getCustomCategory().getNameCategory3())) {
                    category3.add(enchantName);
                } else if (category.equalsIgnoreCase(plugin.getCustomCategory().getNameCategory4())) {
                    category4.add(enchantName);
                } else if (category.equalsIgnoreCase(plugin.getCustomCategory().getNameCategory5())) {
                    category5.add(enchantName);
                } else if (category.equalsIgnoreCase(plugin.getCustomCategory().getNameCategory6())) {
                    category6.add(enchantName);
                }else{
                    Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin + enchantName + " Is a no valid category, &c"+enchantName));
                }
            } else {
                Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin + enchantName + " Is a no valid enchant, &c"+enchantName));
            }
        }
        Category1List = category1;
        Category2List = category2;
        Category3List = category3;
        Category4List = category4;
        Category5List = category5;
        Category6List = category6;
    }

    /*
    public static String getEnchantCategory(String name){
        for (int i = 0; i < enchantListCategory.size(); i++){
            Bukkit.getConsoleSender().sendMessage(category);
            if (enchantListCategory.get(i) == name ){
                String category = (String) enchantlist.get(i);
                Bukkit.getConsoleSender().sendMessage(category);
                return category;
            }
        }
        Bukkit.getConsoleSender().sendMessage(Message.getTranslate(EdgeEnchants.prefixPlugin+" &cError. "+name+ " is invalid"));
        return null;
    }
     */

    public static String getNameOfEnchant(String nameClave){
        for(int i = 0; i < arrayList.size(); i++){
            if (arrayList.get(i).get(0).get(0).equalsIgnoreCase(nameClave)){
                return arrayList.get(i).get(0).get(2);
            }
        }
        return null;
    }

    public static String getCategoryEnchant(String enchantNameClave){
        for(int i = 0; i < enchantListCategory.size(); i++){
            if(enchantListCategory.containsKey(enchantNameClave)){
                return enchantListCategory.get(enchantNameClave);
            }
        }
        return null;
    }

    public List<String> getEnchantlist() {
        return enchantlist;
    }

    public HashMap getEnchantListCategory() {
        return enchantListCategory;
    }
}
