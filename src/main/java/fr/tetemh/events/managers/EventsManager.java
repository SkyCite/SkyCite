package fr.tetemh.events.managers;

import fr.tetemh.events.customclass.Event;
import fr.tetemh.events.events.ChasseAuTresor;
import fr.tetemh.events.runnables.ChasseAuTresorRunnable;
import fr.tetemh.skycite.SkyCite;
import lombok.Data;

import java.util.HashMap;

@Data
public class EventsManager {

    private final SkyCite plugin;

    private HashMap<String, Object> events = new HashMap<>();

    public EventsManager(SkyCite plugin) {
        this.plugin = plugin;

        ChasseAuTresor chasseAuTresor = new ChasseAuTresor(this.getPlugin());
        this.getEvents().put(chasseAuTresor.getConstantName(), chasseAuTresor);
    }
}
