package fr.tetemh.skycite.events;

import fr.tetemh.skycite.SkyCite;
import lombok.Data;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@Data
public class OnQuit implements Listener {

    private SkyCite plugin;

    public OnQuit (SkyCite plugin) {
        this.setPlugin(plugin);
    }

    @EventHandler
    void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        this.getPlugin().getPlayersManager().getPlayers().get(player.getUniqueId()).setOnline(false);
    }

}
