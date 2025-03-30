package org.randomlima.lotracesv2.Managers;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.randomlima.lotracesv2.LOTRacesV2;
import org.randomlima.lotracesv2.Origin;
import org.randomlima.lotracesv2.Utils.OriginKeys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OriginPlayerManager {
    private final LOTRacesV2 plugin;
    private ODSManager odsManager;
    private static NamespacedKey originKey;
    private static Map<Player, Origin> originPlayers = new HashMap<Player, Origin>();
    public OriginPlayerManager(LOTRacesV2 plugin){
        originKey = new NamespacedKey(plugin, "originKey");
        this.plugin = plugin;
        odsManager = new ODSManager(plugin);
    }

    public void loadOrigins(){
        List<Player> players = new ArrayList<>();
        for(Player p : Bukkit.getServer().getOnlinePlayers()){
            if(p.getPersistentDataContainer().has(originKey)) players.add(p);
        }

        for(Player player : players){
            String name = player.getPersistentDataContainer().get(originKey, PersistentDataType.STRING);
            setPlayerOrigin(player, name);
        }
    }

    public void setPlayerOrigin(Player player, String name){
        Origin origin = new Origin(plugin, name);
        origin.applyPlayer(player);
        originPlayers.put(player, origin);

        PersistentDataContainer container = player.getPersistentDataContainer();
        container.set(originKey, PersistentDataType.STRING, origin.getname());
    }

    public boolean hasOrigin(Player player){
        return originPlayers.containsKey(player);
    }
    public Origin getPlayerOrigin(Player player){
        if(hasOrigin(player)){
            return originPlayers.get(player);
        } else {
            return null;
        }
    }
}
