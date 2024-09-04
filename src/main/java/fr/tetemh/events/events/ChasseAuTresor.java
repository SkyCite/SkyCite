package fr.tetemh.events.events;

import fr.tetemh.events.customclass.CChest;
import fr.tetemh.events.customclass.Event;
import fr.tetemh.events.runnables.ChasseAuTresorBoardRunnable;
import fr.tetemh.events.runnables.ChasseAuTresorRunnable;
import fr.tetemh.fastinv.ItemBuilder;
import fr.tetemh.skycite.SkyCite;
import lombok.Data;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChasseAuTresor extends Event {

    @Getter
    private List<CChest> chests = new ArrayList<>();
    @Getter
    private ChasseAuTresorRunnable chasseAuTresorRunnable;

    public ChasseAuTresor(SkyCite plugin) {
        super(plugin, "Chasse Au Trésor");
        Random random = new Random();

        this.chasseAuTresorRunnable = new ChasseAuTresorRunnable(this.getPlugin(), this);
        this.setBoardRunnable(new ChasseAuTresorBoardRunnable(this.getPlugin(), this));

        // Chests
        CChest farmingChest = new CChest(new Location(this.getPlugin().getServer().getWorld("world"), 5, 100, 0), "Farmin");
        farmingChest.getItems().add(
                new ItemBuilder(Material.DIAMOND_PICKAXE)
                        .enchant(Enchantment.EFFICIENCY, 3)
                        .enchant(Enchantment.UNBREAKING, 3)
        );
        farmingChest.getItems().add(
                new ItemBuilder(Material.NETHERITE_SCRAP)
        );
        for (int i = 0; i < 5; i++) {
            int randomNumber = random.nextInt(6) + 1;
            farmingChest.getItems().add(
                    new ItemBuilder(Material.GOLDEN_CARROT)
                            .amount(randomNumber)
            );
        }
        this.getChests().add(farmingChest);
    }

    public void start() {
        this.getChasseAuTresorRunnable().runTaskTimer(this.getPlugin(), 0, 20);
    }

    public void stop() {
    }

    public void pause() {
    }
}
