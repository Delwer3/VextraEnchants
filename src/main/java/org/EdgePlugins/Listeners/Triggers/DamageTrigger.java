package org.EdgePlugins.Listeners.Triggers;

import org.EdgePlugins.Configs.Enchants;
import org.EdgePlugins.Cooldowns.Main;
import org.EdgePlugins.Cooldowns.PlayerGeneral;
import org.EdgePlugins.EdgeEnchants;
import org.EdgePlugins.Listeners.Active.ExecuteEffects;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DamageTrigger implements Listener {
    //damaged_by_entity
    @EventHandler
    public void onDamageEntity(EntityDamageByEntityEvent event){
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();
        if (damaged instanceof LivingEntity && damager instanceof LivingEntity) {
            LivingEntity player = (LivingEntity) damaged;
            LivingEntity damagerPlayer = (LivingEntity) damager;
            EdgeEnchants plugin = (EdgeEnchants) EdgeEnchants.getPlugin();
            String enchantsList = player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_HELMET, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_CHESTPLATE, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_LEGGINS, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_BOOTS, PersistentDataType.STRING);
            if (!(enchantsList == null)) {
                if (!(enchantsList.isEmpty())) {
                    ArrayList<List<String>> list = PlayerGeneral.getListString(enchantsList);
                    if (player.getPersistentDataContainer().has(Main.FINAL_LIST_CD_MAINHAND_CD)) {
                        ArrayList<String> listCd = new ArrayList<>(Arrays.asList(player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD, PersistentDataType.STRING).split(",")));
                        for (List<String> enchantList : list) {
                            if (plugin.getEnchants().getEnchantlist().contains(enchantList.get(0)))
                            if (Enchants.getEnchantType(enchantList.get(0)).equalsIgnoreCase("damaged_by_entity")) {
                                int percentToRealize = (int) Enchants.getEnchantChance(enchantList.get(0), Integer.parseInt(enchantList.get(2)));
                                int percentToFall = Math.abs(percentToRealize - 100);
                                int random = new Random().nextInt(Math.max(1, percentToFall)) + percentToRealize;

                                if (!listCd.contains(enchantList.get(0))) {
                                    if (random > percentToFall) {
                                       boolean s = ExecuteEffects.ExecuteEffect(damagerPlayer, player, Enchants.getEnchantEffects(enchantList.get(0), Integer.parseInt(enchantList.get(2))), enchantList.get(0), Integer.parseInt(enchantList.get(2)), event);
                                       if (s == true){
                                           event.setCancelled(true);
                                       }
                                    }
                                    PlayerGeneral.cdEnchant(player, enchantList);
                                } else {

                                }
                            }
                        }
                    } else {
                        for (List<String> enchantList : list) {
                            if (plugin.getEnchants().getEnchantlist().contains(enchantList.get(0))) {
                                if (Enchants.getEnchantType(enchantList.get(0)).equalsIgnoreCase("damaged_by_entity")) {
                                    int percentToRealize = (int) Enchants.getEnchantChance(enchantList.get(0), Integer.parseInt(enchantList.get(2)));
                                    int percentToFall = Math.abs(percentToRealize - 100);
                                    int random = new Random().nextInt(Math.max(1, percentToFall)) + percentToRealize;
                                    if (random > percentToFall) {
                                        PlayerGeneral.cdEnchant(player, enchantList);
                                        boolean s = ExecuteEffects.ExecuteEffect(damagerPlayer, player, Enchants.getEnchantEffects(enchantList.get(0), Integer.parseInt(enchantList.get(2))), enchantList.get(0), Integer.parseInt(enchantList.get(2)), event);
                                        if (s == true){
                                            event.setCancelled(true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    //defense-by-mob
    @EventHandler
    public void onDamageMob(EntityDamageByEntityEvent event){
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();
        if (damaged instanceof LivingEntity && damager instanceof Mob) {
            LivingEntity player = (LivingEntity) damaged;
            LivingEntity damagerPlayer = (LivingEntity) damager;
            EdgeEnchants plugin = (EdgeEnchants) EdgeEnchants.getPlugin();
            String enchantsList = player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_HELMET, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_CHESTPLATE, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_LEGGINS, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_BOOTS, PersistentDataType.STRING);
            if (!(enchantsList == null)) {
                if (!(enchantsList.isEmpty())) {
                    ArrayList<List<String>> list = PlayerGeneral.getListString(enchantsList);
                    if (player.getPersistentDataContainer().has(Main.FINAL_LIST_CD_MAINHAND_CD)) {
                        ArrayList<String> listCd = new ArrayList<>(Arrays.asList(player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD, PersistentDataType.STRING).split(",")));
                        for (List<String> enchantList : list) {
                            if (plugin.getEnchants().getEnchantlist().contains(enchantList.get(0)))
                                if (Enchants.getEnchantType(enchantList.get(0)).equalsIgnoreCase("damaged_by_mob")) {
                                    int percentToRealize = (int) Enchants.getEnchantChance(enchantList.get(0), Integer.parseInt(enchantList.get(2)));
                                    int percentToFall = Math.abs(percentToRealize - 100);
                                    int random = new Random().nextInt(Math.max(1, percentToFall)) + percentToRealize;

                                    if (!listCd.contains(enchantList.get(0))) {
                                        if (random > percentToFall) {
                                            boolean s = ExecuteEffects.ExecuteEffect(damagerPlayer, player, Enchants.getEnchantEffects(enchantList.get(0), Integer.parseInt(enchantList.get(2))), enchantList.get(0), Integer.parseInt(enchantList.get(2)), event);
                                            if (s == true){
                                                event.setCancelled(true);
                                            }
                                        }
                                        PlayerGeneral.cdEnchant(player, enchantList);
                                    } else {

                                    }
                                }
                        }
                    } else {
                        for (List<String> enchantList : list) {
                            if (plugin.getEnchants().getEnchantlist().contains(enchantList.get(0))) {
                                if (Enchants.getEnchantType(enchantList.get(0)).equalsIgnoreCase("damaged_by_mob")) {
                                    int percentToRealize = (int) Enchants.getEnchantChance(enchantList.get(0), Integer.parseInt(enchantList.get(2)));
                                    int percentToFall = Math.abs(percentToRealize - 100);
                                    int random = new Random().nextInt(Math.max(1, percentToFall)) + percentToRealize;
                                    if (random > percentToFall) {
                                        PlayerGeneral.cdEnchant(player, enchantList);
                                        boolean s = ExecuteEffects.ExecuteEffect(damagerPlayer, player, Enchants.getEnchantEffects(enchantList.get(0), Integer.parseInt(enchantList.get(2))), enchantList.get(0), Integer.parseInt(enchantList.get(2)), event);
                                        if (s == true){
                                            event.setCancelled(true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    //defense-universnal
    @EventHandler
    public void onDamagePlayer(EntityDamageByEntityEvent event){
        Entity damaged = event.getEntity();
        Entity damager = event.getDamager();
        if (damaged instanceof LivingEntity && damager instanceof Player) {
            LivingEntity player = (LivingEntity) damaged;
            LivingEntity damagerPlayer = (LivingEntity) damager;
            EdgeEnchants plugin = (EdgeEnchants) EdgeEnchants.getPlugin();
            String enchantsList = player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_HELMET, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_CHESTPLATE, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_LEGGINS, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_BOOTS, PersistentDataType.STRING);
            if (!(enchantsList == null)) {
                if (!(enchantsList.isEmpty())) {
                    ArrayList<List<String>> list = PlayerGeneral.getListString(enchantsList);
                    if (player.getPersistentDataContainer().has(Main.FINAL_LIST_CD_MAINHAND_CD)) {
                        ArrayList<String> listCd = new ArrayList<>(Arrays.asList(player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD, PersistentDataType.STRING).split(",")));
                        for (List<String> enchantList : list) {
                            if (plugin.getEnchants().getEnchantlist().contains(enchantList.get(0)))
                                if (Enchants.getEnchantType(enchantList.get(0)).equalsIgnoreCase("damaged_by_player")) {
                                    int percentToRealize = (int) Enchants.getEnchantChance(enchantList.get(0), Integer.parseInt(enchantList.get(2)));
                                    int percentToFall = Math.abs(percentToRealize - 100);
                                    int random = new Random().nextInt(Math.max(1, percentToFall)) + percentToRealize;

                                    if (!listCd.contains(enchantList.get(0))) {
                                        if (random > percentToFall) {
                                            boolean s = ExecuteEffects.ExecuteEffect(damagerPlayer, player, Enchants.getEnchantEffects(enchantList.get(0), Integer.parseInt(enchantList.get(2))), enchantList.get(0), Integer.parseInt(enchantList.get(2)), event);
                                            if (s == true){
                                                event.setCancelled(true);
                                            }
                                        }
                                        PlayerGeneral.cdEnchant(player, enchantList);
                                    } else {

                                    }
                                }
                        }
                    } else {
                        for (List<String> enchantList : list) {
                            if (plugin.getEnchants().getEnchantlist().contains(enchantList.get(0))) {
                                if (Enchants.getEnchantType(enchantList.get(0)).equalsIgnoreCase("damaged_by_player")) {
                                    int percentToRealize = (int) Enchants.getEnchantChance(enchantList.get(0), Integer.parseInt(enchantList.get(2)));
                                    int percentToFall = Math.abs(percentToRealize - 100);
                                    int random = new Random().nextInt(Math.max(1, percentToFall)) + percentToRealize;
                                    if (random > percentToFall) {
                                        PlayerGeneral.cdEnchant(player, enchantList);
                                        boolean s = ExecuteEffects.ExecuteEffect(damagerPlayer, player, Enchants.getEnchantEffects(enchantList.get(0), Integer.parseInt(enchantList.get(2))), enchantList.get(0), Integer.parseInt(enchantList.get(2)), event);
                                        if (s == true){
                                            event.setCancelled(true);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    //defense-universnal
    @EventHandler
    public void onDamagePlayerShot(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof Projectile) {
            Projectile projectile = (Projectile) event.getDamager();
            if (projectile.getShooter() instanceof Player) {
                if (event.getEntity() instanceof LivingEntity && projectile.getShooter() instanceof Player) {
                    LivingEntity player = (LivingEntity) event.getEntity();
                    LivingEntity damagerPlayer = (LivingEntity) projectile.getShooter();
                    EdgeEnchants plugin = (EdgeEnchants) EdgeEnchants.getPlugin();
                    String enchantsList = player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_HELMET, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_CHESTPLATE, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_LEGGINS, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_BOOTS, PersistentDataType.STRING);
                    if (!(enchantsList == null)) {
                        if (!(enchantsList.isEmpty())) {
                            ArrayList<List<String>> list = PlayerGeneral.getListString(enchantsList);
                            if (player.getPersistentDataContainer().has(Main.FINAL_LIST_CD_MAINHAND_CD)) {
                                ArrayList<String> listCd = new ArrayList<>(Arrays.asList(player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD, PersistentDataType.STRING).split(",")));
                                for (List<String> enchantList : list) {
                                    if (plugin.getEnchants().getEnchantlist().contains(enchantList.get(0)))
                                        if (Enchants.getEnchantType(enchantList.get(0)).equalsIgnoreCase("damaged_by_player_shoot")) {
                                            int percentToRealize = (int) Enchants.getEnchantChance(enchantList.get(0), Integer.parseInt(enchantList.get(2)));
                                            int percentToFall = Math.abs(percentToRealize - 100);
                                            int random = new Random().nextInt(Math.max(1, percentToFall)) + percentToRealize;

                                            if (!listCd.contains(enchantList.get(0))) {
                                                if (random > percentToFall) {
                                                    boolean s = ExecuteEffects.ExecuteEffect(damagerPlayer, player, Enchants.getEnchantEffects(enchantList.get(0), Integer.parseInt(enchantList.get(2))), enchantList.get(0), Integer.parseInt(enchantList.get(2)), event);
                                                    if (s == true) {
                                                        event.setCancelled(true);
                                                    }
                                                }
                                                PlayerGeneral.cdEnchant(player, enchantList);
                                            } else {

                                            }
                                        }
                                }
                            } else {
                                for (List<String> enchantList : list) {
                                    if (plugin.getEnchants().getEnchantlist().contains(enchantList.get(0))) {
                                        if (Enchants.getEnchantType(enchantList.get(0)).equalsIgnoreCase("damaged_by_player_shoot")) {
                                            int percentToRealize = (int) Enchants.getEnchantChance(enchantList.get(0), Integer.parseInt(enchantList.get(2)));
                                            int percentToFall = Math.abs(percentToRealize - 100);
                                            int random = new Random().nextInt(Math.max(1, percentToFall)) + percentToRealize;
                                            if (random > percentToFall) {
                                                PlayerGeneral.cdEnchant(player, enchantList);
                                                boolean s = ExecuteEffects.ExecuteEffect(damagerPlayer, player, Enchants.getEnchantEffects(enchantList.get(0), Integer.parseInt(enchantList.get(2))), enchantList.get(0), Integer.parseInt(enchantList.get(2)), event);
                                                if (s == true) {
                                                    event.setCancelled(true);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public void onDamageMobShot(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof Projectile) {
            Projectile projectile = (Projectile) event.getDamager();
            if (projectile.getShooter() instanceof Mob) {
                if (event.getEntity() instanceof LivingEntity && projectile.getShooter() instanceof Mob) {
                    LivingEntity player = (LivingEntity) event.getEntity();
                    LivingEntity damagerPlayer = (LivingEntity) projectile.getShooter();
                    EdgeEnchants plugin = (EdgeEnchants) EdgeEnchants.getPlugin();
                    String enchantsList = player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_HELMET, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_CHESTPLATE, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_LEGGINS, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_BOOTS, PersistentDataType.STRING);
                    if (!(enchantsList == null)) {
                        if (!(enchantsList.isEmpty())) {
                            ArrayList<List<String>> list = PlayerGeneral.getListString(enchantsList);
                            if (player.getPersistentDataContainer().has(Main.FINAL_LIST_CD_MAINHAND_CD)) {
                                ArrayList<String> listCd = new ArrayList<>(Arrays.asList(player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD, PersistentDataType.STRING).split(",")));
                                for (List<String> enchantList : list) {
                                    if (plugin.getEnchants().getEnchantlist().contains(enchantList.get(0)))
                                        if (Enchants.getEnchantType(enchantList.get(0)).equalsIgnoreCase("damaged_by_mob_shoot")) {
                                            int percentToRealize = (int) Enchants.getEnchantChance(enchantList.get(0), Integer.parseInt(enchantList.get(2)));
                                            int percentToFall = Math.abs(percentToRealize - 100);
                                            int random = new Random().nextInt(Math.max(1, percentToFall)) + percentToRealize;

                                            if (!listCd.contains(enchantList.get(0))) {
                                                if (random > percentToFall) {
                                                    boolean s = ExecuteEffects.ExecuteEffect(damagerPlayer, player, Enchants.getEnchantEffects(enchantList.get(0), Integer.parseInt(enchantList.get(2))), enchantList.get(0), Integer.parseInt(enchantList.get(2)), event);
                                                    if (s == true) {
                                                        event.setCancelled(true);
                                                    }
                                                }
                                                PlayerGeneral.cdEnchant(player, enchantList);
                                            } else {

                                            }
                                        }
                                }
                            } else {
                                for (List<String> enchantList : list) {
                                    if (plugin.getEnchants().getEnchantlist().contains(enchantList.get(0))) {
                                        if (Enchants.getEnchantType(enchantList.get(0)).equalsIgnoreCase("damaged_by_mob_shoot")) {
                                            int percentToRealize = (int) Enchants.getEnchantChance(enchantList.get(0), Integer.parseInt(enchantList.get(2)));
                                            int percentToFall = Math.abs(percentToRealize - 100);
                                            int random = new Random().nextInt(Math.max(1, percentToFall)) + percentToRealize;
                                            if (random > percentToFall) {
                                                PlayerGeneral.cdEnchant(player, enchantList);
                                                boolean s = ExecuteEffects.ExecuteEffect(damagerPlayer, player, Enchants.getEnchantEffects(enchantList.get(0), Integer.parseInt(enchantList.get(2))), enchantList.get(0), Integer.parseInt(enchantList.get(2)), event);
                                                if (s == true) {
                                                    event.setCancelled(true);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public void onDamageShoot(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof Projectile) {
            Projectile projectile = (Projectile) event.getDamager();
            if (projectile.getShooter() instanceof LivingEntity) {
                if (event.getEntity() instanceof LivingEntity && projectile.getShooter() instanceof LivingEntity) {
                    LivingEntity player = (LivingEntity) event.getEntity();
                    LivingEntity damagerPlayer = (LivingEntity) projectile.getShooter();
                    EdgeEnchants plugin = (EdgeEnchants) EdgeEnchants.getPlugin();
                    String enchantsList = player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_HELMET, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_CHESTPLATE, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_LEGGINS, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_BOOTS, PersistentDataType.STRING);
                    if (!(enchantsList == null)) {
                        if (!(enchantsList.isEmpty())) {
                            ArrayList<List<String>> list = PlayerGeneral.getListString(enchantsList);
                            if (player.getPersistentDataContainer().has(Main.FINAL_LIST_CD_MAINHAND_CD)) {
                                ArrayList<String> listCd = new ArrayList<>(Arrays.asList(player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD, PersistentDataType.STRING).split(",")));
                                for (List<String> enchantList : list) {
                                    if (plugin.getEnchants().getEnchantlist().contains(enchantList.get(0)))
                                        if (Enchants.getEnchantType(enchantList.get(0)).equalsIgnoreCase("damaged_by_shoot")) {
                                            int percentToRealize = (int) Enchants.getEnchantChance(enchantList.get(0), Integer.parseInt(enchantList.get(2)));
                                            int percentToFall = Math.abs(percentToRealize - 100);
                                            int random = new Random().nextInt(Math.max(1, percentToFall)) + percentToRealize;

                                            if (!listCd.contains(enchantList.get(0))) {
                                                if (random > percentToFall) {
                                                    boolean s = ExecuteEffects.ExecuteEffect(damagerPlayer, player, Enchants.getEnchantEffects(enchantList.get(0), Integer.parseInt(enchantList.get(2))), enchantList.get(0), Integer.parseInt(enchantList.get(2)), event);
                                                    if (s == true) {
                                                        event.setCancelled(true);
                                                    }
                                                }
                                                PlayerGeneral.cdEnchant(player, enchantList);
                                            } else {

                                            }
                                        }
                                }
                            } else {
                                for (List<String> enchantList : list) {
                                    if (plugin.getEnchants().getEnchantlist().contains(enchantList.get(0))) {
                                        if (Enchants.getEnchantType(enchantList.get(0)).equalsIgnoreCase("damaged_by_shoot")) {
                                            int percentToRealize = (int) Enchants.getEnchantChance(enchantList.get(0), Integer.parseInt(enchantList.get(2)));
                                            int percentToFall = Math.abs(percentToRealize - 100);
                                            int random = new Random().nextInt(Math.max(1, percentToFall)) + percentToRealize;
                                            if (random > percentToFall) {
                                                PlayerGeneral.cdEnchant(player, enchantList);
                                                boolean s = ExecuteEffects.ExecuteEffect(damagerPlayer, player, Enchants.getEnchantEffects(enchantList.get(0), Integer.parseInt(enchantList.get(2))), enchantList.get(0), Integer.parseInt(enchantList.get(2)), event);
                                                if (s == true) {
                                                    event.setCancelled(true);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
                    LivingEntity player = (LivingEntity) event.getEntity();
                    EdgeEnchants plugin = (EdgeEnchants) EdgeEnchants.getPlugin();
        String enchantsList = player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_HELMET, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_CHESTPLATE, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_LEGGINS, PersistentDataType.STRING)+player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD_BOOTS, PersistentDataType.STRING);
                    if (!(enchantsList == null)) {
                        if (!(enchantsList.isEmpty())) {
                            ArrayList<List<String>> list = PlayerGeneral.getListString(enchantsList);
                            if (player.getPersistentDataContainer().has(Main.FINAL_LIST_CD_MAINHAND_CD)) {
                                ArrayList<String> listCd = new ArrayList<>(Arrays.asList(player.getPersistentDataContainer().get(Main.FINAL_LIST_CD_MAINHAND_CD, PersistentDataType.STRING).split(",")));
                                for (List<String> enchantList : list) {
                                    if (plugin.getEnchants().getEnchantlist().contains(enchantList.get(0)))
                                        if (Enchants.getEnchantType(enchantList.get(0)).equalsIgnoreCase("player_kill")) {
                                            int percentToRealize = (int) Enchants.getEnchantChance(enchantList.get(0), Integer.parseInt(enchantList.get(2)));
                                            int percentToFall = Math.abs(percentToRealize - 100);
                                            int random = new Random().nextInt(Math.max(1, percentToFall)) + percentToRealize;

                                            if (!listCd.contains(enchantList.get(0))) {
                                                if (random > percentToFall) {
                                                    boolean s = ExecuteEffects.ExecuteEffect(player, player, Enchants.getEnchantEffects(enchantList.get(0), Integer.parseInt(enchantList.get(2))), enchantList.get(0), Integer.parseInt(enchantList.get(2)), event);
                                                    if (s == true) {
                                                    }
                                                }
                                                PlayerGeneral.cdEnchant(player, enchantList);
                                            } else {

                                            }
                                        }
                                }
                            } else {
                                for (List<String> enchantList : list) {
                                    if (plugin.getEnchants().getEnchantlist().contains(enchantList.get(0))) {
                                        if (Enchants.getEnchantType(enchantList.get(0)).equalsIgnoreCase("player_kill")) {
                                            int percentToRealize = (int) Enchants.getEnchantChance(enchantList.get(0), Integer.parseInt(enchantList.get(2)));
                                            int percentToFall = Math.abs(percentToRealize - 100);
                                            int random = new Random().nextInt(Math.max(1, percentToFall)) + percentToRealize;
                                            if (random > percentToFall) {
                                                PlayerGeneral.cdEnchant(player, enchantList);
                                                boolean s = ExecuteEffects.ExecuteEffect(player, player, Enchants.getEnchantEffects(enchantList.get(0), Integer.parseInt(enchantList.get(2))), enchantList.get(0), Integer.parseInt(enchantList.get(2)), event);
                                                if (s == true) {
                                                }
                                            }
                                        }
                                    }
                                }
                }
            }
        }
    }
}
