package com.github.nutt1101.event;

import com.bekvon.bukkit.residence.api.ResidenceApi;
import com.bekvon.bukkit.residence.containers.Flags;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import com.github.nutt1101.*;
import com.github.nutt1101.items.Ball;
import com.github.nutt1101.utils.BrilliantDropInHook;
import com.github.nutt1101.utils.NBTHandler;
import com.github.nutt1101.utils.TranslationFileReader;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.projectiles.BlockProjectileSource;

import java.util.List;
import java.util.Objects;


public class HitEvent implements Listener {
    private List<EntityType> catchableEntity = ConfigSetting.catchableEntity;
    private Location hitLocation;
    private final Plugin plugin = CatchBall.plugin;
    @EventHandler
    public void CatchBallHitEvent(ProjectileHitEvent event){

        // check if shooter is a player
        if (event.getEntity().getShooter() instanceof Player) {
            Player player = (Player) event.getEntity().getShooter();

            if (!checkCatchBall(event.getEntity())) { return; }

            if (!player.hasPermission("catchball.use")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigSetting.toChat(TranslationFileReader.noPermissionToUse,
                        getCoordinate(event.getHitBlock() == null ? Objects.requireNonNull(event.getHitEntity()).getLocation() : event.getHitBlock().getLocation())
                        , "").replace("{BALL}", TranslationFileReader.catchBallName)));

                event.getEntity().remove();

                if (event.getHitEntity() != null) {
                    Entity hitEntity = event.getHitEntity();
                    hitLocation = hitEntity.getLocation();
                    BrilliantDropInHook.drop(player, hitLocation, Ball.makeBall());
                } else {
                    BrilliantDropInHook.drop(player, event.getHitBlock().getLocation(), Ball.makeBall());
                }

                event.setCancelled(true);

                return;
            }

            event.setCancelled(true);

