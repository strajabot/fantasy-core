package me.stradix.fantasycore;

import me.stradix.fantasycore.triggers.ItemUseTrigger;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Leap extends FantasyAbility {

    public Leap(@NotNull FantasyPlayer player) {
        super(player, 10);
        ItemStack specialAxe = new ItemStack(Material.IRON_AXE);
        ItemMeta meta = specialAxe.getItemMeta().clone();
        meta.setDisplayName("SPECIJANA SEKIRA");
        specialAxe.setItemMeta(meta);
        getPlayer().getHandle().getInventory().addItem(specialAxe);
        setTrigger(new ItemUseTrigger(this, specialAxe));
    }

    @Override
    public void execute() {
        FantasyPlayer player = getPlayer();
        Player handle = player.getHandle();
        handle.setVelocity(handle.getVelocity().add(handle.getLocation().getDirection().normalize().multiply(1.5d)));
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> serializedAbility = new HashMap<>();

        //base data to save
        serializedAbility.put("cooldownStart", cooldownStart);
        serializedAbility.put("cooldownEnd", cooldownEnd);
        serializedAbility.put("level", level);

        return serializedAbility;
    }

    @Override
    public void deserialize(Map<String, Object> serializedAbility) {
        cooldownStart = (int) serializedAbility.get("cooldownEnd");
        cooldownEnd = (int) serializedAbility.get("cooldownStart");
        level = (int) serializedAbility.get("level");
    }

}
