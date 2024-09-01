package fr.tetemh.skycite.custom.customclass;

import fr.tetemh.fastinv.FastInv;
import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.guis.bank.BankGui;
import lombok.Data;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.Inventory;
import org.bukkit.persistence.PersistentDataType;

@Data
public class Bank {

    private final SkyCite plugin;
    private final String name;
    private final Location location;
    private Villager bankEntity;
    private FastInv inventory;


    public Bank (SkyCite plugin) {
        this.plugin = plugin;
        this.name = "Banquier";
        this.location = new Location(Bukkit.getWorld("world"), 0, 100, 5);

        this.genInventory();
    }

    public void spawn () {
        this.setBankEntity((Villager) this.getLocation().getWorld().spawnEntity(this.getLocation(), EntityType.VILLAGER));
        this.getBankEntity().customName(Component.text(this.getName()));
        this.getBankEntity().setCustomNameVisible(true);
        this.getBankEntity().setAI(false);
        this.getBankEntity().setInvulnerable(true);
        this.getBankEntity().setGravity(true);

        // Data
        NamespacedKey key_type = new NamespacedKey(SkyCite.getInstance(), "npc_type");
        this.getBankEntity().getPersistentDataContainer().set(key_type, PersistentDataType.STRING, "bank");
    }

    public void kill() {
        this.getBankEntity().remove();
    }

    private void genInventory() {
        this.setInventory(new BankGui(this.getPlugin(), this));
    }
}