            event.getEntity().remove();
            // hit a entity
            if (event.getHitEntity() != null) {

                if (!resCheck(player, event.getHitEntity().getLocation()) && ConfigSetting.UseRes) {
                    BrilliantDropInHook.drop(player, event.getHitEntity().getLocation(), Ball.makeBall());
                    player.sendMessage(ConfigSetting.toChat(TranslationFileReader.canNotCatchable, getCoordinate(event.getHitEntity().getLocation()), ""));
                    return;
                }

                if (event.getHitEntity() instanceof Tameable tameable) {
                    Player shooter = (Player) event.getEntity().getShooter();
                    if (tameable.isTamed()) {
                        boolean isNullOwnerValue = tameable.getOwner() == null;
                        boolean sameOwner = isNullOwnerValue ? true : tameable.getOwner().getName().equals(shooter.getName());
                        if ((isNullOwnerValue && !ConfigSetting.allowCatchableTamedOwnerIsNull) || !sameOwner) {
                            BrilliantDropInHook.drop(player, event.getHitEntity().getLocation(), Ball.makeBall());
                            player.sendMessage(ConfigSetting.toChat(TranslationFileReader.canNotCatchable, getCoordinate(event.getHitEntity().getLocation()), ""));
                            return;
                        }
                    }

                }

                Entity hitEntity = event.getHitEntity();
                hitLocation = hitEntity.getLocation();

                String checkCustom = getIsCustomEntity(hitEntity);


                // check if the hitEntity is a catchable entity. on config.yml CatchableEntity
                for (EntityType entity : catchableEntity) {
                    if (hitEntity.getType().equals(entity) && !(hitEntity instanceof Player) && !checkCustom.equals("CUSTOM")) {
                        if(Math.random() < ConfigSetting.catchFailRate) {
                            BrilliantDropInHook.drop(player, hitLocation, Ball.makeBall());
                            player.sendMessage(ConfigSetting.toChat(TranslationFileReader.catchFail, getCoordinate(hitLocation), entity.toString()));
                            return;
                        }
                        // hitEntity.getWorld().dropItem(hitEntity.getLocation(), entityToItemStack(entity));
                        if (!(ConfigSetting.catchSuccessSound.equals("FALSE"))) {
                            player.playSound(player.getLocation(), Sound.valueOf(ConfigSetting.catchSuccessSound), 1f, 1f);
                        }

                        event.getHitEntity().remove();

                        BrilliantDropInHook.drop(player, hitLocation, new HeadDrop().getEntityHead(event.getHitEntity(), player));
                        if (ConfigSetting.ShowParticles) {
                            hitEntity.getWorld().spawnParticle(Particle.valueOf(ConfigSetting.CustomParticles), hitLocation, 1);
                        }

                        player.sendMessage(ConfigSetting.toChat(TranslationFileReader.catchSuccess, getCoordinate(hitLocation), entity.toString()));
                        CatchBall.codex.record(player, hitEntity);
                        return;
                    }
                }

                // if player hit a can not be catch entity, catchBall will be return
                player.sendMessage(ConfigSetting.toChat(TranslationFileReader.canNotCatchable, getCoordinate(hitLocation), ""));
                BrilliantDropInHook.drop(player, hitLocation, Ball.makeBall());

                // hit block, catchBall will be return
            } else if (event.getHitBlock() != null) {

                event.getEntity().remove();

                hitLocation = event.getHitBlock().getLocation();
                player.sendMessage(ConfigSetting.toChat(TranslationFileReader.ballHitBlock, getCoordinate(hitLocation), ""));

                BrilliantDropInHook.drop(player, hitLocation, Ball.makeBall());
                return;
            }

        } else if (event.getEntity().getShooter() instanceof BlockProjectileSource){

            if (!checkCatchBall(event.getEntity())) { return; }

            event.setCancelled(true);
            event.getEntity().remove();
            // hit a entity
            if (event.getHitEntity() != null) {
                Entity hitEntity = (Entity) event.getHitEntity();
                hitLocation = hitEntity.getLocation();

                String checkCustom = getIsCustomEntity(hitEntity);

                // check if the hitEntity is a catchable entity. on config.yml CatchableEntity
                for (EntityType entity : catchableEntity) {
                    if (hitEntity.getType().equals(entity) && !(hitEntity instanceof Player) && !checkCustom.equals("CUSTOM")) {
                        hitEntity.remove();
                        hitEntity.getWorld().dropItem(hitLocation, new HeadDrop().getEntityHead(event.getHitEntity(), null));

                        return;
                    }
                }

                hitEntity.getWorld().dropItem(hitLocation, Ball.makeBall());

            } else if (event.getHitBlock() != null) {
                hitLocation = event.getHitBlock().getLocation();
                event.getHitBlock().getWorld().dropItem(event.getHitBlock().getLocation(), Ball.makeBall());
                return;
            }
        }

        return;
    }


    // config text will be use this method , so put on this class
    public static String getCoordinate(Location location) {

        return location.getBlockX() + ", " +
                location.getBlockY() + ", " +
                location.getBlockZ();
    }

    public boolean resCheck(Player player, Location location) {
        if (plugin.getServer().getPluginManager().getPlugin("Residence") == null) { return true; }

        if (ResidenceApi.getResidenceManager().getByLoc(location) == null) { return true; }

        ClaimedResidence residence = ResidenceApi.getResidenceManager().getByLoc(location);

        if (residence.getOwnerUUID().equals(player.getUniqueId()) || player.isOp() || player.hasPermission("catchball.op")) { return true; }

        for (String flags : ConfigSetting.residenceFlag) {
            if (!residence.getPermissions().playerHas(player, Flags.valueOf(flags.toLowerCase()) , true)) {

                player.sendMessage(ConfigSetting.toChat(TranslationFileReader.noResidencePermissions, "", "").
                        replace("{FLAG}", flags));

                return false;
            }
        }

        return true;
    }

    public boolean checkCatchBall(Projectile projectile) {
        if (!(projectile instanceof Snowball)) { return false; }

        if (projectile instanceof ThrowableProjectile) {
            ThrowableProjectile throwableProjectile = (ThrowableProjectile) projectile;
            if (!Objects.requireNonNull(throwableProjectile.getItem().getItemMeta()).equals(Ball.makeBall().getItemMeta())) { return false; }
        }

        return true;
    }

    public String getIsCustomEntity(Entity hitEntity) {
        String checkCustom = null;

        checkCustom = NBTHandler.isCustomEntity(hitEntity);

        return checkCustom;
    }

}
