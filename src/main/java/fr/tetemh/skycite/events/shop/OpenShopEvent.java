package fr.tetemh.skycite.events.shop;

import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.custom.customEvent.ClickNPCEntity;
import fr.tetemh.skycite.custom.customclass.Shop;
import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;

@Getter
public class OpenShopEvent implements Listener {

    private final SkyCite plugin;

    public OpenShopEvent(SkyCite plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onVillagerShopOpen(ClickNPCEntity event) {
        Player player = event.getPlayer();
        String name = event.getNpcName();

        if (event.getVillager().getPersistentDataContainer().has(new NamespacedKey(this.plugin, "npc_type"), PersistentDataType.STRING)) {
            String npcType = event.getVillager().getPersistentDataContainer().get(new NamespacedKey(this.plugin, "npc_type"), PersistentDataType.STRING);

            switch (npcType) {
                case "shop" -> {
                    Shop shop = this.plugin.getShopsManager().getShop(name).get();
                    shop.getInventoryManager().first().open(player);
                }

                case "bank" -> {
                    this.getPlugin().getBank().getInventory().open(player);
                }
            }
        }
    }
}
