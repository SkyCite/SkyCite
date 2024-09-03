package fr.tetemh.events;

import fr.tetemh.events.managers.EventsManager;
import fr.tetemh.skycite.SkyCite;
import lombok.Getter;
import lombok.Setter;

public class Events {

    @Getter
    private final SkyCite plugin;

    @Getter @Setter
    private EventsManager eventsManager;

    public Events(SkyCite plugin) {
        this.plugin = plugin;
    }

    public void onEnable() {
    this.setEventsManager(new EventsManager(this.getPlugin()));
    }

    public void onDisable() {

    }
}
