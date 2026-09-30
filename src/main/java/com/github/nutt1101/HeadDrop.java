package com.github.nutt1101;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.stream.Collectors;

import com.github.nutt1101.event.HitEvent;
import com.github.nutt1101.utils.NBTHandler;
import com.github.nutt1101.utils.TranslationFileReader;

import org.bukkit.Bukkit;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.CopperGolem;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Frog;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Llama;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Shulker;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.ZombieNautilus;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import net.md_5.bungee.api.ChatColor;

public class HeadDrop {
    private final Plugin plugin = CatchBall.plugin;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss");

    /**
     * When CatchBall hit catchable entity, It will drop the skull of hitEntity.
     * @param hitEntity the hitEntity of the hit event
     * @param player player who throw the CatchBall
     * @return the skull of saved hitEntity info
     */
    public ItemStack getEntityHead(Entity hitEntity, Player player) {
        String entityName = getEntityValue(hitEntity, "DisplayName");

        ItemStack entityHead = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta headMeta = entityHead.getItemMeta();

        String hitEntityUuid = hitEntity.getUniqueId().toString();
        PersistentDataContainer headData = headMeta.getPersistentDataContainer();
        headData.set(new NamespacedKey(plugin, "skullData"), PersistentDataType.STRING, hitEntityUuid);

        Date now = new Date();
        String location = "(" + hitEntity.getWorld().getName() + ") " +
                HitEvent.getCoordinate(hitEntity.getLocation());

        if (hitEntity.getCustomName() != null) {
            headMeta.setDisplayName(ChatColor.WHITE + hitEntity.getCustomName());
        } else {
            headMeta.setDisplayName(ChatColor.WHITE + entityName);
        }

        List<String> headLore = new ArrayList<>();

        if (player == null) {
            headLore.addAll(TranslationFileReader.dropSkullLore.stream().map(lore -> ChatColor.translateAlternateColorCodes('&', lore).
                    replace("{ENTITY}", entityName).replace("{PLAYER}", "Dispenser").replace("{TIME}", format.format(now)).
                    replace("{LOCATION}", location)).collect(Collectors.toList()));
        } else {
            headLore.addAll(TranslationFileReader.dropSkullLore.stream().map(lore -> ChatColor.translateAlternateColorCodes('&', lore).
                    replace("{ENTITY}", entityName).replace("{PLAYER}", player.getName()).replace("{TIME}", format.format(now)).
                    replace("{LOCATION}", location)).collect(Collectors.toList()));
        }

        headMeta = NBTHandler.saveEntityNBT(plugin, hitEntity, headMeta);

        headMeta.setLore(headLore);
        entityHead.setItemMeta(headMeta);

        return skullTextures(entityHead, getEntityValue(hitEntity, "Skull"));
    }

    public static String getEntityValue(Entity entity, String field) {
        return getEntityValue(entity.getType().name(), getVariant(entity), field);
    }

    public static String getEntityValue(String type, String variant, String field) {
        String path = "EntityList." + type + ".";
        String value = variant == null ? null : ConfigSetting.entityFile.getString(path + "Variants." + variant + "." + field);
        return value != null ? value : ConfigSetting.entityFile.getString(path + field);
    }

    public static String getVariant(Entity entity) {
        Object variant = switch (entity) {
            case Cat cat -> cat.getCatType();
            case Wolf wolf -> wolf.getVariant();
            case Frog frog -> frog.getVariant();
            case Cow cow -> cow.getVariant();
            case Pig pig -> pig.getVariant();
            case Chicken chicken -> chicken.getVariant();
            case MushroomCow mushroomCow -> mushroomCow.getVariant();
            case Axolotl axolotl -> axolotl.getVariant();
            case Rabbit rabbit -> rabbit.getRabbitType();
            case Parrot parrot -> parrot.getVariant();
            case Fox fox -> fox.getFoxType();
            case Horse horse -> horse.getColor();
            case Llama llama -> llama.getColor();
            case Panda panda -> panda.getCombinedGene();
            case Sheep sheep -> sheep.getColor();
            case Shulker shulker -> shulker.getColor();
            case Villager villager -> villager.getProfession();
            case ZombieVillager zombieVillager -> zombieVillager.getVillagerProfession();
            case ZombieNautilus zombieNautilus -> zombieNautilus.getVariant();
            case CopperGolem copperGolem -> copperGolem.getWeatheringState();
            default -> null;
        };
        if (variant instanceof Keyed keyed) return keyed.getKey().getKey().toUpperCase(Locale.ROOT);
        return variant instanceof Enum<?> e ? e.name() : null;
    }

    /**
     * Apply the entity.yml skull texture to the head.
     * @param head skull of hitEntity
     * @param textureValue base64 texture value from entity.yml
     * @return head with texture value
     */
    public ItemStack skullTextures(ItemStack head, String textureValue) {
        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();

        try {
            PlayerProfile profile = Bukkit.createPlayerProfile("catchball");
            PlayerTextures textures = profile.getTextures();

            if (textureValue != null && !textureValue.isEmpty()) {
                try {
                    String decodedValue = new String(Base64.getDecoder().decode(textureValue));

                    String urlStr = decodedValue.split("\"url\":\"")[1].split("\"")[0];
                    URL textureUrl = new URL(urlStr);

                    textures.setSkin(textureUrl);
                    profile.setTextures(textures);

                    skullMeta.setOwnerProfile(profile);
                } catch (Exception e) {
                    plugin.getLogger().log(Level.WARNING, "Failed to decode texture value: " + e.getMessage());
                }
            } else {
                plugin.getLogger().log(Level.WARNING, "Could not find skull texture value");
            }
        } catch (Exception e) {
            plugin.getLogger().log(Level.WARNING, "Failed to set skull texture: " + e.getMessage());
            e.printStackTrace();
        }

        head.setItemMeta(skullMeta);
        return head;
    }
}