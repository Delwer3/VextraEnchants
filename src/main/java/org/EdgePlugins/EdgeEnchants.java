package org.EdgePlugins;

import org.EdgePlugins.Commands.general;
import org.EdgePlugins.Configs.*;
import org.EdgePlugins.Cooldowns.Main;
import org.EdgePlugins.Cooldowns.PlayerGeneral;
import org.EdgePlugins.Listeners.Active.Verification;
import org.EdgePlugins.Listeners.ApplyEnchantListener;
import org.EdgePlugins.Listeners.GuiListener;
import org.EdgePlugins.Listeners.Items.Runes;
import org.EdgePlugins.Listeners.Triggers.AttackTriggers;
import org.EdgePlugins.Listeners.Triggers.DamageTrigger;
import org.EdgePlugins.Utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.common.returnsreceiver.qual.This;

public class EdgeEnchants extends JavaPlugin {
    public static String prefixPlugin = Message.getTranslate("&8[&dVextra&bEnchants&8]");
    private CustomCategory customCategory;
    private Enchants enchants;
    private TemporaryEnchants temporaryEnchants;
    private GeneralTime generalTime;
    private ConfigCustom configCustom;
    public static Plugin getPlugin() {
        return Bukkit.getServer().getPluginManager().getPlugin("VextraEnchants");
    }


    public void onEnable(){
        customCategory = new CustomCategory(this);
        generalTime = new GeneralTime(this);
        enchants = new Enchants(this);
        configCustom = new ConfigCustom(this);
        temporaryEnchants = new TemporaryEnchants(this);
        registerCommands();
        enableReloads();
        registerEvents();
    }
    public void onDisable(){
        getGeneralTime().saveConfig();
        Bukkit.getConsoleSender().sendMessage(Message.getTranslate("&aSaved data time"));
    }

    public void registerEvents(){
        getServer().getPluginManager().registerEvents(new GuiListener(), this);
        getServer().getPluginManager().registerEvents(new Runes(), this);
        getServer().getPluginManager().registerEvents(new ApplyEnchantListener(), this);
        getServer().getPluginManager().registerEvents(new Main(), this);
        getServer().getPluginManager().registerEvents(new DamageTrigger(), this);
        getServer().getPluginManager().registerEvents(new AttackTriggers(), this);
        getServer().getPluginManager().registerEvents(new PlayerGeneral(), this);
        getServer().getPluginManager().registerEvents(new Verification(), this);
    }
    public void registerCommands(){
        this.getCommand("vextraenchants").setExecutor(new general(this));
        this.getCommand("enchantment").setExecutor(new general(this));
    }
    public CustomCategory getCustomCategory() {
        return customCategory;
    }
    public Enchants getEnchants(){ return enchants;}
    public ConfigCustom getconfigCustom(){ return configCustom;}
    public TemporaryEnchants getTemporaryEnchants(){ return temporaryEnchants;}
    public GeneralTime getGeneralTime(){return generalTime;}
    public void enableReloads(){
        getCustomCategory().reloadConfig();
        getEnchants().reloadConfig();
        getconfigCustom();
        getTemporaryEnchants().reloadConfig();
        GeneralTime.GeneralTimeMethod(this);
    }
}

