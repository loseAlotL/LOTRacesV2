package org.randomlima.lotracesv2.Managers;

import org.bukkit.entity.Player;
import org.randomlima.lotracesv2.LOTRacesV2;
import org.randomlima.lotracesv2.Origin;

import java.util.HashMap;
import java.util.Map;

public class OriginPlayerManager {
    private final LOTRacesV2 plugin;
    private ODSManager odsManager;
    private static Map<Player, Origin> originPlayers = new HashMap<Player, Origin>();
    public OriginPlayerManager(LOTRacesV2 plugin){
        this.plugin = plugin;
        odsManager = new ODSManager(plugin);
    }

    public void addPlayer(Player player, Origin origin){
        originPlayers.put(player, origin);
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
