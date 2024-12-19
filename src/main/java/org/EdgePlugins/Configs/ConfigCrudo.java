package org.EdgePlugins.Configs;

import org.EdgePlugins.EdgeEnchants;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigCrudo {
        private EdgeEnchants plugin;
        private String fileName;
        private FileConfiguration fileConfiguration = null;
        private File file = null;
        private String folderName;

        public ConfigCrudo(String fileName, String folderName, EdgeEnchants plugin){
            this.fileName = fileName;
            this.folderName = folderName;
            this.plugin = plugin;
        }

        public String getPath(){
            return this.fileName;
        }

        public void registerConfig(){
            if(folderName != null){
                file = new File(plugin.getDataFolder() +File.separator + folderName,fileName);
            }else{
                file = new File(plugin.getDataFolder(), fileName);
            }

            if(!file.exists()){
                if(folderName != null){
                    plugin.saveResource(folderName+File.separator+fileName, false);
                }else{
                    plugin.saveResource(fileName, false);
                }
            }

            fileConfiguration = new YamlConfiguration();
            try {
                fileConfiguration.load(file);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }
        public void saveConfig() {
            try {
                fileConfiguration.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public FileConfiguration getConfig() {
            if (fileConfiguration == null) {
                reloadConfig();
            }
            return fileConfiguration;
        }

        public boolean reloadConfig() {
            if (fileConfiguration == null) {
                if(folderName != null){
                    file = new File(plugin.getDataFolder() +File.separator + folderName, fileName);
                }else{
                    file = new File(plugin.getDataFolder(), fileName);
                }

            }
            fileConfiguration = YamlConfiguration.loadConfiguration(file);

            if(file != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(file);
                fileConfiguration.setDefaults(defConfig);
            }
            return true;
        }
    }
