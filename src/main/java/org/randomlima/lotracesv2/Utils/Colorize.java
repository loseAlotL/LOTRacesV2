package org.randomlima.lotracesv2.Utils;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;

public class Colorize {
    public static @NotNull String format(String str){
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
