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
import com.github.nutt1101.command.Command;
import com.github.nutt1101.command.TabComplete;

public class CatchBall extends JavaPlugin {
    private FileConfiguration config = this.getConfig();
    public static Plugin plugin;
    private ExecutorService executorService;
    private Metrics metrics;

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

        // Initialize metrics with proper shutdown handling
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
    }

    @Override
    public void onDisable() {
        // Properly shutdown executor service
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

        // Shutdown metrics
        if (metrics != null) {
            metrics.shutdown();
        }

        // Cancel all tasks registered by this plugin
        getServer().getScheduler().cancelTasks(this);
    }

    public void registerEvent() {
        PluginManager registerEvent = this.getServer().getPluginManager();
        registerEvent.registerEvents(new HitEvent(), this);
        registerEvent.registerEvents(new DropGoldEgg(), this);
        registerEvent.registerEvents(new SkullClick(), this);
        registerEvent.registerEvents(new GUIClick(), this);
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

    // Method to safely execute async tasks
    public void executeAsync(Runnable task) {
        if (!executorService.isShutdown()) {
            executorService.execute(task);
        }
    }
}