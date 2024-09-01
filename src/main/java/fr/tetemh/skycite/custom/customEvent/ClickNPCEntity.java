package fr.tetemh.skycite.custom.customEvent;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
public class ClickNPCEntity extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final Villager villager;
    private final String npcName;

    public ClickNPCEntity(Player player, Villager villager, String npcName) {
        super();
        this.player = player;
        this.villager = villager;
        this.npcName = npcName;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}