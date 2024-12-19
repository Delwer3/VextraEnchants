package org.EdgePlugins.Listeners.Items;

import org.EdgePlugins.EdgeEnchants;
import org.EdgePlugins.Listeners.Items.ActionsItems.RuneDel;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import static org.EdgePlugins.ItemsHandler.Runes.CUSTOM_RUNE_TEMPORARY;
import static org.EdgePlugins.ItemsHandler.Runes.CUSTOM_RUNE;
public class Runes implements Listener {
    @EventHandler
    public void onClickWithItem(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta.getPersistentDataContainer().has(CUSTOM_RUNE_TEMPORARY)) {
                RuneDel.delItem(item);
                EdgeEnchants plugin = (EdgeEnchants) EdgeEnchants.getPlugin();
                RuneDel.giveRandomTemporary(plugin, event.getPlayer());
            } else if (meta.getPersistentDataContainer().has(CUSTOM_RUNE)) {
                RuneDel.delItem(item);
                EdgeEnchants plugin = (EdgeEnchants) EdgeEnchants.getPlugin();
                RuneDel.giveRnadomCategory(plugin, event.getPlayer(), Integer.parseInt(meta.getPersistentDataContainer().get(CUSTOM_RUNE, PersistentDataType.STRING)));
            }
        }
    }
}
