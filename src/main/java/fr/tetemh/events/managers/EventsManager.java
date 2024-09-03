package fr.tetemh.events.managers;

import fr.tetemh.events.customclass.Event;
import fr.tetemh.skycite.SkyCite;
import lombok.Getter;

import java.util.HashMap;

public class EventsManager {

    private final SkyCite plugin;

    @Getter
    private HashMap<String, Event> events = new HashMap<>();

    public EventsManager(SkyCite plugin) {
        this.plugin = plugin;

        Event chasseAuTresor = new Event("Chasse Au Trésore");
        this.getEvents().put(chasseAuTresor.getConstantName(), chasseAuTresor);
    }
}
