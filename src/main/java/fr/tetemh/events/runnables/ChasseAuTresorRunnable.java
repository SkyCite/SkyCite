package fr.tetemh.events.runnables;

import fr.tetemh.events.customclass.CChest;
import fr.tetemh.events.events.ChasseAuTresor;
import fr.tetemh.fastinv.ItemBuilder;
import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.utils.Utils;
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
    private CChest cChest;

    private boolean start;

    public ChasseAuTresorRunnable(SkyCite plugin, ChasseAuTresor cat) {
        this.plugin = plugin;
        this.cat = cat;
    }

    @Override
    public void run() {
        if(!this.isStart()) this.setStart(true);

        if(this.getTimeCounter() == 0 || (this.getCChest() != null && this.getCChest().getLocation().getBlock().getState() instanceof Chest && ((Chest) this.getCChest().getLocation().getBlock().getState()).getInventory().isEmpty())) {

            if(this.getCChest() != null) this.getCChest().getLocation().getBlock().setType(Material.AIR);

            if(this.getCat().getChests().size() == this.getChestNumber()) {
                this.setStart(false);
                this.cancel();
            }

            this.setCChest(this.getCat().getChests().get(this.getChestNumber()));
            this.setChestNumber(this.getChestNumber() + 1);

            this.spawnChest(this.getCChest());
            this.getPlugin().getServer().broadcast(Component.text("Le prochain coffre viens d'apparaitre au cordonné x: " + this.getCChest().getLocation().getBlockX() + " y: " + this.getCChest().getLocation().getBlockY() + " z: " + this.getCChest().getLocation().getBlockZ()));
        }

        this.setTimeCounter(this.getTimeCounter() + 1);
        if(this.getTimeCounter() == (60*5)) {
            this.setTimeCounter(0);
        }
    }

    private void spawnChest(CChest cchest) {
        if(cchest.getLocation().getBlock().getType() != Material.AIR) {
            this.getPlugin().getServer().getOperators().forEach(op -> {
                op.getPlayer().sendMessage(Component.text("§cC'est la D Stop l'évent (Seulement Téo qui gère)"));
            });
            return;
        }

        cchest.getLocation().getBlock().setType(Material.CHEST);

        Chest chest = (Chest) cchest.getLocation().getBlock().getState();
        Utils.fillInventoryWithRandomItems(chest.getInventory(), cchest.getItems());

        chest.getPersistentDataContainer().set(new NamespacedKey(this.getPlugin(), "chest_number"), PersistentDataType.INTEGER, this.getChestNumber());
    }
}
