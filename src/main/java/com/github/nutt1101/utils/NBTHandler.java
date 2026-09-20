package com.github.nutt1101.utils;

import de.tr7zw.changeme.nbtapi.NBTContainer;
import de.tr7zw.changeme.nbtapi.NBTEntity;
import de.tr7zw.changeme.nbtapi.NBTType;
import io.papermc.paper.entity.EntitySerializationFlag;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class NBTHandler {

    private static final String LEGACY_NBT_KEY = "entity";
    private static final String ENTITY_DATA_KEY = "entity_data";
    private static final String ENTITY_TYPE_KEY = "entityType";
    private static final String[] RELEASE_RESET_KEYS = {"Motion", "FallDistance", "fall_distance"};

    public static ItemMeta saveEntityNBT(Plugin plugin, Entity hitEntity, ItemMeta headMeta) {
        PersistentDataContainer data = headMeta.getPersistentDataContainer();
        data.set(
                new NamespacedKey(plugin, ENTITY_DATA_KEY),
                PersistentDataType.BYTE_ARRAY,
                Bukkit.getUnsafe().serializeEntity(hitEntity, EntitySerializationFlag.FORCE)
        );

        data.set(
                new NamespacedKey(plugin, ENTITY_TYPE_KEY),
                PersistentDataType.STRING,
                hitEntity.getType().toString()
        );

        return headMeta;
    }

    public static Entity spawnStoredEntity(Plugin plugin, PersistentDataContainer data,
                                           Location location, EntityType entityType) {
        byte[] serialized = data.get(new NamespacedKey(plugin, ENTITY_DATA_KEY), PersistentDataType.BYTE_ARRAY);

        if (serialized != null) {
            serialized = sanitizeSerializedEntity(serialized, entityType);

            Entity entity = Bukkit.getUnsafe().deserializeEntity(serialized, location.getWorld(), false, true);
            entity.spawnAt(location, CreatureSpawnEvent.SpawnReason.CUSTOM);
            return entity;
        }

        Entity entity = location.getWorld().spawnEntity(location, entityType);
        loadLegacyEntityNBT(plugin, entity, data);
        return entity;
    }

    private static byte[] sanitizeSerializedEntity(byte[] serialized, EntityType entityType) {
        try {
            NBTContainer container = new NBTContainer(new ByteArrayInputStream(serialized));
            boolean changed = false;

            for (String key : RELEASE_RESET_KEYS) {
                if (container.hasTag(key)) {
                    container.removeKey(key);
                    changed = true;
                }
            }

            if (entityType == EntityType.CREAKING && container.hasTag("home_pos")) {
                container.removeKey("home_pos");
                changed = true;
            }

            if (!changed) {
                return serialized;
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            container.writeCompound(out);
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return serialized;
        }
    }

    private static void loadLegacyEntityNBT(Plugin plugin, Entity entity, PersistentDataContainer data) {
        try {
            String nbtString = data.get(new NamespacedKey(plugin, LEGACY_NBT_KEY), PersistentDataType.STRING);
            if (nbtString == null) {
                return;
            }

            NBTContainer nbtContainer = new NBTContainer(nbtString);

            nbtContainer.removeKey("Pos");
            nbtContainer.removeKey("Motion");
            nbtContainer.removeKey("Rotation");
            nbtContainer.removeKey("FallDistance");
            nbtContainer.removeKey("OnGround");
            nbtContainer.removeKey("home_pos");

            Component legacyName = takeLegacyCustomName(nbtContainer);

            NBTEntity nbtEntity = new NBTEntity(entity);
            nbtEntity.mergeCompound(nbtContainer);

            if (legacyName != null) {
                entity.customName(legacyName);
            }

            if (entity instanceof Ageable ageableEntity) {
                if (nbtContainer.hasTag("Age")) {
                    ageableEntity.setAge(nbtContainer.getInteger("Age"));
                }

                if (nbtContainer.hasTag("IsBaby")) {
                    if (nbtContainer.getBoolean("IsBaby")) {
                        ageableEntity.setBaby();
                    } else {
                        ageableEntity.setAdult();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Component takeLegacyCustomName(NBTContainer nbtContainer) {
        if (nbtContainer.getType("CustomName") != NBTType.NBTTagString) {
            return null;
        }

        String raw = nbtContainer.getString("CustomName");
        if (raw == null) {
            return null;
        }

        String trimmed = raw.trim();
        if (!trimmed.startsWith("{") && !trimmed.startsWith("[") && !trimmed.startsWith("\"")) {
            return null;
        }

        try {
            Component name = GsonComponentSerializer.gson().deserialize(raw);
            nbtContainer.removeKey("CustomName");
            return name;
        } catch (Exception e) {
            return null;
        }
    }

    public static String isCustomEntity(Entity hitEntity) {
        NBTEntity nbtEntity = new NBTEntity(hitEntity);
        return nbtEntity.getString("Paper.SpawnReason");
    }
}
