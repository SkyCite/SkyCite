package fr.tetemh.events.customclass;

import fr.tetemh.fastinv.ItemBuilder;
import lombok.Data;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Data
public class CChest {
    private final Location location;
    private final String name;
    private List<ItemStack> items = new ArrayList<>();
    private Inventory inv;

    public CChest (Location location, String name) {
        this.location = location;
        this.name = name;

        this.setInv(Bukkit.createInventory(null, 3*9, Component.text(this.getName())));
    }
}
