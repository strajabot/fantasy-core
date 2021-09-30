package me.stradix.fantasycore.listeners;

import me.stradix.fantasycore.FantasyCore;
import me.stradix.fantasycore.FantasyPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.jetbrains.annotations.NotNull;

public class LoadFantasyPlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        Player handle = event.getPlayer();
        FantasyPlayer.load(handle);
        System.out.println(handle.getName() + " has been loaded");
    }

    @EventHandler
    public void serverStart(PluginEnableEvent event) {
        //reload command fix
        if(event.getPlugin() instanceof FantasyCore) {
            FantasyCore.get().getServer().getOnlinePlayers().forEach(FantasyPlayer::load);
        }
    }

}
