package org.EdgePlugins.Configs;

import org.EdgePlugins.EdgeEnchants;
import org.EdgePlugins.Utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ConfigCustom {
    private ConfigCrudo configcrudo;
    private EdgeEnchants plugin;

    private String getMaterialRandomEnchant;
    private Boolean getEnableBooleanRandomEnchant;
    private String getNameDisplayRandomEnchant;

    private String getMaterialPasalEnchants;
    private Boolean getEnableBooleanPasalEnchants;
    private String getNameDisplayPasalEnchants;

    private String getMaterialfillItem;
    private String getNameDisplayfillItem;
    private String getMaterialreamening;
    private String getNameDisplayreamening;
    private String getMaterialNoMoreEnchants;
    private String getNameDisplayNoMoreEnchants;
    private String getMaterialBotton1;
    private String getNameDisplayBotton1;
    private String getMaterialBotton2;
    private String getNameDisplayBotton2;

    private String getMaterialRune;
    private String getNameDisplayRune;
    private List<String> LoreDisplayRune;

    private Integer categoryTemporaryCost;

    //messaged
    private String noXpPlayer;

    private String messageCostOfCategoryTemporary;

    private String won5050;
    private String lost5050;
    private Boolean lostorwonmessageBoolean;
    private String MaterialOfBook;
    private List<String> loreBotton1;
    private String isNotApplicable;

    public ConfigCustom(EdgeEnchants plugin){
        this.plugin = plugin;
        configcrudo = new ConfigCrudo("config.yml", null, plugin);
        configcrudo.registerConfig();
        loadConfig();
    }

    public void reloadConfig(){
        configcrudo.reloadConfig();
        loadConfig();
    }

    public void loadConfig(){
        FileConfiguration config = configcrudo.getConfig();
        getMaterialRandomEnchant = config.getString("random-enchant-category.item-display");
        getEnableBooleanRandomEnchant = true;
        getNameDisplayRandomEnchant = config.getString("random-enchant-category.name-display");

        getMaterialPasalEnchants = config.getString("temporary-enchant-category.item-display");
        getEnableBooleanPasalEnchants = config.getBoolean("temporary-enchant-category.enable");
        getNameDisplayPasalEnchants = config.getString("temporary-enchant-category.name-display");

        getNameDisplayfillItem = config.getString("filler-article.name-display");
        getMaterialfillItem = config.getString("filler-article.item-display");

        getNameDisplayreamening = config.getString("temporary-temporizer-item.name-display");
        getMaterialreamening = config.getString("temporary-temporizer-item.item-display");

        getNameDisplayNoMoreEnchants = config.getString("no-more-items-temporary-item.name-display");
        getMaterialNoMoreEnchants = config.getString("no-more-items-temporary-item.item-display");

        getNameDisplayBotton1 = config.getString("temporary-claim-item-1.name-display");
        getMaterialBotton1 = config.getString("temporary-claim-item-1.item-display");

        getNameDisplayBotton2 = config.getString("temporary-claim-item-2.name-display");
        getMaterialBotton2 = config.getString("temporary-claim-item-2.item-display");

        getNameDisplayRune = config.getString("runes.temporary-rune.name-display");
        getMaterialRune = config.getString("runes.temporary-rune.item-display");
        LoreDisplayRune = config.getStringList("runes.temporary-rune.lore-display");

        getNameDisplayBotton2 = config.getString("temporary-claim-item-2.name-display");
        getMaterialBotton2 = config.getString("temporary-claim-item-2.item-display");

        categoryTemporaryCost = config.getInt("temporary-enchant-category.cost-of-category");

        noXpPlayer = config.getString("messages.player-no-xp");

        messageCostOfCategoryTemporary = config.getString("temporary-claim-item-2.cost-lore-message");

        won5050 = config.getString("messages.rune-temporary");
        lost5050 = config.getString("messages.rune-temporary");
        lostorwonmessageBoolean = config.getBoolean("temporary-enchant-category.won-or-lose-message");

        MaterialOfBook = config.getString("enchant-item.item-display");

        loreBotton1 = config.getStringList("temporary-claim-item-1.lore");

        isNotApplicable = config.getString("messages.cannot-be-applied");
    }

    public String getGetMaterialRandomEnchant() {
        return getMaterialRandomEnchant;
    }

    public Boolean getGetEnableBooleanRandomEnchant() {
        return getEnableBooleanRandomEnchant;
    }

    public String getGetNameDisplayRandomEnchant() {
        return getNameDisplayRandomEnchant;
    }

    public String getGetNameDisplayPasalEnchants() {
        return getNameDisplayPasalEnchants;
    }

    public String getGetMaterialPasalEnchants() {
        return getMaterialPasalEnchants;
    }

    public Boolean getGetEnableBooleanPasalEnchants() {
        return getEnableBooleanPasalEnchants;
    }

    public String getGetNameDisplayfillItem() {
        return getNameDisplayfillItem;
    }

    public String getGetMaterialfillItem() {
        return getMaterialfillItem;
    }

    public String getGetNameDisplayreamening() {
        return getNameDisplayreamening;
    }

    public String getGetMaterialreamening() {
        return getMaterialreamening;
    }

    public String getGetNameDisplayNoMoreEnchants() {
        return getNameDisplayNoMoreEnchants;
    }

    public String getGetMaterialNoMoreEnchants() {
        return getMaterialNoMoreEnchants;
    }

    public String getGetNameDisplayBotton2() {
        return getNameDisplayBotton2;
    }

    public String getGetMaterialBotton2() {
        return getMaterialBotton2;
    }

    public String getGetNameDisplayBotton1() {
        return getNameDisplayBotton1;
    }

    public String getGetMaterialBotton1() {
        return getMaterialBotton1;
    }

    public String getGetNameDisplayRune() {
        return getNameDisplayRune;
    }

    public String getGetMaterialRune() {
        return getMaterialRune;
    }

    public List<String> getLoreDisplayRune() {
        return LoreDisplayRune;
    }
    public Integer getCategoryTemporaryCost() {
        return categoryTemporaryCost;
    }
    public String getNoXpPlayer() {
        return noXpPlayer;
    }
    public String getMessageCostOfCategoryTemporary() {
        return messageCostOfCategoryTemporary;
    }

    public String getLost5050() {
        return lost5050;
    }

    public String getWon5050() {
        return won5050;
    }

    public Boolean getLostorwonmessageBoolean() {
        return lostorwonmessageBoolean;
    }

    public String getMaterialOfBook() {
        return MaterialOfBook;
    }

    public List<String> getLoreBotton1() {
        return loreBotton1;
    }

    public String getIsNotApplicable() {
        return isNotApplicable;
    }
}

