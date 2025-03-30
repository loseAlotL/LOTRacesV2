package org.randomlima.lotracesv2.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedMainHandEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.randomlima.lotracesv2.LOTRacesV2;
import org.randomlima.lotracesv2.Managers.ODSManager;
import org.randomlima.lotracesv2.Managers.OriginPlayerManager;
import org.randomlima.lotracesv2.Origin;

import java.util.List;

public class ItemSwapListener implements Listener {
    private final LOTRacesV2 plugin;
    private ODSManager odsManager;
    private OriginPlayerManager originPlayerManager;
    private List<String> rings;
    public ItemSwapListener(LOTRacesV2 plugin){
        this.plugin = plugin;
        odsManager = new ODSManager(plugin);
        originPlayerManager = new OriginPlayerManager(plugin);
    }
    @EventHandler
    public void onItemSwap(PlayerItemHeldEvent event){
        Player player = event.getPlayer();
        if(!originPlayerManager.hasOrigin(player))return;
        Origin origin = originPlayerManager.getPlayerOrigin(player);

        rings = odsManager.getRings(origin.getname());

        ItemStack mainHand = player.getInventory().getItem(event.getNewSlot());
        ItemStack offHand = player.getInventory().getItemInOffHand();

        boolean hasRing = false;

        for (String ringName : rings) {
            if (mainHand.hasItemMeta() && mainHand.getItemMeta().hasDisplayName() &&
                    mainHand.getItemMeta().getDisplayName().equals(ringName)) {
                hasRing = true;
                break;
            }
            if (offHand.hasItemMeta() && offHand.getItemMeta().hasDisplayName() &&
                    offHand.getItemMeta().getDisplayName().equals(ringName)) {
                hasRing = true;
                break;
            }
        }

        if (hasRing) {
            origin.applyPlayer(player);  // Apply the "buff"
            player.sendMessage("BUFF");
        } else {
            origin.applyPlayerDebuff(player);  // Apply the "debuff"
            player.sendMessage("DEBUFF");
        }
    }
}
