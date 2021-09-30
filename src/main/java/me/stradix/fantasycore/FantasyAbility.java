package me.stradix.fantasycore;

import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public abstract class FantasyAbility {

    public static BiMap<String, Class<? extends FantasyAbility>> ALL_ABILITIES =
            new ImmutableBiMap.Builder<String, Class<? extends FantasyAbility>>()
                    .put("leap", Leap.class )
                    .build();

    @NotNull
    private FantasyPlayer player;

    @Nullable
    private AbilityTrigger trigger;

    private String abilityID;

    private int cooldown;

    protected long cooldownStart;

    protected long cooldownEnd;

    protected int level;

    public FantasyAbility(@NotNull FantasyPlayer player, int cooldown) {
        Preconditions.checkNotNull(player, "Argument \"player\" can't be null");
        this.player = player;
        this.cooldown = cooldown;
    }

    public abstract void execute();

    public abstract Map<String, Object> serialize();

    public abstract void deserialize(Map<String, Object> serializedMap);

    public void use() {
        long currTime =  System.currentTimeMillis();
        if(currTime < cooldownEnd) {
            //todo: implement ability cooldown
            return;
        }
        cooldownStart = System.currentTimeMillis();
        cooldownEnd = System.currentTimeMillis() + cooldown*1000;
        execute();
    }

    public int getCooldownLength() {
        return cooldown;
    }

    public long getCooldownStart() {
        return cooldownStart;
    }

    public long getCooldownEnd() {
        return cooldownEnd;
    }

    @NotNull
    public FantasyPlayer getPlayer() {
        return player;
    }

    protected void setTrigger(@NotNull AbilityTrigger trigger) {
        if(this.trigger != null) this.trigger.stop();
        this.trigger = trigger;
        this.trigger.start();
    }

    void unload() {
        if(trigger != null) trigger.stop();
    }



}
