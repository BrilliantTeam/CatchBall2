package com.github.nutt1101.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;


public class BrilliantDropInHook {

    private static final NamespacedKey ITEM = NamespacedKey.fromString("brilliantdropin:item");
    private static final String PERMISSION = "dropin.use";

    public static void drop(Player player, Location location, ItemStack stack) {
        if (enabled(player) && Bukkit.isOwnedByCurrentRegion(player)) {
            player.getInventory().addItem(stack).values()
                    .forEach(left -> player.getWorld().dropItem(player.getLocation(), left));
            return;
        }
        Drop2InventoryHook.registerDrop(player, location);
        location.getWorld().dropItem(location, stack);
    }

    private static boolean enabled(Player player) {
        return player != null && player.isOnline()
                && Bukkit.getPluginManager().isPluginEnabled("BrilliantDropIn")
                && player.hasPermission(PERMISSION)
                && player.getPersistentDataContainer().getOrDefault(ITEM, PersistentDataType.BYTE, (byte) 0) != 0;
    }
}
