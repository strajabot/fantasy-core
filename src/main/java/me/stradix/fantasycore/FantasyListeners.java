package me.stradix.fantasycore;

import me.stradix.fantasycore.listeners.LoadFantasyPlayerListener;
import me.stradix.fantasycore.listeners.UnloadFantasyPlayerListener;
import org.bukkit.event.Listener;

public class FantasyListeners {

    public static void register() {
        FantasyCore plugin = FantasyCore.get();
        registerHandler(plugin, new LoadFantasyPlayerListener());
        registerHandler(plugin, new UnloadFantasyPlayerListener());
    }

    private static void registerHandler(FantasyCore plugin, Listener listener) {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }



}
