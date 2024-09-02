package fr.tetemh.skycite.runnable;

import fr.tetemh.fastboard.FastBoard;
import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.custom.customclass.CPlayer;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.scheduler.BukkitRunnable;

public class BoardsRunnable extends BukkitRunnable {

    @Getter
    private final SkyCite plugin;

    public BoardsRunnable(SkyCite plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        this.getPlugin().getBoardsManager().getBoards().forEach((uuid, board) -> {
            CPlayer cPlayer = this.getPlugin().getPlayersManager().getPlayers().get(uuid);
            this.updateLine(board, cPlayer);
        });
    }

    private void updateLine(FastBoard board, CPlayer cPlayer) {
        board.updateLines(
                Component.text(""),
                Component.text("Money : " + cPlayer.getMoney()),
                Component.text("")
        );
    }
}
