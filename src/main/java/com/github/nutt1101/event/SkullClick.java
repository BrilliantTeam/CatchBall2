package com.github.nutt1101.event;

import cn.handyplus.lib.adapter.HandySchedulerUtil;
import cn.handyplus.lib.adapter.PlayerSchedulerUtil;
import com.bekvon.bukkit.residence.api.ResidenceApi;
import com.bekvon.bukkit.residence.containers.Flags;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import com.github.nutt1101.*;
import com.github.nutt1101.utils.NBTHandler;
import com.github.nutt1101.utils.TranslationFileReader;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public class SkullClick implements Listener {
    private final Plugin plugin = CatchBall.plugin;
    private final HashMap<UUID, Long> lastClickTime = new HashMap<>();

    @EventHandler
    public void skullClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = (event.getItem() != null) ? event.getItem() : new ItemStack(Material.AIR);

        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (!item.getType().equals(Material.PLAYER_HEAD)) { return; }

            ItemMeta itemMeta = item.getItemMeta();
            PersistentDataContainer data = itemMeta.getPersistentDataContainer();

            if (data.has(new NamespacedKey(plugin, "skullData"), PersistentDataType.STRING)) {
                String path = data.get(new NamespacedKey(plugin, "skullData"), PersistentDataType.STRING);

                if (path == null) {
                    player.sendMessage(ConfigSetting.toChat(TranslationFileReader.skullDoesNotFound, "", ""));
                    event.setCancelled(true);
                    return;
                }

                Location location = event.getClickedBlock().getLocation();

                if (!new HitEvent().resCheck(player, location)) {
                    event.setCancelled(true);
                    return;
                }

                if (!new HitEvent().gfCheck(player, location)) {
                    event.setCancelled(true);
                    return;
                }

                ClaimedResidence residence = ResidenceApi.getResidenceManager().getByLoc(location);
                boolean bypassPermissions = false;

                if (residence != null && !player.isOp() && !player.hasPermission("catchball.op")) {
                    String[] requiredFlags = {"animals", "canimals", "monsters", "cmonsters"};
                    List<String> missingFlags = new ArrayList<>();
                    for (String flag : requiredFlags) {
                        try {
                            if (!residence.getPermissions().playerHas(player, Flags.valueOf(flag.toLowerCase()), true)) {
                                missingFlags.add(flag);
                            }
                        } catch (IllegalArgumentException e) {
                            plugin.getLogger().warning("Invalid Residence flag: " + flag);
                        }
                    }

                    if (!missingFlags.isEmpty()) {
                        long currentTime = System.currentTimeMillis();
                        UUID playerUUID = player.getUniqueId();

                        if (lastClickTime.containsKey(playerUUID) && (currentTime - lastClickTime.get(playerUUID) <= 10000)) {
                            bypassPermissions = true;
                            lastClickTime.remove(playerUUID);
                        } else {
                            String missingFlagsString = String.join("§7、§e", missingFlags);
                            player.sendMessage("§7｜§6系統§7｜§f飯娘：§7此領地未開啟 §e" + missingFlagsString + "§7 權限。\n§7｜§6系統§7｜§f飯娘：§c放出後有可能會導致生物遺失！\n§7｜§6系統§7｜§f飯娘：§c10 秒內§7再次點擊可強制放置。");
                            lastClickTime.put(playerUUID, currentTime);
                            event.setCancelled(true);
                            return;
                        }
                    }
                }

                if (bypassPermissions || residence == null || residence.getOwnerUUID().equals(player.getUniqueId()) || player.isOp() || player.hasPermission("catchball.op")) {
                    try {
                        EntityType entityType = EntityType.valueOf(data.get(new NamespacedKey(plugin, "entityType"), PersistentDataType.STRING));
                        Location clickLocation = event.getClickedBlock().getLocation();

                        clickLocation.setX(clickLocation.getBlockX() + 0.5);
                        clickLocation.setZ(clickLocation.getBlockZ() + 0.5);

                        for (int i = 0; i < 3; i++) {
                            if (clickLocation.getBlock().getType().equals(Material.AIR) || clickLocation.getBlock().getType().equals(Material.WATER)) { break; }
                            clickLocation.setY(clickLocation.getY() + 1D);

                            if (i == 2) {
                                player.sendMessage(ConfigSetting.toChat(TranslationFileReader.locationUnsafe, "", ""));
                                event.setCancelled(true);
                                return;
                            }
                        }

                        Entity entity = player.getWorld().spawnEntity(clickLocation, entityType);

                        NBTHandler.loadEntityNBT(plugin, entity, data);
                        PlayerSchedulerUtil.teleport(entity, clickLocation);

                        event.getItem().setAmount(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
