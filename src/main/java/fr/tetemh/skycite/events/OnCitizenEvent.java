package fr.tetemh.skycite.events;

import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.custom.customclass.Shop;
import lombok.Getter;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.CitizensDisableEvent;
import net.citizensnpcs.api.event.CitizensEnableEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;

import java.util.Optional;

@Getter
public class OnCitizenEvent implements Listener {

    private final SkyCite plugin;

    public OnCitizenEvent(SkyCite plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCitizenStart(CitizensEnableEvent event) {




        // Init Shop here For spawn NPC
        this.getPlugin().getShopsManager().init();

        // Spawn Bank
//        this.getPlugin().getBank().setNPC();
//        if(!this.getPlugin().getBank().getNpc().isSpawned()) this.getPlugin().getBank().spawn();
    }

    @EventHandler
    public void onCitizenStop(CitizensDisableEvent event) {
    }
}
