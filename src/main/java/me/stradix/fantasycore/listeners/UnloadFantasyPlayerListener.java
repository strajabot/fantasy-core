package me.stradix.fantasycore.listeners;

import me.stradix.fantasycore.FantasyPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;

public class UnloadFantasyPlayerListener implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {

        Player handle = event.getPlayer();
        FantasyPlayer player = FantasyPlayer.get(handle);
        if(player == null) {
            System.out.println(handle.getName() + " wasn't loaded in the first place");
            return;
        }
        player.unload();
        System.out.println(handle.getName() + " has been unloaded");
    }

    public void onPluginUnload(PluginDisableEvent event) {

    }


}
