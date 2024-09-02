package fr.tetemh.skycite.events;

import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.custom.customclass.CPlayer;
import lombok.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

@Data
public class OnJoin implements Listener {

    private SkyCite plugin;

    public OnJoin (SkyCite plugin) {
        this.setPlugin(plugin);
    }

    @EventHandler
    void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(this.getPlugin().getPlayersManager().getPlayers().containsKey(player.getUniqueId())) {
            CPlayer cPlayer = this.getPlugin().getPlayersManager().getPlayers().get(player.getUniqueId());
            cPlayer.setOnline(true);
        } else {
            this.getPlugin().getPlayersManager().getPlayers().put(player.getUniqueId(), new CPlayer(player));
        }

        // Spawn Bank
//        this.getPlugin().getBank().spawn(player);
//        this.getPlugin().getShopsManager().getShops().values().forEach(shop -> {
//            shop.spawn(player);
//        });
    }

}
