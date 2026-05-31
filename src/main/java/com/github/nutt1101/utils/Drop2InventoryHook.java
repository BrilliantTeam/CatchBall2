package com.github.nutt1101.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Method;

public class Drop2InventoryHook {

    private static volatile boolean initialized = false;
    private static Method registerSimpleMethod = null;
    private static Method hasDrop2InvEnabled = null;

    private static void tryInit() {
        initialized = true;
        try {
            Plugin d2i = Bukkit.getPluginManager().getPlugin("Drop2InventoryPlus");
            if (d2i == null || !d2i.isEnabled()) return;
            ClassLoader cl = d2i.getClass().getClassLoader();
            Class<?> dom = Class.forName("de.jeff_media.drop2inventory.handlers.DropOwnerManager", true, cl);
            registerSimpleMethod = dom.getMethod("registerSimple", Player.class, Location.class);
            Class<?> pc = Class.forName("de.jeff_media.drop2inventory.handlers.PermissionChecker", true, cl);
            hasDrop2InvEnabled = pc.getMethod("hasDrop2InvEnabled", Player.class);
        } catch (Exception ignored) {}
    }

    public static void registerDrop(Player player, Location location) {
        if (player == null || !player.isOnline()) return;
        if (!initialized) tryInit();
        if (registerSimpleMethod == null) return;
        try {
            if (hasDrop2InvEnabled != null && !(boolean) hasDrop2InvEnabled.invoke(null, player)) return;
            registerSimpleMethod.invoke(null, player, location);
        } catch (Exception ignored) {}
    }
}
