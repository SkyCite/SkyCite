package fr.tetemh.events.customclass;

import fr.tetemh.events.EventStatus;
import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.utils.Utils;
import lombok.Data;
import org.bukkit.scheduler.BukkitRunnable;

@Data
public class Event {

    private final SkyCite plugin;
    private final String name;
    private final String constantName;
    private EventStatus status;
    private BukkitRunnable boardRunnable;

    public Event(SkyCite plugin, String name) {
        this.plugin = plugin;
        this.name = name;
        
        this.constantName = Utils.normalizeString(name);
        this.setStatus(EventStatus.STOP);
    }

    public void disable() {
    }
}
