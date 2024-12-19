package org.EdgePlugins.Listeners.Active;

import org.EdgePlugins.Cooldowns.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class Verification implements Listener {
   @EventHandler
    public void onJoinPlayer(PlayerJoinEvent event){
       Player generalPlayer = event.getPlayer();
       generalPlayer.getPersistentDataContainer().set(Main.FINAL_LIST_CD_MAINHAND_CD, PersistentDataType.STRING, "");
   }

}
