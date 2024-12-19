package org.EdgePlugins.Cooldowns;

import com.google.common.collect.Lists;
import org.EdgePlugins.Configs.Enchants;
import org.EdgePlugins.Configs.TemporaryEnchants;
import org.EdgePlugins.EdgeEnchants;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

import static org.EdgePlugins.Listeners.ApplyEnchantListener.plugin;

public class PlayerGeneral implements Listener {
    public static NamespacedKey FinalEnchantsKey = new NamespacedKey(EdgeEnchants.getPlugin(), "FinalEnchantsKey");

    public static ArrayList<List<String>> getListString(String cdCadena){
        String[] cdCadenas = cdCadena.split(",");
        ArrayList<List<String>> finalList = new ArrayList<>();
        for(String parte : cdCadenas){
            ArrayList<String> finalList1 = new ArrayList<>();
            String[] partes = parte.split(":");
            String nameEnchant = partes[0];
            if (plugin.getEnchants().getEnchantlist().contains(partes[0])) {
                String cd = String.valueOf(Enchants.getEnchantCd(nameEnchant, Integer.parseInt(partes[2])));
                String level = partes[2];
                String isinCd = partes[3];
                finalList1.add(nameEnchant);
                finalList1.add(cd);
                finalList1.add(level);
                finalList1.add(isinCd);
                finalList.add(finalList1);
            }
        }
        return finalList;
    }
    public static void setListInMainHand(ArrayList<List<String>> listInMainHand, Player player){
    }
        /*
    @EventHandler
    public static void OnPlayerJoin(PlayerJoinEvent event){
        if (event.getPlayer() != null){
            Player player = event.getPlayer();
            List<String> cdEnchants = new ArrayList<>();
            Bukkit.getScheduler().runTaskTimer(EdgeEnchants.getPlugin(), () -> {
                String metaList = player.getPersistentDataContainer().get(Main.FINAL_KEY, PersistentDataType.STRING);
                if (!(metaList.split(",").length == cdEnchants.size())){
                if (!(metaList.isEmpty() )) {
                    player.sendMessage(metaList + "    metalist");
                    if (metaList != null || !(metaList.isEmpty())) {
                        String[] partes = metaList.split(",");
                        Map<String, String> map = new HashMap<>();
                        for (String parte : partes) {
                            String[] partes1 = parte.split(":");
                            if (partes1.length >= 3) {
                                String nameEnchant = partes1[0];
                                int cd = Integer.parseInt(partes1[1]);
                                int level = Integer.parseInt(partes1[2]);
                                String finalString = "" + cd + ":" + level;
                                map.put(nameEnchant, finalString);
                            }
                        }
                        String finalEnchantList = "";
                        if (map.size() > 0) {
                            for (String enchantName : map.keySet()) {
                                if (!(cdEnchants.contains(enchantName))) {
                                    String cdvalue = map.get(enchantName);
                                    String[] partesCD = cdvalue.split(":");
                                    int cd = Integer.parseInt(partesCD[0]);
                                    int level = Integer.parseInt(partesCD[1]);
                                    if (cd > 0) {
                                        player.sendMessage(cdEnchants.toString());
                                        cdEnchants.add(enchantName);
                                        cdEnchant(player, enchantName, cd, level, cdEnchants);
                                    } else {
                                        finalEnchantList = finalEnchantList + enchantName + ":" + level + ",";
                                    }
                                }
                            }
                        }
                        if (finalEnchantList.length() > 0) {
                            player.getPersistentDataContainer().set(FinalEnchantsKey, PersistentDataType.STRING, finalEnchantList);
                        }
                    }
                }else {player.getPersistentDataContainer().set(FinalEnchantsKey, PersistentDataType.STRING, "");
                    cdEnchants.clear();
                }
                }
            }, 0L, 20L);
        }
    }

     */

    public static String  getValor(LivingEntity player){
        return player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD, PersistentDataType.STRING);

    }
    public static void cdEnchant(LivingEntity player, List<String> list) {
        int cd = Integer.parseInt(list.get(1));
        if (!player.getPersistentDataContainer().has(Main.FINAL_LIST_CD_MAINHAND_CD)){
            player.getPersistentDataContainer().set(Main.FINAL_LIST_CD_MAINHAND_CD, PersistentDataType.STRING, list.get(0)+",");
        }else{
            String value = player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD, PersistentDataType.STRING);
            player.getPersistentDataContainer().set(Main.FINAL_LIST_CD_MAINHAND_CD, PersistentDataType.STRING, value+list.get(0)+",");
        }
        Bukkit.getScheduler().runTaskLater(EdgeEnchants.getPlugin(), () -> {
            String valor = getValor(player);
            String[] valor1 = valor.split(",");
            ArrayList<String> list1 = new ArrayList<>(Arrays.asList(valor1));
            list1.remove(list.get(0));
            String finalList = String.join(",", list1);
            player.getPersistentDataContainer().set(Main.FINAL_LIST_CD_MAINHAND_CD, PersistentDataType.STRING, finalList);

        }, cd * 20L);
    }

}
