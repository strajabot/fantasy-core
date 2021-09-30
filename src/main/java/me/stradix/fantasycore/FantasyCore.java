package me.stradix.fantasycore;

import me.stradix.fantasycore.listeners.LoadFantasyPlayerListener;
import me.stradix.fantasycore.listeners.UnloadFantasyPlayerListener;
import me.stradix.fantasycore.task.TaskManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class FantasyCore extends JavaPlugin {

    private static FantasyCore instance;

    @NotNull
    public static FantasyCore get() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new LoadFantasyPlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new UnloadFantasyPlayerListener(), this);
        TaskManager.get().enable();
    }

    @Override
    public void onDisable() {
        instance = null;
        TaskManager.get().disable();
    }

}
