package org.randomlima.lotracesv2.Managers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.randomlima.lotracesv2.LOTRacesV2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ODSManager {
    private final LOTRacesV2 plugin;
    private File originsFile;
    private FileConfiguration originsConfig;

    public ODSManager(LOTRacesV2 plugin) {
        this.plugin = plugin;
        loadOriginsFile();
    }

    private void loadOriginsFile() {
        originsFile = new File(plugin.getDataFolder(), "origins.yml");
        if (!originsFile.exists()) {
            plugin.saveResource("origins.yml", false);
        }
        originsConfig = YamlConfiguration.loadConfiguration(originsFile);
    }

    private void saveOriginsFile() {
        try {
            originsConfig.save(originsFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save origins.yml! Error: " + e.getMessage());
            plugin.getLogger().severe("Stack trace:");
            for (StackTraceElement element : e.getStackTrace()) {
                plugin.getLogger().severe("  at " + element.toString());
            }
        }
    }

    public String getName(String originKey) {
        try {
            return originsConfig.getString("origins." + originKey + ".name", "defaultOrigin");
        } catch (Exception e){
            Bukkit.getLogger().log(Level.SEVERE, "Failed to retrieve name for origin: " + originKey, e);
            return "defaultOrigin";
        }
    }

    public double getDouble(String originKey, String attribute) {
        try {
            return originsConfig.getDouble("origins." + originKey + "." + attribute, 0.0);
        } catch (Exception e){
            Bukkit.getLogger().log(Level.SEVERE, "Failed to retrieve double attribute for origin: " + originKey + " and attribute: " + attribute, e);
            return 0.0;
        }
    }
    public List<String> getOrigins() {
        return new ArrayList<>(originsConfig.getConfigurationSection("origins").getKeys(false));
    }

}
