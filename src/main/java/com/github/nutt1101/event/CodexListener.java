package com.github.nutt1101.event;

import com.github.nutt1101.CatchBall;
import com.github.nutt1101.codex.CodexMenu;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CodexListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        CatchBall.codex.load(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        CatchBall.codex.unload(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        if (event.getInventory().getHolder() instanceof CodexMenu) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof CodexMenu menu)) {
            return;
        }
        event.setCancelled(true);
        if (!(event.getWhoClicked() instanceof Player player)) {
            return;
        }
        int page = menu.page();
        boolean variantTab = menu.variantTab();
        if (event.getRawSlot() == CodexMenu.TAB_SLOT) {
            page = 0;
            variantTab = !variantTab;
        } else if (event.getRawSlot() == CodexMenu.PREVIOUS_SLOT && page > 0) {
            page--;
        } else if (event.getRawSlot() == CodexMenu.NEXT_SLOT && page + 1 < menu.pages()) {
            page++;
        } else {
            return;
        }
        int target = page;
        boolean tab = variantTab;
        player.getScheduler().run(CatchBall.plugin, task -> {
            CatchBall.codex.open(player, target, tab);
            player.playSound(player.getLocation(), Sound.ITEM_BOOK_PAGE_TURN, 1.0f, 1.0f);
        }, null);
    }
}
