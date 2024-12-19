package org.EdgePlugins.Utils;

import org.bukkit.ChatColor;

public class Message {
    public static String getTranslate(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
