package com.github.nutt1101.codex;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Collection;
import java.util.Locale;
import java.util.logging.Level;

public final class Toasts {

    private static final String CRITERION = "unlocked";

    private final Plugin plugin;

    private boolean enabled;
    private long revokeDelayTicks = 2;

    public Toasts(Plugin plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("deprecation")
    public void register(Collection<String> entries, boolean enabled, String description, long revokeDelayTicks) {
        this.enabled = enabled;
        this.revokeDelayTicks = revokeDelayTicks;
        if (!enabled) {
            return;
        }
        for (String entry : entries) {
            NamespacedKey key = key(entry);
            if (Bukkit.getAdvancement(key) != null) {
                continue;
            }
            String frame = entry.contains(":") ? "goal" : "task";
            String title = CodexService.value(entry, "DisplayName");
            try {
                Bukkit.getUnsafe().loadAdvancement(key, json(CodexService.value(entry, "Skull"), title, description, frame));
            } catch (Exception withSkull) {
                try {
                    Bukkit.getUnsafe().loadAdvancement(key, json(null, title, description, frame));
                } catch (Exception exception) {
                    plugin.getLogger().log(Level.WARNING,
                            "Failed to register the codex toast for " + entry + ".", exception);
                }
            }
        }
    }

    public void show(Player player, String entry) {
        if (!enabled) {
            return;
        }
        Advancement advancement = Bukkit.getAdvancement(key(entry));
        if (advancement == null) {
            return;
        }
        AdvancementProgress progress = player.getAdvancementProgress(advancement);
        if (progress.isDone()) {
            progress.revokeCriteria(CRITERION);
        }
        progress.awardCriteria(CRITERION);
        player.getScheduler().runDelayed(plugin,
                task -> player.getAdvancementProgress(advancement).revokeCriteria(CRITERION),
                null, Math.max(1, revokeDelayTicks));
    }

    private NamespacedKey key(String entry) {
        return new NamespacedKey(plugin, "codex/" + entry.toLowerCase(Locale.ROOT).replace(':', '/'));
    }

    private String json(String skull, String title, String description, String frame) {
        JsonObject icon = new JsonObject();
        icon.addProperty("id", "minecraft:player_head");
        if (skull != null && !skull.isEmpty()) {
            JsonObject property = new JsonObject();
            property.addProperty("name", "textures");
            property.addProperty("value", skull);
            JsonArray properties = new JsonArray();
            properties.add(property);
            JsonObject profile = new JsonObject();
            profile.add("properties", properties);
            JsonObject components = new JsonObject();
            components.add("minecraft:profile", profile);
            icon.add("components", components);
        }

        JsonObject display = new JsonObject();
        display.add("icon", icon);
        display.add("title", component(title));
        display.add("description", component(description));
        display.addProperty("frame", frame);
        display.addProperty("show_toast", true);
        display.addProperty("announce_to_chat", false);
        display.addProperty("hidden", true);

        JsonObject trigger = new JsonObject();
        trigger.addProperty("trigger", "minecraft:impossible");
        JsonObject criteria = new JsonObject();
        criteria.add(CRITERION, trigger);

        JsonArray requirement = new JsonArray();
        requirement.add(CRITERION);
        JsonArray requirements = new JsonArray();
        requirements.add(requirement);

        JsonObject root = new JsonObject();
        root.add("display", display);
        root.add("criteria", criteria);
        root.add("requirements", requirements);
        return root.toString();
    }

    private static com.google.gson.JsonElement component(String legacy) {
        return JsonParser.parseString(GsonComponentSerializer.gson()
                .serialize(LegacyComponentSerializer.legacyAmpersand().deserialize(legacy == null ? "" : legacy)));
    }
}
