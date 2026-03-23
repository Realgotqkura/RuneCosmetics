package com.realgotqkura.runeCosmetics;

import com.realgotqkura.runeCosmetics.cmd.DebugCmd;
import com.realgotqkura.runeCosmetics.cmd.RuneCommand;
import com.realgotqkura.runeCosmetics.events.RuneEvents;
import com.realgotqkura.runeCosmetics.events.RuneGUIEvents;
import com.realgotqkura.runeCosmetics.gui.RunesGUI;
import com.realgotqkura.runeCosmetics.utils.RandomUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class RuneCosmetics extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        loadEvents();
        loadCommands();

        RandomUtils.loadNskContainer(this);
        RuneEvents.runProjectiles(this);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().log(Level.INFO, "Disabling RuneCosmetics!");
    }

    public void loadEvents(){
        this.getServer().getPluginManager().registerEvents(new RunesGUI(this), this);
        this.getServer().getPluginManager().registerEvents(new RuneEvents(), this);
        this.getServer().getPluginManager().registerEvents(new RuneGUIEvents(), this);
    }

    public void loadCommands(){
        this.getCommand("runes").setExecutor(new RuneCommand());
        this.getCommand("runes_debug").setExecutor(new DebugCmd());
    }
}
