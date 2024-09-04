package fr.tetemh.events.runnables;

import fr.tetemh.events.events.ChasseAuTresor;
import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.custom.customclass.CPlayer;
import lombok.Data;
import net.kyori.adventure.text.Component;
import org.bukkit.scheduler.BukkitRunnable;

@Data
public class ChasseAuTresorBoardRunnable extends BukkitRunnable {

    private final SkyCite plugin;
    private final ChasseAuTresor cat;

    public ChasseAuTresorBoardRunnable(SkyCite plugin, ChasseAuTresor cat) {
        this.plugin = plugin;
        this.cat = cat;
    }

    @Override
    public void run() {
        this.getPlugin().getPlayersManager().getPlayers().values().stream().filter(cPlayer -> cPlayer.isOnline() && cPlayer.isInEvent()).forEach(this::updateLine);
    }

    private void updateLine(CPlayer cPlayer) {
        cPlayer.getFastBoard().updateTitle(Component.text("SkyCite"));
        cPlayer.getFastBoard().updateLines(
                Component.text(""),
                Component.text("Trésor : " + this.getCat().getChasseAuTresorRunnable().getChestNumber() + "/" + this.getCat().getChests().size()),
                Component.text("Chrono : " + this.getCat().getChasseAuTresorRunnable().getChestNumber()),
                Component.text("")
        );
    }
}
