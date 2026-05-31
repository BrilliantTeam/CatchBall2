package com.github.nutt1101.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;

public class Drop2InventoryHook {

    private static volatile boolean initialized = false;
    private static boolean available = false;
    private static Method registerSimpleMethod = null;

    private static void tryInit() {
        initialized = true;
        try {
            Class<?> managerClass = Class.forName("de.jeff_media.drop2inventory.handlers.DropOwnerManager");
            registerSimpleMethod = managerClass.getMethod("registerSimple", Player.class, Location.class);
            available = true;
        } catch (ClassNotFoundException | NoSuchMethodException ignored) {
        }
    }

    /**
     * Registers the drop owner so Drop2Inventory-Plus can auto-collect the item
     * when world.dropItem() is called at the same location immediately after.
     * Lazy-initializes on first call to avoid triggering D2I's static block too early.
     */
    public static void registerDrop(Player player, Location location) {
        if (!initialized) tryInit();
        if (!available || registerSimpleMethod == null) return;
        try {
            registerSimpleMethod.invoke(null, player, location);
        } catch (Exception ignored) {
        }
    }
}
