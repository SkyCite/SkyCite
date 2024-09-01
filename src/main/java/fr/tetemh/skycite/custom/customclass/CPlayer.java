package fr.tetemh.skycite.custom.customclass;

import lombok.Data;
import org.bukkit.entity.Player;

@Data
public class CPlayer {
    private Player player;
    private boolean online;
    private long money;

    public CPlayer (Player player) {
        this.setPlayer(player);
        this.setOnline(true);
        this.setMoney(0);
    }
}
