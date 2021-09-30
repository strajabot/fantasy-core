package me.stradix.fantasycore.task.global;

import me.stradix.fantasycore.FantasyPlayer;
import me.stradix.fantasycore.task.Duration;
import me.stradix.fantasycore.task.SyncTimingTask;
import org.bukkit.entity.Player;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UpdatePlayerEnergyTask extends SyncTimingTask {
    public UpdatePlayerEnergyTask() {
        super(Duration.ZERO, Duration.min(5));
    }

    @Override
    public void run() {
        List<FantasyPlayer> online = FantasyPlayer.getOnline();
        for(FantasyPlayer player: online) {
            Player handle = player.getHandle();
            long loggedIn = handle.getLastLogin();
            if(loggedIn + TimeUnit.MINUTES.toMillis(5) > System.currentTimeMillis()) continue;
            player.setEnergy(player.getEnergy() + 2 - (1/player.getLevel()));
        }
    }
}
