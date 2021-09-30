package me.stradix.fantasycore.task;

import org.apache.commons.lang.Validate;
import org.jetbrains.annotations.NotNull;

public abstract class SyncDelayTask extends Task {

    @NotNull
    protected Duration delay;

    public SyncDelayTask(@NotNull Duration delay) {
        Validate.notNull(delay, "Delay can't be null, use Delay.ZERO instead");
        this.delay = delay;
    }

    @Override
    public void enable() {
        super.enable();
        internalTask = scheduler().runTaskLater(
                plugin(),
                this::run,
                delay.getTicks()
        );
    }

    public void disable() {
        super.disable();
        internalTask = null;
    }

    public Duration getDelay() {
        return delay;
    }

}
