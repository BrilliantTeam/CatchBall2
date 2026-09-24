package com.github.nutt1101.event;

import com.github.nutt1101.ConfigSetting;
import com.github.nutt1101.items.Ball;
import com.github.nutt1101.utils.TranslationFileReader;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public class ThrowBallEvent implements Listener {
    private static final Set<String> INTERACTABLE_ENTITIES = Set.of(
        "VILLAGER",
        "WOLF",
        "CAT",
        "HORSE",
        "DONKEY",
        "MULE",
        "SKELETON_HORSE",
        "ZOMBIE_HORSE",
        "LLAMA",
        "TRADER_LLAMA",
        "PARROT",
        "WANDERING_TRADER",
        "ALLAY",
        "CAMEL",
        "CAMEL_HUSK",
        "NAUTILUS",
        "ZOMBIE_NAUTILUS",
        "HAPPY_GHAST"
    );

    private boolean handleBallThrow(Player player, ItemStack item, EntityType entityType) {
        if (item == null || !item.hasItemMeta() || 
            !item.getItemMeta().equals(Ball.makeBall().getItemMeta())) {
            return false;
        }

        if (!player.hasPermission("catchball.use")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', 
                ConfigSetting.toChat(TranslationFileReader.noPermissionToUse, "", "")
                    .replace("{BALL}", TranslationFileReader.catchBallName)));
            return false;
        }

        if (INTERACTABLE_ENTITIES.contains(entityType.name())) {
            Snowball snowball = player.launchProjectile(Snowball.class);
            snowball.setItem(Ball.makeBall());
            item.setAmount(item.getAmount() - 1);
        }

        return true;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof ItemFrame) {
            return;
        }

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        EntityType entityType = event.getRightClicked().getType();

        if (handleBallThrow(player, item, entityType)) {
            event.setCancelled(true);
        }
    }
}