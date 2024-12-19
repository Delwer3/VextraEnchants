package org.EdgePlugins.Effects;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class CONSOLE_COMMAND {
    public static void consoleCoomand(String command){
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.getServer().dispatchCommand(console, command);
    }
}
