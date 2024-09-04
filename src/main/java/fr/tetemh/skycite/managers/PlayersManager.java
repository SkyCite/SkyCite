package fr.tetemh.skycite.managers;

import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.custom.customclass.CPlayer;
import fr.tetemh.skycite.runnable.BoardsRunnable;
import lombok.Getter;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Getter
public class PlayersManager {

    private final SkyCite plugin;
    private HashMap<UUID, CPlayer> players = new HashMap<>();
    private BoardsRunnable boardsRunnable;

    public PlayersManager(SkyCite plugin) {
        this.plugin = plugin;

        this.getPlugin().getServer().getOnlinePlayers().forEach(player -> {
            this.getPlayers().put(player.getUniqueId(), new CPlayer(player));
        });

        this.boardsRunnable = new BoardsRunnable(this.getPlugin());
        this.getBoardsRunnable().runTaskTimer(this.getPlugin(), 0, 20);
    }

    public Optional<CPlayer> getPlayerByName(String name) {
        return this.getPlayers().values().stream().filter(cPlayer -> cPlayer.getPlayer().name().toString() == name).findFirst();
    }

}
