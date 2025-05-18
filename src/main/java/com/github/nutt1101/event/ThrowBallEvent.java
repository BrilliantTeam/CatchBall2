package com.github.nutt1101.event;

import com.github.nutt1101.ConfigSetting;
import com.github.nutt1101.items.Ball;
import com.github.nutt1101.utils.TranslationFileReader;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ThrowBallEvent implements Listener {

    private boolean handleBallThrow(Player player, ItemStack item) {
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

        Snowball snowball = player.launchProjectile(Snowball.class);
        snowball.setItem(Ball.makeBall());

        item.setAmount(item.getAmount() - 1);
        return true;
    }

    //方塊交互的處理，非必要，所以註解。
    //@EventHandler(priority = EventPriority.HIGHEST)
    //public void onPlayerThrowBall(PlayerInteractEvent event) {
    //    if (event.getAction() != org.bukkit.event.block.Action.RIGHT_CLICK_AIR && 
    //        event.getAction() != org.bukkit.event.block.Action.RIGHT_CLICK_BLOCK) {
    //        return;
    //    }

    //    Player player = event.getPlayer();
    //    ItemStack item = player.getInventory().getItemInMainHand();

    //    if (handleBallThrow(player, item)) {
    //        event.setCancelled(true);
    //    }
    //}

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (handleBallThrow(player, item)) {
            event.setCancelled(true);
        }
    }
}