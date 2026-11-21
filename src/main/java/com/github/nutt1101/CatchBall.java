package com.github.nutt1101;

import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.nutt1101.event.HitEvent;
import com.github.nutt1101.event.DropGoldEgg;
import com.github.nutt1101.event.SkullClick;
import com.github.nutt1101.event.GUIClick;
import com.github.nutt1101.event.ThrowBallEvent;
import com.github.nutt1101.event.CodexListener;
import com.github.nutt1101.codex.CodexService;
import com.github.nutt1101.command.Command;
import com.github.nutt1101.command.TabComplete;

public class CatchBall extends JavaPlugin {
    public static Plugin plugin;
    public static CodexService codex;

    private void checkPluginHook(String pluginName) {
        if (this.getServer().getPluginManager().getPlugin(pluginName) != null) {
            plugin.getLogger().log(Level.INFO, ChatColor.GREEN + pluginName + " Hook!");
        }
    }

    @Override
    public void onEnable() {
        plugin = this;

        ConfigSetting.checkConfig();
        codex = new CodexService(this);

        registerEvent();
        registerCommand();

        checkPluginHook("Residence");
        checkPluginHook("PlaceholderAPI");
        checkPluginHook("BrilliantDropIn");
    }

    @Override
    public void onDisable() {
        if (codex != null) {
            codex.close();
        }
    }

    public void registerEvent() {
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents(new HitEvent(), this);
        pm.registerEvents(new DropGoldEgg(), this);
        pm.registerEvents(new SkullClick(), this);
        pm.registerEvents(new GUIClick(), this);
        pm.registerEvents(new ThrowBallEvent(), this);
        pm.registerEvents(new CodexListener(), this);
    }

    public void registerCommand() {
        PluginCommand ctbCommand = this.getCommand("ctb");
        if (ctbCommand != null) {
            ctbCommand.setExecutor(new Command());
            ctbCommand.setTabCompleter(new TabComplete());
        }
    }
}
