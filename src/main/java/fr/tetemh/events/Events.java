package fr.tetemh.events;

import fr.tetemh.events.commands.EventCommand;
import fr.tetemh.events.managers.EventsManager;
import fr.tetemh.skycite.SkyCite;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@Data
public class Events {

    private final SkyCite plugin;
    private EventsManager eventsManager;
    private YamlConfiguration eventsConfig;

    public Events(SkyCite plugin) {
        this.plugin = plugin;
    }

    public void onEnable() {

        // Config Files
        try {
            File eventsFile = new File(this.getPlugin().getDataFolder(), "events.yml");
            this.setEventsConfig(YamlConfiguration.loadConfiguration(eventsFile));
            this.getEventsConfig().save(eventsFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.setEventsManager(new EventsManager(this.getPlugin()));

        this.getPlugin().getCommand("event").setExecutor(new EventCommand(this.getPlugin()));
    }

    public void onDisable() {
        this.getEventsManager().getEvents().values().forEach(event -> {
            event.disable();
        });
    }
}
