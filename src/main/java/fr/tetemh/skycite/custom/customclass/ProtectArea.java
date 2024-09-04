package fr.tetemh.skycite.custom.customclass;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import fr.tetemh.skycite.SkyCite;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.World;

@Data
public class ProtectArea {

    private ProtectedCuboidRegion region;
    private String regionName;

    public ProtectArea (String worldName, String regionName, int x1, int y1, int z1, int x2, int y2, int z2) {
        this.setRegionName(regionName);
        World world = Bukkit.getWorld(worldName);
        if(world == null) SkyCite.getInstance().getLogger().warning("La zone " + worldName + " n'a pas pu etre protéger ! (" + worldName + " not found !)");

        BlockVector3 min = BlockVector3.at(Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2));
        BlockVector3 max = BlockVector3.at(Math.max(x1, x2), Math.max(y1, y2), Math.max(z1, z2));

        this.setRegion(new ProtectedCuboidRegion(this.getRegionName(), min, max));

        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regions = container.get(BukkitAdapter.adapt(world));

        regions.addRegion(this.getRegion());
    }
}
