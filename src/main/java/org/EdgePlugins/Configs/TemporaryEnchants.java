package org.EdgePlugins.Configs;

import org.EdgePlugins.EdgeEnchants;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class TemporaryEnchants {
    private ConfigCrudo configcrudo;
    private EdgeEnchants plugin;
    private static int TimeTemporary;

    private static List<String> getTemporaryEnchantList;
    private int getPercentToFall;
    private List<String> BlackListOfEnchants;

    public TemporaryEnchants(EdgeEnchants plugin) {
        this.plugin = plugin;
        configcrudo = new ConfigCrudo("temporary-enchants.yml", null, plugin);
        configcrudo.registerConfig();
        loadConfig();
    }

    public void reloadConfig() {
        configcrudo.reloadConfig();
        loadConfig();
    }

    public void loadConfig() {
        FileConfiguration config = configcrudo.getConfig();
        getTemporaryEnchantList = config.getStringList("enchant-list");
        getPercentToFall = config.getInt("percent-to-fall");
        TimeTemporary = config.getInt("time");
        BlackListOfEnchants = config.getStringList("black-list-of-enchants");
    }

    public int getGetPercentToFall() {
        return getPercentToFall;
    }

    public List<String> getGetTemporaryEnchantList() {
        return getTemporaryEnchantList;
    }

    public static void onTerminateTime(){
        getTemporaryEnchantList = null;
    }
    public int getTimeTemporary() {
        return TimeTemporary;
    }

    public List<String> getBlackListOfEnchants() {
        return BlackListOfEnchants;
    }
}