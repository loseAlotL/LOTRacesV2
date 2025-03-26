package org.randomlima.lotracesv2.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.randomlima.lotracesv2.LOTRacesV2;
import org.randomlima.lotracesv2.Managers.ODSManager;
import org.randomlima.lotracesv2.Origin;
import org.randomlima.lotracesv2.Utils.Colorize;
import org.randomlima.lotracesv2.Utils.MessageUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetOriginCommand implements CommandExecutor {
    private final LOTRacesV2 plugin;
    private final ODSManager odsManager;

    public SetOriginCommand(LOTRacesV2 plugin, ODSManager odsManager) {
        this.plugin = plugin;
        this.odsManager = odsManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(Colorize.format(MessageUtil.header + "&cUsage: /setorigin <player> <origin>"));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(Colorize.format(MessageUtil.header + MessageUtil.error + "&4Player not found"));
            return true;
        }

        String origin = args[1];

        List<String> validOrigins = odsManager.getOrigins();
        if(validOrigins.contains(origin)){
            Origin newOrigin = new Origin(plugin, origin);
            target.sendMessage(Colorize.format(MessageUtil.header + "Your origin has been set to: "+ origin));
            newOrigin.applyPlayer(target);
        } else {
            target.sendMessage(Colorize.format(MessageUtil.header + MessageUtil.error+origin+" &cis not a valid origin. See &4origins.yml &cfor valid origins."));
        }

        return true;
    }
}