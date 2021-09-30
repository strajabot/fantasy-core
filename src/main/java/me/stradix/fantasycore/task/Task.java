package me.stradix.fantasycore.task;

import me.stradix.fantasycore.FantasyCore;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public abstract class Task {
    protected BukkitTask internalTask;
    private boolean isEnabled;

    public abstract void run();

    public void enable() {
        if(isEnabled) throw new RuntimeException("Task is already enabled");
        else isEnabled = true;
    }

    public void disable() {
        if(!isEnabled) throw new RuntimeException("Task is already disabled");
        else isEnabled = false;
    }

    public boolean isEnabled() {
        return  isEnabled;
    }

    protected FantasyCore plugin() {
        return FantasyCore.get();
    }

    protected BukkitScheduler scheduler() {
        FantasyCore plugin = FantasyCore.get();
        return plugin.getServer().getScheduler();
    }

    protected <T> Future<T> sync(Callable<T> sync) {
        return scheduler().callSyncMethod(plugin(), sync);
    }

    protected BukkitTask sync(Runnable sync) {
        return scheduler().runTask(plugin(), sync);
    }

    protected BukkitTask async(Runnable async) {
        return scheduler().runTaskAsynchronously(plugin(), async);
    }

}
