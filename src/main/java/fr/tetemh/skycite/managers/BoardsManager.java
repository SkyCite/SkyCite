package fr.tetemh.skycite.managers;

import fr.mrmicky.fastboard.adventure.FastBoard;
import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.runnable.BoardsRunnable;
import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class BoardsManager {

    @Getter
    private final Map<UUID, FastBoard> boards = new HashMap<>();
    @Getter
    private final SkyCite plugin;

    private BoardsRunnable boardsRunnable;

    public BoardsManager(SkyCite plugin) {
        this.plugin = plugin;

        this.setBoardsRunnable(new BoardsRunnable(this.getPlugin()));
    }
}
