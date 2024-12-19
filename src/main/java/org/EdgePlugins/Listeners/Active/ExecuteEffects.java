package org.EdgePlugins.Listeners.Active;

import org.EdgePlugins.EdgeEnchants;
import org.EdgePlugins.Effects.*;
import org.EdgePlugins.Utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ExecuteEffects {
    private static void noEffectExist(String enchant, int lvl, String effect){
        Bukkit.getLogger().log(Level.WARNING, Message.getTranslate(EdgeEnchants.prefixPlugin + " &cThe enchant "+ enchant+ "(lvl "+lvl+ "). No exist Effect &4"+effect));
    }
    private static void noEffectLegth(String enchant, int lvl, String effect){
        Bukkit.getLogger().log(Level.WARNING, Message.getTranslate(EdgeEnchants.prefixPlugin + " &cThe enchant "+ enchant+ "(lvl "+lvl+ "). Does not have enough or invalid arguments in effect &4"+effect));
    }

    public static List<LivingEntity> getTargets(LivingEntity damager, LivingEntity damaged, String[] args, String nameEnchant) {
        String targetString = args[args.length - 1].replaceAll("\\s", "");

        if (args[0].trim().equalsIgnoreCase("CANCEL_EVENT") ||
                args[0].trim().equalsIgnoreCase("PLAY_CONSOLE_COMMAND") ||
                args[0].trim().equalsIgnoreCase("ADD_DURAILITY_IN_ITEM")) {
            return null;
        }

        Pattern prefixPattern = Pattern.compile("^(@[A-Za-z]+)");
        Matcher prefixMatcher = prefixPattern.matcher(targetString);

        String prefix = "";
        if (prefixMatcher.find()) {
            prefix = prefixMatcher.group(1);
        }

        Pattern valuePattern = Pattern.compile("\\{(.*?)\\}");
        Matcher valueMatcher = valuePattern.matcher(targetString);

        String value = "";
        if (valueMatcher.find()) {
            value = valueMatcher.group(1);
        }
        String[] valueArray = value.split(":");
        if (prefix.equalsIgnoreCase("@Damaged")) {
            List<LivingEntity> listPlayer = new ArrayList<>();
            listPlayer.add(damaged);
            listPlayer.add(damager);
            return listPlayer;
        } else if (prefix.equalsIgnoreCase("@Damager")) {
            List<LivingEntity> listPlayer = new ArrayList<>();
            listPlayer.add(damager);
            listPlayer.add(damaged);
            return listPlayer;
        } else if (prefix.equalsIgnoreCase("@Radius")) {
            int radius = 0;
            String targets = "";
            try {
                int radius1 = Integer.parseInt(valueArray[0]);
                String targets1 = valueArray[1];
                targets = targets1;
                radius = radius1;
            }catch (NumberFormatException e){
             Bukkit.getLogger().log(Level.SEVERE, "[VextraEnchants] Error in radius or type targeters radius settings in "+nameEnchant);
            }

            List<LivingEntity> listPlayer = new ArrayList<>();
            listPlayer.add(damaged);
                if (targets.equalsIgnoreCase("MOBS")) {
                    for (Entity entity : damaged.getWorld().getNearbyEntities(damaged.getLocation(), radius, radius, radius)) {
                        if (entity instanceof LivingEntity) {
                            LivingEntity entity1 = (LivingEntity) entity;
                            if (entity1 instanceof Mob) {
                                    listPlayer.add(entity1);
                            }
                        }
                    }
                } else if (targets.equalsIgnoreCase("PLAYERS")) {
                    for (Entity entity : damaged.getWorld().getNearbyEntities(damaged.getLocation(), radius, radius, radius)) {
                        if (entity instanceof LivingEntity) {
                            LivingEntity entity1 = (LivingEntity) entity;
                            if (entity1 instanceof Player) {
                                    listPlayer.add(entity1);
                            }
                        }
                    }
                }
            return listPlayer;
        } else {
            Bukkit.getLogger().log(Level.SEVERE, "Effect " + args[0] + " could not be executed since " + targetString + " is not a valid target");
            return null;
        }
    }
    public static String replacePlaceholders(String input, Map<String, String> placeholders) {
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            input = input.replace(entry.getKey(), entry.getValue());
        }
        return input;
    }
    public static Map placeHolders(LivingEntity damaged, LivingEntity damager, int Level, String nameEnchant, Event event){
        Map<String, String> placeholders = new HashMap<>();
        if (damaged instanceof Player){
            placeholders.put("%damaged_name%", ((Player) damaged).getDisplayName());
        }
        if (damager instanceof Player){
            placeholders.put("%damager_name%", ((Player) damager).getDisplayName());
        }
        placeholders.put("%damager_max_health%", (""+damager.getAttribute(Attribute.GENERIC_MAX_HEALTH)));
        placeholders.put("%damaged_max_health%", (""+damaged.getAttribute(Attribute.GENERIC_MAX_HEALTH)));
        placeholders.put("%damager_health%", (""+damager.getHealth()));
        placeholders.put("%damaged_health%", (""+damaged.getHealth()));
        placeholders.put("%enchant_level%", (""+Level));
        placeholders.put("%enchant_name%", (""+nameEnchant));
        if (event instanceof EntityDamageByEntityEvent){
            EntityDamageByEntityEvent event1 = (EntityDamageByEntityEvent) event;
            placeholders.put("%damage%", (""+(int)event1.getDamage()));
        }
        if (placeholders.isEmpty()){
            return null;
        }
        return placeholders;
    }

    public static boolean ExecuteEffect(LivingEntity damager , LivingEntity damaged, List<String> effectsList, String nameEnchant, int levelEnchant, Event event){
        Map<String, String> finalPlaceholders = placeHolders(damaged, damager, levelEnchant, nameEnchant, event);
        boolean sfinal = false;
        for (String line : effectsList){
            if (finalPlaceholders != null) {
                line = replacePlaceholders(line, finalPlaceholders);
            }
          String[] args = line.split("°");
          List<LivingEntity> playerList = getTargets(damager, damaged, args, nameEnchant);
            LivingEntity player = null;
            LivingEntity player1 = null;
            if (!(playerList == null)) {
                if (!playerList.isEmpty()){
                    if (playerList.size() >= 2) {
                        player = playerList.get(0);
                        player1 = playerList.get(1);
                    }
                    if (playerList.size() >= 1){
                        player = playerList.get(0);
                    }
               }
          }
          boolean s = findUsage(args[0], args, player, nameEnchant, levelEnchant, player1, event, playerList);
          if (s == true){
              sfinal = s;
          }
        }
        return sfinal;
    }

    public static boolean findUsage(String usage, String[] args, LivingEntity player, String nameEnchant, int levelEnchant, LivingEntity player1, Event event, List<LivingEntity> livingEntityList) {
        usage = usage.trim();
        if (usage.equalsIgnoreCase("SENDMESSAGE")) {

            if (args.length >= 3) {
                if (livingEntityList.size() > 2) {
                    for (LivingEntity entity : livingEntityList) {
                        if (!(entity == player)) {
                            SendMessage.sendMessage(args[1], entity);
                        }
                    }
                }else{
                    SendMessage.sendMessage(args[1], player);
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        } else if (usage.equalsIgnoreCase("DAMAGE")) {
            if (args.length >= 3) {
                try {

                    int damage = Integer.parseInt(args[1].trim());
                    if (livingEntityList.size() > 2) {
                        for (LivingEntity entity : livingEntityList) {
                            if (!(entity == player)) {
                                Damage.setDamage(entity, damage);
                            }
                        }
                    }else{
                        Damage.setDamage(player, damage);
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid number or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        } else if (usage.equalsIgnoreCase("ADD_HEALTH")) {
            if (args.length >= 3) {
                try {
                    int health = Integer.parseInt(args[1].trim());
                    if (livingEntityList.size() > 2) {
                        for (LivingEntity entity : livingEntityList) {
                            if (!(entity == player)) {
                                ADD_HEALTH.addHealh(entity, health);
                            }
                        }
                    }else{
                        ADD_HEALTH.addHealh(player, health);
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid number or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        } else if (usage.equalsIgnoreCase("SET_POTION")) {
            if (args.length >= 5) {
                try {
                    String potionType = args[1].trim().toUpperCase();
                    int duration = Integer.parseInt(args[2].trim());
                    int amplifier = Integer.parseInt(args[3].trim());
                    if (livingEntityList.size() > 2) {
                        for (LivingEntity entity : livingEntityList) {
                            if (!(entity == player)) {
                                PotionSet.setPotionEffect(entity, potionType, duration, amplifier);
                            }
                        }
                    } else {
                        PotionSet.setPotionEffect(player, potionType, duration, amplifier);
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid number or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        } else if (usage.equalsIgnoreCase("REMOVE_POTION")) {
            if (args.length >= 3) {
                try {
                    String potionType = args[1].trim();
                    if (livingEntityList.size() > 2) {
                        for (LivingEntity entity : livingEntityList) {
                            if (!(entity == player)) {
                                PotionSet.removePotionEffect(entity, potionType);
                            }
                        }
                    } else {
                        PotionSet.removePotionEffect(player, potionType);
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid number or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        } else if (usage.equalsIgnoreCase("ADD_XP")) {
            if (args.length >= 3) {
                try {

                    int xp = Integer.parseInt(args[1].trim());
                    if (livingEntityList.size() > 2) {
                        for (LivingEntity entity : livingEntityList) {
                            if (!(entity == player)) {
                                XP_EFFECTS.add_xp(entity, xp);
                            }
                        }
                    } else {
                        XP_EFFECTS.add_xp(player, xp);
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid number or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        } else if (usage.equalsIgnoreCase("REMOVE_XP")) {
            if (args.length >= 3) {
                try {
                    int xp = Integer.parseInt(args[1].trim());
                    if (livingEntityList.size() > 2) {
                        for (LivingEntity entity : livingEntityList) {
                            if (!(entity == player)) {
                                XP_EFFECTS.remove_xp(entity, xp);
                            }
                        }
                    }else {
                        XP_EFFECTS.remove_xp(player, xp);
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid number or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        } else if (usage.equalsIgnoreCase("REMOVE_XP_LVL")) {
            if (args.length >= 3) {
                try {
                    int xp = Integer.parseInt(args[1].trim());
                    if (livingEntityList.size() > 2) {
                        for (LivingEntity entity : livingEntityList) {
                            if (!(entity == player)) {
                                XP_EFFECTS.remove_xp_lvl(entity, xp);
                            }
                        }
                    }else {
                        XP_EFFECTS.remove_xp_lvl(player, xp);
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid number or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        } else if (usage.equalsIgnoreCase("ADD_XP_LVL")) {
            if (args.length >= 3) {
                try {
                    int xp = Integer.parseInt(args[1].trim());
                    if (livingEntityList.size() > 2) {
                        for (LivingEntity entity : livingEntityList) {
                            if (!(entity == player)) {
                                XP_EFFECTS.add_xp_lvl(entity, xp);
                            }
                        }
                    }else {
                        XP_EFFECTS.add_xp_lvl(player, xp);
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid number or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        } else if (usage.equalsIgnoreCase("CANCEL_EVENT")) {
            if (args.length >= 1) {
                return true;
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        } else if (usage.equalsIgnoreCase("STEAL_HEALTH")) {
            if (args.length >= 2) {
                if (livingEntityList.size() > 2) {
                    for (LivingEntity entity : livingEntityList) {
                        if (!(entity == player)) {
                            STEAL_HEALTH.stealHealth(player, entity, Integer.parseInt(args[1].trim()));
                        }
                    }
                }else {
                    STEAL_HEALTH.stealHealth(player, player1, Integer.parseInt(args[1].trim()));
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        }  else if (usage.equalsIgnoreCase("PLAY_SOUND")) {
            if (args.length >= 4) {
                try {
                    int volume = Integer.parseInt(args[2].trim());
                    int pitch = Integer.parseInt(args[3].trim());
                    if (livingEntityList.size() > 2) {
                        for (LivingEntity entity : livingEntityList) {
                            if (!(entity == player)) {
                                PLAY_SOUND.playSound(args[1].trim(), entity, volume, pitch);
                            }
                        }
                    }else {
                        PLAY_SOUND.playSound(args[1].trim(), player, volume, pitch);
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid number or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        }  else if (usage.equalsIgnoreCase("PLAY_CONSOLE_COMMAND")) {
            if (args.length >= 2) {
                try {
                    CONSOLE_COMMAND.consoleCoomand(args[1]);
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid command or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        }  else if (usage.equalsIgnoreCase("LIGHTNING")) {
            if (args.length >= 2) {
                try {
                    if (livingEntityList.size() > 2) {
                        for (LivingEntity entity : livingEntityList) {
                            if (!(entity == player)) {
                                LIGHTNING.ligting(entity);
                            }
                        }
                    }else {
                        LIGHTNING.ligting(player);
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid command or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        }  else if (usage.equalsIgnoreCase("LIGHTNING_EFFECT")) {
            if (args.length >= 2) {
                try {
                    if (livingEntityList.size() > 2) {
                        for (LivingEntity entity : livingEntityList) {
                            if (!(entity == player)) {
                                LIGHTNING.ligtingEffect(entity);
                            }
                        }
                    }else {
                        LIGHTNING.ligtingEffect(player);
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid command or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        }   else if (usage.equalsIgnoreCase("SET_PARTICLE")) {
            if (args.length >= 2) {
                try {
                    int count = Integer.parseInt(args[2].trim());
                    double offsetX = Integer.parseInt(args[3].trim());
                    double offsetY = Integer.parseInt(args[4].trim());
                    double offsetZ = Integer.parseInt(args[5].trim());
                    double size = Integer.parseInt(args[6].trim());
                    if (livingEntityList.size() > 2) {
                        for (LivingEntity entity : livingEntityList) {
                            if (!(entity == player)) {
                                SET_PARTICLE.setParticle(entity, args[1], count, offsetX, offsetY, offsetZ, size);
                            }
                        }
                    }else {
                        SET_PARTICLE.setParticle(player, args[1], count, offsetX, offsetY, offsetZ, size);
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid command or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        }  else if (usage.equalsIgnoreCase("AMPLIFY_DAMAGE")) {
            if (args.length >= 2) {
                try {
                    int amount = Integer.parseInt(args[1].trim());
                    if (livingEntityList.size() > 2) {
                        for (LivingEntity entity : livingEntityList) {
                            if (!(entity == player)) {
                                R_A_DAMAGE.amplifyDamage(entity, amount, event);
                            }
                        }
                    }else {
                        R_A_DAMAGE.amplifyDamage(player1, amount, event);
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid command or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        }  else if (usage.equalsIgnoreCase("REDUCE_DAMAGE")) {
            if (args.length >= 2) {
                try {
                    int amount = Integer.parseInt(args[1].trim());
                    if (livingEntityList.size() > 2) {
                        for (LivingEntity entity : livingEntityList) {
                            if (!(entity == player)) {
                                R_A_DAMAGE.reduceDamage(entity, amount, event);
                            }
                        }
                    }else {
                        R_A_DAMAGE.reduceDamage(player, amount, event);
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid command or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        }  else if (usage.equalsIgnoreCase("ADD_DURAILITY_IN_MAIN_HAND")) {
            if (args.length >= 2) {
                try {
                    int amount = Integer.parseInt(args[1].trim());
                    if (livingEntityList.size() > 2) {
                        for (LivingEntity entity : livingEntityList) {
                            if (!(entity == player)) {
                                DURABILITY.repairInMainHand(entity, amount);
                            }
                        }
                    }else {
                        DURABILITY.repairInMainHand(player, amount);
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid command or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        }  else if (usage.equalsIgnoreCase("ADD_DURAILITY_IN_ALL_ARMOR")) {
            if (args.length >= 2) {
                try {
                    int amount = Integer.parseInt(args[1].trim());
                    if (livingEntityList.size() > 2) {
                        for (LivingEntity entity : livingEntityList) {
                            if (!(entity == player)) {
                                DURABILITY.repairAllArmor(entity, amount);
                            }
                        }
                    }else {
                        DURABILITY.repairAllArmor(player, amount);
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid command or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        }  else if (usage.equalsIgnoreCase("DAMAGE_ALL_ARMOR")) {
            if (args.length >= 3) {
                try {
                    int amount = Integer.parseInt(args[1].trim());
                    if (livingEntityList.size() > 2) {
                        for (LivingEntity entity : livingEntityList) {
                            if (!(entity == player)) {
                                DURABILITY.damageAllArmor(entity, amount);
                            }
                        }
                    }else {
                        DURABILITY.damageAllArmor(player, amount);
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid command or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        }  else if (usage.equalsIgnoreCase("DAMAGE_ITEM_IN_MAIN_HAND")) {
            if (args.length >= 3) {
                try {
                    int amount = Integer.parseInt(args[1].trim());
                    if (livingEntityList.size() > 2) {
                        for (LivingEntity entity : livingEntityList) {
                            if (!(entity == player)) {
                                DURABILITY.damageInMainHand(entity, amount);
                            }
                        }
                    }else {
                        DURABILITY.damageInMainHand(player, amount);
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid command or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        }  else if (usage.equalsIgnoreCase("KILL")) {
            if (args.length >= 2) {
                try {
                    if (livingEntityList.size() > 2) {
                        for (LivingEntity entity : livingEntityList) {
                            if (!(entity == player)) {
                                KILL.killPlayer(entity);
                            }
                        }
                    }else {
                        KILL.killPlayer(player);
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid command or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        }  else if (usage.equalsIgnoreCase("PUSH")) {
            if (args.length >= 3) {
                try {
                    if (livingEntityList.size() > 2) {
                        for (LivingEntity entity : livingEntityList) {
                            if (!(entity == player)) {
                                PUSH.pushEntity(entity, Integer.parseInt(args[1].trim()));
                            }
                        }
                    }else {
                        PUSH.pushEntity(player, Integer.parseInt(args[1].trim()));
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid command or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        }  else if (usage.equalsIgnoreCase("EXPLOSION")) {
            if (args.length >= 3) {
                try {
                    float potence = Float.parseFloat(args[1].trim());
                    if (livingEntityList.size() > 2) {
                        for (LivingEntity entity : livingEntityList) {
                            if (!(entity == player)) {
                                EXPLOSION.setExplosionReal(entity, potence);
                            }
                        }
                    }else {
                        EXPLOSION.setExplosionReal(player, potence);
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid command or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        }  else if (usage.equalsIgnoreCase("EXPLOSION_EFFECT")) {
            if (args.length >= 3) {
                try {
                    float potence = Float.parseFloat(args[1].trim());
                    if (livingEntityList.size() > 2) {
                        for (LivingEntity entity : livingEntityList) {
                            if (!(entity == player))
                            EXPLOSION.setExplosionEffect(entity, potence);
                            }
                    }else {
                        EXPLOSION.setExplosionEffect(player, potence);
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid command or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        }  else if (usage.equalsIgnoreCase("SET_HEALTH")) {
            if (args.length >= 3) {
                try {
                    int value = Integer.parseInt(args[1].trim());
                    if (livingEntityList.size() > 2) {
                        for (LivingEntity entity : livingEntityList) {
                            if (!(entity == player))
                                SET_HEALTH.setHealth(entity, value);
                        }
                    }else {
                        EXPLOSION.setExplosionEffect(player, value);
                    }
                } catch (NumberFormatException e) {
                    Bukkit.getLogger().log(Level.SEVERE, "Invalid command or structure in " + nameEnchant + " (Lvl" + levelEnchant + ")");
                }
            } else {
                noEffectLegth(nameEnchant, levelEnchant, usage);
            }
        }
        return false;
    }

}
