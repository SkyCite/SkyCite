package fr.tetemh.skycite.custom.customclass;

import fr.mrmicky.fastboard.adventure.FastBoard;
import lombok.Data;
import org.bukkit.entity.Player;

@Data
public class CPlayer {
    private Player player;
    private boolean online;
    private long money;
    private boolean inEvent;
    private FastBoard fastBoard;

    public CPlayer (Player player) {
        this.setPlayer(player);
        this.setOnline(true);
        this.setMoney(0);

        this.initBoard();
    }

    private void initBoard() {
        this.setFastBoard(new FastBoard(this.getPlayer()));
    }
}
