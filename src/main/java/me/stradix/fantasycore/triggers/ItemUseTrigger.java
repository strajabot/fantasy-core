package me.stradix.fantasycore.triggers;

import me.stradix.fantasycore.AbilityTrigger;
import me.stradix.fantasycore.FantasyAbility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ItemUseTrigger extends AbilityTrigger {

    private ItemStack itemStack;

    public ItemUseTrigger(@NotNull FantasyAbility ability, ItemStack itemStack) {
        super(ability);
        this.itemStack = itemStack.clone();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player handle = getAbility().getPlayer().getHandle();
        ItemStack itemStack = event.getItem();
        if(!event.getPlayer().equals(handle)) return;
        if(itemStack == null) return;
        if(itemStack.isSimilar(this.itemStack)) trigger();
    }

}
