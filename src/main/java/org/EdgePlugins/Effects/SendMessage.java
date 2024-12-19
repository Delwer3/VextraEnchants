package org.EdgePlugins.Effects;

import org.EdgePlugins.Utils.Message;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class SendMessage {


    public static void sendMessage(String message, LivingEntity player) {
        if (player instanceof Player) {
            Player player1 = (Player) player;
            player1.sendMessage(Message.getTranslate(message));
        }
    }
}
