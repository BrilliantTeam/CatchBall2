package com.github.nutt1101;

import java.util.logging.Level;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import cn.handyplus.lib.adapter.HandySchedulerUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.nutt1101.event.HitEvent;
import com.github.nutt1101.event.DropGoldEgg;
import com.github.nutt1101.event.SkullClick;
import com.github.nutt1101.event.GUIClick;
import com.github.nutt1101.event.ThrowBallEvent;
import com.github.nutt1101.command.Command;
import com.github.nutt1101.command.TabComplete;

public class CatchBall extends JavaPlugin {
    private FileConfiguration config = this.getConfig();
    public static Plugin plugin;
    private ExecutorService executorService;
    private Metrics metrics;
    private static boolean isFolia;

    static {
        try {
            Class.forName("io.papermc.paper.threadedregions.RegionScheduler");
            isFolia = true;
        } catch (ClassNotFoundException e) {
            isFolia = false;
        }
    }

    private void checkPluginHook(String pluginName) {
        if (this.getServer().getPluginManager().getPlugin(pluginName) != null) {
            plugin.getLogger().log(Level.INFO, ChatColor.GREEN + pluginName + " Hook!");
        }
    }

    @Override
    public void onEnable() {
        plugin = this;
        executorService = Executors.newCachedThreadPool();
        
        ConfigSetting.checkConfig();

        metrics = new Metrics(this, 12380);
        
        registerEvent();
        registerCommand();

        checkPluginHook("Residence");
        checkPluginHook("MythicMobs");
        checkPluginHook("GriefPrevention");
        checkPluginHook("Lands");
        checkPluginHook("PlaceholderAPI");
        checkPluginHook("RedProtect");
        checkPluginHook("SimpleClaimSystem");

        HandySchedulerUtil.init(this);
        
        if (isFolia) {
            getLogger().info("Detected Folia environment. Global Scheduler tasks will be handled accordingly.");
        }
    }

    @Override
    public void onDisable() {
        if (executorService != null) {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }

        if (metrics != null) {
            metrics.shutdown();
        }

        if (isFolia) {
            try {
                getServer().getGlobalRegionScheduler().cancelTasks(this);
                getServer().getAsyncScheduler().cancelTasks(this);
                getLogger().info("Folia schedulers (Global & Async) tasks cancelled.");
            } catch (NoSuchMethodError | NoClassDefFoundError e) {
                getLogger().warning("Folia detected but failed to access new Scheduler API.");
            }
        } else {
            try {
                getServer().getScheduler().cancelTasks(this);
            } catch (Exception e) {
                getLogger().warning("Failed to cancel Bukkit Scheduler tasks.");
            }
        }
    }

    public void registerEvent() {
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new HitEvent(), this);
        pm.registerEvents(new DropGoldEgg(), this);
        pm.registerEvents(new SkullClick(), this);
        pm.registerEvents(new GUIClick(), this);
        pm.registerEvents(new ThrowBallEvent(), this);
    }

    public void registerCommand() {
        PluginCommand ctbCommand = this.getCommand("ctb");
        if (ctbCommand != null) {
            ctbCommand.setExecutor(new Command());
            ctbCommand.setTabCompleter(new TabComplete());
        }
    }

    public static String getServerVersion() {
        return plugin.getServer().getBukkitVersion();
    }

    public void executeAsync(Runnable task) {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.execute(task);
        }
    }
    
    public static boolean isFolia() {
        return isFolia;
    }
}
