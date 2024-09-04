package fr.tetemh.events.runnables;

import fr.tetemh.events.customclass.CChest;
import fr.tetemh.events.events.ChasseAuTresor;
import fr.tetemh.fastinv.ItemBuilder;
import fr.tetemh.skycite.SkyCite;
import lombok.Data;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

@Data
public class ChasseAuTresorRunnable extends BukkitRunnable {

    private final SkyCite plugin;
    private final ChasseAuTresor cat;
    private int timeCounter = 0;
    private int chestNumber = 0;

    public ChasseAuTresorRunnable(SkyCite plugin, ChasseAuTresor cat) {
        this.plugin = plugin;
        this.cat = cat;
    }

    @Override
    public void run() {
        if(this.getTimeCounter() == 0) {
            CChest chest = this.getCat().getChests().get(this.getChestNumber());
            this.setChestNumber(this.getChestNumber() + 1);

            this.spawnChest(chest);
        }

        this.setTimeCounter(this.getTimeCounter() + 1);
        if(this.getTimeCounter() == (60*5)) {
            this.setTimeCounter(0);
        }
    }

    private void spawnChest(CChest cchest) {
        if(cchest.getLocation().getBlock().getType() != Material.AIR) {
            this.getPlugin().getServer().getOperators().forEach(op -> {
                op.getPlayer().sendMessage(Component.text("C'est la D Stop l'évent (Seulement Téo qui gère)"));
            });
            return;
        }

        cchest.getLocation().getBlock().setType(Material.CHEST);

        Chest chest = (Chest) cchest.getLocation().getBlock().getState();
        chest.getPersistentDataContainer().set(new NamespacedKey(this.getPlugin(), "chest_number"), PersistentDataType.INTEGER, this.getChestNumber());
    }
}
