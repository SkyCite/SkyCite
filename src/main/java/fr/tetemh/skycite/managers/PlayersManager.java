package fr.tetemh.skycite.managers;

import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.custom.customclass.CPlayer;
import lombok.Getter;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Getter
public class PlayersManager {

    private final SkyCite plugin;
    private HashMap<UUID, CPlayer> players = new HashMap<>();

    public PlayersManager(SkyCite plugin) {
        this.plugin = plugin;

        this.getPlugin().getServer().getOnlinePlayers().forEach(player -> {
            if(this.getPlayers().containsKey(player.getUniqueId())) {
                CPlayer cPlayer = this.getPlayers().get(player.getUniqueId());
                cPlayer.setOnline(true);
            } else {
                this.getPlayers().put(player.getUniqueId(), new CPlayer(player));
            }
        });
    }

    public Optional<CPlayer> getPlayerByName(String name) {
        return this.getPlayers().values().stream().filter(cPlayer -> cPlayer.getPlayer().name().toString() == name).findFirst();
    }

}
