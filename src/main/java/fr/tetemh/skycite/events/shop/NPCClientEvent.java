package fr.tetemh.skycite.events.shop;

import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.custom.customclass.Shop;
import lombok.Getter;
import net.citizensnpcs.api.event.NPCClickEvent;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;

@Getter
public class NPCClientEvent implements Listener {

    private final SkyCite plugin;

    public NPCClientEvent(SkyCite plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onVillagerShopOpen(NPCClickEvent event) {
        Player player = event.getClicker();

        if (event.getNPC().getEntity().getPersistentDataContainer().has(new NamespacedKey(this.plugin, "npc_type"), PersistentDataType.STRING)) {
            String npcType = event.getNPC().getEntity().getPersistentDataContainer().get(new NamespacedKey(this.plugin, "npc_type"), PersistentDataType.STRING);

            switch (npcType) {
                case "shop" -> {
                    if (event.getNPC().getEntity().getPersistentDataContainer().has(new NamespacedKey(this.plugin, "npc_name"), PersistentDataType.STRING)) {
                        String name = event.getNPC().getEntity().getPersistentDataContainer().get(new NamespacedKey(this.plugin, "npc_name"), PersistentDataType.STRING);
                        Shop shop = this.plugin.getShopsManager().getShop(name).get();
                        shop.getInventoryManager().first().open(player);
                    }
                }

                case "bank" -> {
                    this.getPlugin().getBank().getInventory().open(player);
                }
            }
        }
    }
}
