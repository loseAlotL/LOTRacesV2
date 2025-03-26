package org.randomlima.lotracesv2.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.randomlima.lotracesv2.Managers.ODSManager;

import java.util.ArrayList;
import java.util.List;

public class SetOriginTabCompleter implements TabCompleter {
    private final ODSManager odsManager;

    public SetOriginTabCompleter(ODSManager odsManager) {
        this.odsManager = odsManager;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            for (Player player : sender.getServer().getOnlinePlayers()) {
                completions.add(player.getName());
            }
        } else if (args.length == 2) {
            completions.addAll(odsManager.getOrigins());
        }

        return completions;
    }
}
