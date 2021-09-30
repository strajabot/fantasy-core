package me.stradix.fantasycore.task;

import org.apache.commons.lang.Validate;
import org.jetbrains.annotations.NotNull;

public abstract class SyncTimingTask extends Task {

    @NotNull
    private Duration delay;
    @NotNull
    private Duration period;

    public SyncTimingTask(@NotNull Duration delay, @NotNull Duration period) {
        Validate.notNull(delay, "Delay can't be null, use Delay.ZERO instead");
        Validate.notNull(period, "Period can't be null, use SyncDelayTask instead");
        this.delay = delay;
        this.period = period;
    }

    @Override
    public void enable() {
        super.enable();
        internalTask = scheduler().runTaskTimer(
                plugin(),
                this::run,
                delay.getTicks(),
                period.getTicks()
        );
    }

    public void disable() {
        super.disable();
        internalTask.cancel();
        internalTask = null;
    }

    public Duration getDelay() {
        return delay;
    }

    public Duration getPeriod() {
        return period;
    }
}
