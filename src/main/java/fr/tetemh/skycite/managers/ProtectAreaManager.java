package fr.tetemh.skycite.managers;

import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.custom.customclass.ProtectArea;
import lombok.Data;

import java.util.HashMap;

@Data
public class ProtectAreaManager {

    private SkyCite plugin;

    private HashMap<String, ProtectArea> areas = new HashMap<>();

    public ProtectAreaManager(SkyCite plugin) {
        this.plugin = plugin;

        ProtectArea spawnArea = new ProtectArea("world", "spawn_area", 100, 100, 100, -100, 0, -100);

        spawnArea.getRegion().setFlag(Flags.CHEST_ACCESS, StateFlag.State.DENY);
        spawnArea.getRegion().setFlag(Flags.BLOCK_BREAK, StateFlag.State.DENY);
        spawnArea.getRegion().setFlag(Flags.BLOCK_PLACE, StateFlag.State.DENY);
        spawnArea.getRegion().setFlag(Flags.DAMAGE_ANIMALS, StateFlag.State.DENY);
        spawnArea.getRegion().setFlag(Flags.FALL_DAMAGE, StateFlag.State.DENY);
        spawnArea.getRegion().setFlag(Flags.PVP, StateFlag.State.DENY);
        spawnArea.getRegion().setFlag(Flags.RIDE, StateFlag.State.DENY);
        spawnArea.getRegion().setFlag(Flags.INTERACT, StateFlag.State.DENY);

        this.getAreas().put(spawnArea.getRegionName(), spawnArea);
    }
}
