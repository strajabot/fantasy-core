package me.stradix.fantasycore.task;

public class Duration {

    public static final Duration ZERO = new Duration(0);

    public enum Unit {
        MILLISECOND(1),
        TICK(50),
        SECOND(1000),
        MINUTE(60000),
        HOUR(3600000),
        DAY(86400000);

        public final long milliseconds;

        Unit(long milliseconds) {
            this.milliseconds = milliseconds;
        }

    }

    public static Duration milliseconds(long duration) {
        return new Duration(duration, Unit.MILLISECOND);
    }

    public static Duration ms(long duration) {
        return new Duration(duration, Unit.MILLISECOND);
    }

    public static Duration ticks(long duration) {
        return new Duration(duration, Unit.TICK);
    }

    public static Duration seconds(long duration) {
        return new Duration(duration, Unit.SECOND);
    }

    public static Duration minutes(long duration) {
        return new Duration(duration, Unit.MINUTE);
    }

    public static Duration min(long duration) {
        return new Duration(duration, Unit.MINUTE);
    }

    public static Duration hours(long duration) {
        return new Duration(duration, Unit.HOUR);
    }

    public static Duration day(long duration) {
        return new Duration(duration, Unit.DAY);
    }

    private long milliseconds;

    public Duration(long duration, Unit unit) {
        new Duration(duration*unit.milliseconds);
    }

    public Duration(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public long getTicks() {
        return milliseconds/Unit.TICK.milliseconds;
    }

}
