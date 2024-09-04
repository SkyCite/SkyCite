package fr.tetemh.skycite.events;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class NotAuthActionEvent  implements Listener {

    private final WorldGuardPlugin worldGuard;

    public NotAuthActionEvent(WorldGuardPlugin worldGuard) {
        this.worldGuard = worldGuard;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        if (!canBuildHere(player, block)) {
            event.setCancelled(true);
            player.sendActionBar(Component.text("Vous ne pouvez pas casser des blocs dans cette zone protégée !"));
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.CHEST) {
            Player player = event.getPlayer();
            Block block = event.getClickedBlock();

            if (!canOpenChestHere(player, block)) {
                event.setCancelled(true);
                player.sendActionBar(Component.text("Vous ne pouvez pas ouvrir ce coffre dans cette zone protégée !"));
            }
        }
    }

    private boolean canBuildHere(Player player, Block block) {
        LocalPlayer localPlayer = worldGuard.wrapPlayer(player);
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regions = container.get(BukkitAdapter.adapt(block.getWorld()));
        if (regions == null) return false;

        ApplicableRegionSet set = regions.getApplicableRegions(BukkitAdapter.asBlockVector(block.getLocation()));
        return set.testState(localPlayer, Flags.BLOCK_BREAK);
    }

    private boolean canOpenChestHere(Player player, Block block) {
        LocalPlayer localPlayer = worldGuard.wrapPlayer(player);
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        return query.testState(BukkitAdapter.adapt(player.getLocation()), localPlayer, Flags.CHEST_ACCESS);
    }
}
