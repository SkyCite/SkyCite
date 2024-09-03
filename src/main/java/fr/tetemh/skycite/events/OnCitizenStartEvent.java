package fr.tetemh.skycite.events;

import fr.tetemh.skycite.SkyCite;
import lombok.Getter;
import net.citizensnpcs.api.event.CitizensEnableEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@Getter
public class OnCitizenStartEvent implements Listener {

    private final SkyCite plugin;

    public OnCitizenStartEvent(SkyCite plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCitizenStart(CitizensEnableEvent event) {

        // Init Shop here For spawn NPC
        this.getPlugin().getShopsManager().init();

        // Spawn Bank
        this.getPlugin().getBank().setNPC();
        this.getPlugin().getBank().spawn();
    }
}
