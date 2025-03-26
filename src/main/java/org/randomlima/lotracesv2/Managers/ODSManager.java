package org.randomlima.lotracesv2.Managers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.randomlima.lotracesv2.LOTRacesV2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        return originsConfig.getString("origins." + originKey + ".name", "Default Name");
    }

    public double getDouble(String originKey, String attribute) {
        return originsConfig.getDouble("origins." + originKey + "." + attribute, 0.0);
    }
    public List<String> getOrigins() {
        return new ArrayList<>(originsConfig.getConfigurationSection("origins").getKeys(false));
    }

}
