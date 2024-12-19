package org.EdgePlugins.Configs;

import org.EdgePlugins.EdgeEnchants;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class GeneralTime {
    private ConfigCrudo configCrudo;
    private EdgeEnchants plugin;
    public static int RemainingTime;
    public static boolean isTimeCorrect = true;

    public GeneralTime(EdgeEnchants plugin){
        this.plugin = plugin;
        configCrudo = new ConfigCrudo("timedata.yml", null, plugin);
        configCrudo.registerConfig();
        loadConfig();
    }
    public void saveConfig(){
        FileConfiguration config = configCrudo.getConfig();
        int h = RemainingTime / 60;
        int m = RemainingTime % 60;
        int total = RemainingTime;
        config.set("t", total);
        config.set("h", h);
        config.set("m", m);
        configCrudo.saveConfig();
    }
    public void loadConfig() {
        FileConfiguration config = configCrudo.getConfig();
        if (!(config.getInt("t") < 0)){
            RemainingTime = config.getInt("t");
        }else{
            RemainingTime = plugin.getTemporaryEnchants().getTimeTemporary() * 60;
        }
    }


    public static void GeneralTimeMethod(EdgeEnchants plugin){
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            RemainingTime--;
            if (RemainingTime <= 0) {
                TemporaryEnchants.onTerminateTime();
                isTimeCorrect = false;
                Bukkit.getScheduler().cancelTasks(plugin);
            }
        }, 0L, 20L * 60L);
    }


    public static int getRemeningTime(){
        return RemainingTime;
    }
}
