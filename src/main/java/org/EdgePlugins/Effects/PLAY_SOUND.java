package org.EdgePlugins.Effects;

import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class PLAY_SOUND {
    public static void playSound(String sound, LivingEntity player, int volume, int pitch){
        if (player instanceof Player) {
            Player player1 = (Player) player;
            player1.playSound(player.getLocation(), Sound.valueOf(sound), volume, pitch);
        }
    }

}
