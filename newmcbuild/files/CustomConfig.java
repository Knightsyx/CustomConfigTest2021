package me.knightsy.newmcbuild.files;

import me.knightsy.newmcbuild.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.logging.Level;

public class CustomConfig {

    private final Main plugin;

    public CustomConfig(Main plugin){
        this.plugin = plugin;
        saveDefaultConfig();
    }

    private FileConfiguration customConfig = null;
    private File customConfigFile = null;


    public void reloadConfig(){
        if(customConfigFile == null){
            this.customConfigFile = new File(plugin.getDataFolder(), "data.yml");
        }
        this.customConfig = YamlConfiguration.loadConfiguration(this.customConfigFile);

        InputStream defaultStream = plugin.getResource("data.yml");
        if(defaultStream != null){
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.customConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig(){
        if(customConfig == null){
            reloadConfig();
        }
        return this.customConfig;
    }

    public void saveConfig(){
        if(this.customConfig == null || this.customConfigFile == null){
            return;
        }
        try {
            getConfig().save(customConfigFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to" + customConfigFile, e);
        }
    }

    public void saveDefaultConfig() {
        if (this.customConfigFile == null) {
            this.customConfigFile = new File(plugin.getDataFolder(), "data.yml");
        }
        if (!this.customConfigFile.exists()) {
            plugin.saveResource("data.yml", false);
        }
    }

}
