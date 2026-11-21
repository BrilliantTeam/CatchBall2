package com.github.nutt1101;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.stream.Collectors;

import com.bekvon.bukkit.residence.containers.Flags;

import com.github.nutt1101.Recipe.BallRecipe;
import com.github.nutt1101.utils.TranslationFileReader;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;

public class ConfigSetting {
    private final static Plugin plugin = CatchBall.plugin;
    public static String locale;
    public static List<EntityType> catchableEntity = new ArrayList<>();
    public static boolean chickenDropGoldEgg;
    public static int chickenDropGoldEggChance;
    public static String catchSuccessSound;
    public static YamlConfiguration entityFile;
    public static boolean recipeEnabled;

    public static List<String> residenceFlag = new ArrayList<>();

    public static boolean allowCatchableTamedOwnerIsNull;
    public static boolean ShowParticles;
    public static String CustomParticles;
    public static double catchFailRate;
    public static boolean UseRes;
    public static boolean UsePAPI;
    public static boolean codexEnabled;
    public static boolean codexToast;
    public static long codexToastRevokeDelay;
    public static List<String> codexExcludedVariants = new ArrayList<>();
    public static boolean databaseEnabled;
    public static String databaseUrl;
    public static String databaseUsername;
    public static String databasePassword;
    public static String databaseTable;

