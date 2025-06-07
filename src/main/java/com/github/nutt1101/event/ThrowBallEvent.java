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
    private static final Set<EntityType> INTERACTABLE_ENTITIES = Set.of(
        EntityType.VILLAGER,
        EntityType.WOLF,
        EntityType.CAT,
        EntityType.HORSE,
        EntityType.DONKEY,
        EntityType.MULE,
        EntityType.LLAMA,
        EntityType.TRADER_LLAMA,
        EntityType.PARROT,
        EntityType.WANDERING_TRADER,
        EntityType.CAMEL
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

        if (INTERACTABLE_ENTITIES.contains(entityType)) {
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