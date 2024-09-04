package fr.tetemh.skycite.runnable;

import fr.mrmicky.fastboard.adventure.FastBoard;
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
        this.getPlugin().getPlayersManager().getPlayers().values().stream().filter(cPlayer -> cPlayer.isOnline() && !cPlayer.isInEvent()).forEach(this::updateLine);
    }

    private void updateLine(CPlayer cPlayer) {
        cPlayer.getFastBoard().updateTitle(Component.text("SkyCite"));
        cPlayer.getFastBoard().updateLines(
                Component.text(""),
                Component.text("Money : " + cPlayer.getMoney()),
                Component.text("")
        );
    }
}