    /**
     * Initialize or reload the plugin
     */
    public static void checkConfig() {
        // check if the file exists
        if (!new File(plugin.getDataFolder(), "config.yml").exists()) {
            plugin.saveResource("config.yml", false);
        }

        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();

        locale = config.isSet("Locale") ? config.getString("Locale") : "en";

        entityFileCreate();

        chickenDropGoldEgg = !config.isSet("ChickenDropGoldEgg") || config.getBoolean("ChickenDropGoldEgg");

        entityFile = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "entity.yml"));
        InputStream defaultEntityFile = plugin.getResource("entity/" + locale + ".yml");
        if (defaultEntityFile != null) entityFile.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defaultEntityFile, StandardCharsets.UTF_8)));

        chickenDropGoldEggChance = config.isSet("ChickenDropGoldEggChance")
                ? Integer.parseInt(config.getString("ChickenDropGoldEggChance").replace("%", ""))
                : 50;

        catchSuccessSound = config.isSet("CatchSuccessSound") ? config.getString("CatchSuccessSound").toUpperCase()
                : "ENTITY_ARROW_HIT_PLAYER".toUpperCase();

        recipeEnabled = !config.isSet("Recipe.enabled") || config.getBoolean("Recipe.enabled");

        residenceFlag = config.isSet("ResidenceFlag") ? config.getStringList("ResidenceFlag")
                : Arrays.asList("animalkilling");

        allowCatchableTamedOwnerIsNull = !config.isSet("AllowCatchableTamedOwnerIsNull")
                || config.getBoolean("AllowCatchableTamedOwnerIsNull");
        ShowParticles = !config.isSet("ShowParticles")
                || config.getBoolean("ShowParticles");
        CustomParticles = config.isSet("CustomParticles") ? config.getString("CustomParticles") : "CLOUD";
        catchFailRate = !config.isSet("catchFailRate") ? config.getDouble("catchFailRate")
        : 0.1;

        try {
            TranslationFileReader.init();
        } catch (IOException e) {
            e.printStackTrace();
            plugin.getLogger().log(Level.WARNING,
                    ChatColor.RED + String.format("The locale you have selected '%s' is currently not supported",
                            locale));
            plugin.getLogger().log(Level.WARNING, ChatColor.RED + "We only support: en, zh_tw");
            locale = "en";
        }

        new BallRecipe();

        if (!catchableEntity.isEmpty()) {
            catchableEntity.clear();
        }

        try {
            if (plugin.getServer().getPluginManager().getPlugin("Residence") != null) {
                residenceFlag = residenceFlag.stream().map(flag -> Flags.valueOf(flag)).map(Flags::name)
                        .collect(Collectors.toList());
            }

        } catch (IllegalArgumentException e) {
            plugin.getLogger().log(Level.WARNING, ChatColor.RED + e.getMessage());
            plugin.getLogger().log(Level.WARNING, ChatColor.RED + "Unknown Residence flag!");
            plugin.getLogger().log(Level.WARNING, ChatColor.RED + "Please check your config setting!");
            residenceFlag.clear();
            residenceFlag.add("animalkilling");
        }

        for (String entity : config.getStringList("CatchableEntity")) {
            try {
                EntityType.valueOf(entity.toUpperCase());// entityType only can receive UpperCase words
                catchableEntity.add(EntityType.valueOf(entity.toUpperCase()));

                // There is a common issue that you put an unknown entityType in the list of CatchableEntity
            } catch (IllegalArgumentException e) {
            }
        }

        UseRes = !config.isSet("UseRes")
                || config.getBoolean("UseRes");

        UsePAPI = !config.isSet("UsePAPI")
                || config.getBoolean("UsePAPI");

        codexEnabled = config.getBoolean("Codex.Enabled", true);
        codexToast = config.getBoolean("Codex.Toast.Enabled", true);
        codexToastRevokeDelay = config.getLong("Codex.Toast.RevokeDelayTicks", 2);
        codexExcludedVariants = config.isSet("Codex.ExcludedVariants")
                ? config.getStringList("Codex.ExcludedVariants").stream().map(String::toUpperCase).collect(Collectors.toList())
                : List.of("SHULKER", "RABBIT:THE_KILLER_BUNNY");
        databaseEnabled = config.getBoolean("Database.Enabled", false);
        databaseUrl = "jdbc:mysql://" + config.getString("Database.Host", "localhost")
                + ":" + config.getInt("Database.Port", 3306)
                + "/" + config.getString("Database.Database", "minecraft")
                + config.getString("Database.Parameters", "");
        databaseUsername = config.getString("Database.Username", "root");
        databasePassword = config.getString("Database.Password", "");
        databaseTable = config.getString("Database.TablePrefix", "catchball_") + "codex";
    }

    /**
     * Check the version of the server to determine the entity.yml file output version.
     */
    public static void entityFileCreate() {
        File file = new File(plugin.getDataFolder(), "entity.yml");
        InputStream inputStream = plugin.getResource("entity/" + locale + ".yml");

        if (file.exists()) return;

        if (inputStream != null) {
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                inputStream.transferTo(outputStream);
                outputStream.close();
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            plugin.getLogger().log(Level.WARNING, ChatColor.RED + "Unknown Error make plugin file can not place!");
        }
    }

    public static Set<String> getEntityList() {
        Set<String> entityList = new LinkedHashSet<>(entityFile.getConfigurationSection("EntityList").getKeys(false));
        entityList.removeIf(entity -> Arrays.stream(EntityType.values()).noneMatch(type -> type.name().equals(entity)));
        return entityList;
    }

    /**
     * Get entityDisplayName saved on entity.yml.
     *
     * @param entityName name of saved entity
     * @return entityDisplayName
     */
    public static String getEntityDisplayName(String entityName) {
        if (entityFile.getConfigurationSection("EntityList").contains(entityName)) {
            return entityFile.getString("EntityList." + entityName.toUpperCase() + ".DisplayName");
        }
        return entityName;
    }

    /**
     * Replace the message argument from config.yml.
     *
     * @param location replace {LOCATION} from source message
     * @param entity   replace {ENTITY} from source message
     * @return replaced message
     */
    public static String toChat(String message, String location, String entity) {
        message = message.contains("{BALL}") ? message.replace("{BALL}", TranslationFileReader.catchBallName)
                : message;
        message = message.contains("{LOCATION}") ? message.replace("{LOCATION}", location) : message;
        message = message.contains("{ENTITY}") ? message.replace("{ENTITY}", getEntityDisplayName(entity)) : message;

        return ChatColor.translateAlternateColorCodes('&', message);
    }

    /**
     * Save the entity.yml file if the catchableEntity changes.
     */
    public static void saveEntityList() {
        FileConfiguration fileConfiguration = plugin.getConfig();
        List<String> savelist = new ArrayList<>();

        ConfigSetting.catchableEntity.forEach(e -> savelist.add(e.toString()));

        fileConfiguration.set("CatchableEntity", savelist.toArray());

        try {
            fileConfiguration.save(new File(plugin.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isLatestVersion(String current, String latest) {

        String[] currentParts = current.split("\\.");

        String[] latestParts = latest.split("\\.");

        int minLength = Math.min(currentParts.length, latestParts.length);

        for (int i = 0; i < minLength; i++) {

            int currentPart = Integer.parseInt(currentParts[i]);

            int latestPart = Integer.parseInt(latestParts[i]);

            if (currentPart < latestPart) return false;

            if (currentPart > latestPart) return true;
        }
        return currentParts.length >= latestParts.length;
    }
}