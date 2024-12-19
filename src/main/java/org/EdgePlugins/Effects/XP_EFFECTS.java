package org.EdgePlugins.Effects;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;


public class XP_EFFECTS {
    public static void remove_xp(LivingEntity player, int xptoRemove){
        if (player instanceof Player) {
            Player player1 = (Player) player;
            player1.giveExp(-xptoRemove);
        }
    }
    public static void add_xp(LivingEntity player, int xptoAdd){
        if (player instanceof Player) {
            Player player1 = (Player) player;
            player1.giveExp(xptoAdd);
        }
    }
    public static void add_xp_lvl(LivingEntity player, int lvl){
        if (player instanceof Player) {
            Player player1 = (Player) player;
            player1.setLevel(player1.getLevel() + lvl);
        }
    }
    public static void remove_xp_lvl(LivingEntity player, int lvl){
        if (player instanceof Player) {
            Player player1 = (Player) player;

            int newLevel = player1.getLevel() - lvl;
            if (newLevel <= 0) {
                player1.setLevel(0);
            } else {
                player1.setLevel(newLevel);
            }
        }
    }
}
