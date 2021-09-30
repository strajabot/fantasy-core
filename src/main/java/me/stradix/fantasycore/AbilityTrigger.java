package me.stradix.fantasycore;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public abstract class AbilityTrigger implements Listener {

    @NotNull
    private FantasyAbility ability;

    public AbilityTrigger(@NotNull FantasyAbility ability) {
        this.ability = ability;

    }

    public void trigger() {
        ability.use();
    }


    public void stop() {
        HandlerList.unregisterAll(this);
    }

    public void start() {
        Bukkit.getPluginManager().registerEvents(this, FantasyCore.get());
    }

    public FantasyAbility getAbility() {
        return ability;
    }
}
