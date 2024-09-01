package fr.tetemh.skycite.managers;

import fr.tetemh.fastinv.FastInv;
import fr.tetemh.skycite.utils.Utils;
import lombok.Data;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Data
public class InventoryManager {

    private List<FastInv> inventorys = new ArrayList<>();

    private int page;

    public FastInv first () {
        this.setPage(0);
        return this.inventorys.get(0);
    }

    public FastInv nextView() {
        this.setPage(this.getPage() + 1);
        return this.inventorys.get(this.getPage());
    }
    public FastInv previousView() {
        this.setPage(this.getPage() - 1);
        return this.inventorys.get(this.getPage());
    }

    public void addInventory(FastInv inventory) {
        this.inventorys.add(inventory);
    }

    public FastInv createInventory (String shopName) {
        FastInv fastinv = new FastInv(6 * 9, shopName);

        ItemStack defualtItem = Utils.getBorderItem().build();
        int size = fastinv.getInventory().getSize();
        for (int i = 0; i < 9; i++) fastinv.setItem(i, defualtItem);
        for (int i = size - 9; i < size; i++) fastinv.setItem(i, defualtItem);
        for (int i = 9; i < size - 9; i += 9) fastinv.setItem(i, defualtItem);
        for (int i = 17; i < size - 9; i += 9) fastinv.setItem(i, defualtItem);

        return fastinv;
    }

}