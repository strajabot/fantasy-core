package me.stradix.fantasycore;

import com.google.common.base.Preconditions;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FantasyPlayer {

    private static Map<Player, FantasyPlayer> playerMap = new HashMap<>();

    @Nullable
    public static FantasyPlayer get(Player player) {
        Preconditions.checkNotNull(player, "Argument \"player\" can't be null");

        return playerMap.get(player);
    }

    @NotNull
    public static List<FantasyPlayer> getOnline() {
        return new ArrayList<>(playerMap.values());
    }

    public static void load(@NotNull Player player) {
        //todo: implement loading saved player state.
        playerMap.put(player, new FantasyPlayer(player));
    }

    private FantasyPlayer(@NotNull Player player) {
        Preconditions.checkNotNull(player, "Argument \"player\" can't be null");
        this.handle = player;
        this.abilities = new ArrayList<>();
        //todo: load abilities from config
        abilities.add(new Leap(this));
        load();
    }

    //cuva se u configu
    private int energy = 10;

    //cuva se u configu
    private int level = 1;

    //cuva se u configu
    private List<FantasyAbility> abilities;

    @NotNull
    private Player handle;

    public void unload() {
        save();
        //todo: implement saving player state.
        for(FantasyAbility ability: abilities) {
            //unload ability and trigger listeners
            ability.unload();
        }
        playerMap.remove(handle);
    }

    public Player getHandle() {
        return  handle;
    }

    public Map<String, Object> serialize() {
        Map<String, Object> serializedPlayer = new HashMap<>();

        serializedPlayer.put("energy", energy);
        serializedPlayer.put("level", level);

        Map<String,Object> serializedAbilities = new HashMap<>();
        for(FantasyAbility ability: abilities) {
            serializedAbilities.put(FantasyAbility.ALL_ABILITIES.inverse().get(ability.getClass()), ability.serialize());
        }
        serializedPlayer.put("abilities", serializedAbilities);

        return serializedPlayer;
    }

    public void deserialize(Map<String, Object> serializedData) {
        energy = (int) serializedData.get("energy");
        level = (int) serializedData.get("level");

        Map<String, Object> serializedAbilities = (Map<String, Object>) serializedData.get("abilities");

        for(String key :serializedAbilities.keySet()) {
            Class<? extends FantasyAbility> abilityClass =  FantasyAbility.ALL_ABILITIES.get(key);
            if(abilityClass == null) continue;
            for(FantasyAbility ability : abilities) {
                if(!ability.getClass().equals(abilityClass)) continue;
                ability.deserialize((Map<String, Object>) serializedAbilities.get(key));
            }
        }


    }

    public void save() {
        File datafile = getDataFile();
        try {
            Yaml yaml = new Yaml();
            yaml.dump(serialize(), new FileWriter(datafile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        File dataFile = getDataFile();
        try {
            Yaml yaml = new Yaml();
            Map<String, Object> config = yaml.load(new FileInputStream(dataFile));
            deserialize(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File getDataFile() {
        return new File(FantasyCore.get().getDataFolder()+"/player_data/"+ getHandle().getName()+".yml");
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<FantasyAbility> getAbilities() {
        return abilities;
    }
}
