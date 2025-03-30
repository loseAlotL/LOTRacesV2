package org.randomlima.lotracesv2;

import org.bukkit.plugin.java.JavaPlugin;
import org.randomlima.lotracesv2.Commands.SetOriginCommand;
import org.randomlima.lotracesv2.Commands.SetOriginTabCompleter;
import org.randomlima.lotracesv2.Listeners.ItemSwapListener;
import org.randomlima.lotracesv2.Managers.ODSManager;

public final class LOTRacesV2 extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic


        ODSManager odsManager = new ODSManager(this);

        getServer().getPluginManager().registerEvents(new ItemSwapListener(this), this);

        this.getCommand("setorigin").setExecutor(new SetOriginCommand(this, odsManager));
        this.getCommand("setorigin").setTabCompleter(new SetOriginTabCompleter(odsManager));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
