package com.github.nutt1101.codex;

import com.github.nutt1101.ConfigSetting;
import com.github.nutt1101.HeadDrop;
import com.github.nutt1101.utils.TranslationFileReader;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class CodexService {

    private final Plugin plugin;
    private final CodexDatabase database;
    private final Toasts toasts;
    private final Map<UUID, Map<String, CodexEntry>> cache = new ConcurrentHashMap<>();

    public CodexService(Plugin plugin) {
        this.plugin = plugin;
        this.database = new CodexDatabase(plugin.getLogger(), ConfigSetting.databaseUrl,
                ConfigSetting.databaseUsername, ConfigSetting.databasePassword, ConfigSetting.databaseTable);
        this.toasts = new Toasts(plugin);
        if (ConfigSetting.databaseEnabled) {
            database.initialise();
        }
        reload();
    }

    public void reload() {
        if (!ConfigSetting.codexEnabled) {
            return;
        }
        List<String> entries = new ArrayList<>(species());
        entries.addAll(variants());
        toasts.register(entries, ConfigSetting.codexToast,
                TranslationFileReader.localeYamlConfig.getString("Codex.ToastDescription", ""),
                ConfigSetting.codexToastRevokeDelay);
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!isLoaded(player.getUniqueId())) {
                load(player.getUniqueId());
            }
        }
    }

    public void load(UUID uuid) {
        if (!ConfigSetting.codexEnabled) {
            return;
        }
        if (ConfigSetting.databaseEnabled) {
            database.load(uuid, unlocked -> cache.put(uuid, new ConcurrentHashMap<>(unlocked)));
        } else {
            cache.put(uuid, new ConcurrentHashMap<>());
        }
    }

    public void unload(UUID uuid) {
        cache.remove(uuid);
    }

    public boolean isLoaded(UUID uuid) {
        return cache.containsKey(uuid);
    }

    public Map<String, CodexEntry> unlocked(UUID uuid) {
        return cache.getOrDefault(uuid, Map.of());
    }

    public void open(Player player, int page, boolean variantTab) {
        player.openInventory(new CodexMenu(unlocked(player.getUniqueId()), page, variantTab).getInventory());
    }

    public void record(Player player, Entity entity) {
        Map<String, CodexEntry> unlocked = cache.get(player.getUniqueId());
        if (!ConfigSetting.codexEnabled || unlocked == null) {
            return;
        }
        String type = entity.getType().name();
        record(player, unlocked, type);
        String variant = HeadDrop.getVariant(entity);
        if (variant != null && variants(type).contains(type + ":" + variant)) {
            record(player, unlocked, type + ":" + variant);
        }
    }

    private void record(Player player, Map<String, CodexEntry> unlocked, String entry) {
        boolean first = !unlocked.containsKey(entry);
        unlocked.merge(entry, new CodexEntry(System.currentTimeMillis(), 1),
                (previous, added) -> new CodexEntry(previous.unlockedAt(), previous.caught() + 1));
        if (ConfigSetting.databaseEnabled) {
            database.record(player.getUniqueId(), entry);
        }
        if (!first) {
            return;
        }
        player.getScheduler().run(plugin, task -> {
            toasts.show(player, entry);
            player.sendMessage(TranslationFileReader.codex("Unlocked", "ENTITY", value(entry, "DisplayName")));
        }, null);
    }

    public void unlockAll(UUID uuid, Collection<String> entries) {
        Map<String, CodexEntry> cached = cache.get(uuid);
        if (cached != null) {
            long now = System.currentTimeMillis();
            entries.forEach(entry -> cached.putIfAbsent(entry, new CodexEntry(now, 0)));
        }
        if (ConfigSetting.databaseEnabled) {
            database.unlockAll(uuid, entries);
        }
    }

    public void clear(UUID uuid) {
        Map<String, CodexEntry> cached = cache.get(uuid);
        if (cached != null) {
            cached.clear();
        }
        if (ConfigSetting.databaseEnabled) {
            database.clear(uuid);
        }
    }

    public void close() {
        cache.clear();
        database.close();
    }

    public static List<String> species() {
        List<String> species = new ArrayList<>();
        for (String type : ConfigSetting.getEntityList()) {
            if (ConfigSetting.catchableEntity.contains(EntityType.valueOf(type))) {
                species.add(type);
            }
        }
        return species;
    }

    public static List<String> variants(String type) {
        ConfigurationSection section = ConfigSetting.entityFile.getConfigurationSection("EntityList." + type + ".Variants");
        List<String> excluded = ConfigSetting.codexExcludedVariants;
        if (section == null || excluded.contains(type)) {
            return List.of();
        }
        List<String> variants = new ArrayList<>();
        for (String variant : section.getKeys(false)) {
            if (!excluded.contains(type + ":" + variant)) {
                variants.add(type + ":" + variant);
            }
        }
        return variants;
    }

    public static List<String> variants() {
        List<String> variants = new ArrayList<>();
        for (String type : species()) {
            variants.addAll(variants(type));
        }
        return variants;
    }

    public static String value(String entry, String field) {
        int split = entry.indexOf(':');
        return split < 0
                ? HeadDrop.getEntityValue(entry, null, field)
                : HeadDrop.getEntityValue(entry.substring(0, split), entry.substring(split + 1), field);
    }
}
