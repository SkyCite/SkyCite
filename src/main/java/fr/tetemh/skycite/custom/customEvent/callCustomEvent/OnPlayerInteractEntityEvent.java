package fr.tetemh.skycite.custom.customEvent.callCustomEvent;

import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.custom.customEvent.ClickNPCEntity;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.persistence.PersistentDataType;

public class OnPlayerInteractEntityEvent implements Listener {

    private final SkyCite plugin;

    public OnPlayerInteractEntityEvent(SkyCite plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Villager) {
            Villager villager = (Villager) event.getRightClicked();

            if (villager.getPersistentDataContainer().has(new NamespacedKey(this.plugin, "npc_type"), PersistentDataType.STRING)) {
                String npmName = villager.getPersistentDataContainer().get(new NamespacedKey(this.plugin, "npc_name"), PersistentDataType.STRING);
                ClickNPCEntity customEvent = new ClickNPCEntity(event.getPlayer(), villager, npmName);
                Bukkit.getServer().getPluginManager().callEvent(customEvent);
            }
        }
    }
}
