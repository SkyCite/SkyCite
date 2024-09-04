package fr.tetemh.events.events.cat;

import fr.tetemh.skycite.SkyCite;
import lombok.Data;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.checkerframework.checker.units.qual.N;

@Data
public class ChestInteractListener implements Listener {

    private SkyCite plugin;

    public ChestInteractListener(SkyCite plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock() != null &&
                event.getClickedBlock().getType() == Material.CHEST) {

            Block block = event.getClickedBlock();

            if (block.getState() instanceof Chest) {
                Chest chest = (Chest) block.getState();
                PersistentDataContainer data = chest.getPersistentDataContainer();

                if (data.has(new NamespacedKey(this.getPlugin(), "chest_number"), PersistentDataType.INTEGER)) {
                    Player player = event.getPlayer();
                    player.openInventory(chest.getInventory());
                }
            }
        }
    }
}
