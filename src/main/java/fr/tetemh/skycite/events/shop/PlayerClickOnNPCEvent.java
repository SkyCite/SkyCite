package fr.tetemh.skycite.events.shop;

import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.custom.customclass.Shop;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@Getter
public class PlayerClickOnNPCEvent implements Listener {

    private final SkyCite plugin;

    public PlayerClickOnNPCEvent(SkyCite plugin) {
        this.plugin = plugin;
    }

//    @EventHandler
//    public void click(NPCRightClickEvent event) {
//        this.callEvent(event.getNPC(), event.getClicker());
//    }
//    @EventHandler
//    public void click(NPCLeftClickEvent event) {
//        this.callEvent(event.getNPC(), event.getClicker());
//    }
//
//    private void callEvent(NPC npc, Player player) {
//        if(npc.data().has("npc_type")) {
//            switch ((String) npc.data().get("npc_type")) {
//                case "shop" -> {
//                    if (npc.data().has("shop_name")) {
//                        String name = npc.data().get("shop_name");
//                        Shop shop = this.plugin.getShopsManager().getShop(name).get();
//                        if(shop == null) {
//                            player.sendMessage("§cErreur avec ce NPC veuillez contacter un Administrateur");
//                            return;
//                        }
//                        shop.getInventoryManager().first().open(player);
//                    } else {
//                        player.sendMessage("§cErreur avec ce NPC veuillez contacter un Administrateur");
//                        return;
//                    }
//                }
//
//                case "bank" -> {
//                    this.getPlugin().getBank().getInventory().open(player);
//                }
//            }
//        }
//    }


}
