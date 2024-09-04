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
    @Getter
    private ChasseAuTresorBoardRunnable boardRunnable;

    public ChasseAuTresor(SkyCite plugin) {
        super(plugin, "Chasse Au Trésor", "cat");
        Random random = new Random();

        this.chasseAuTresorRunnable = new ChasseAuTresorRunnable(this.getPlugin(), this);
        this.setBoardRunnable(new ChasseAuTresorBoardRunnable(this.getPlugin(), this));

        // Chests
        CChest farmingChest = new CChest(new Location(this.getPlugin().getServer().getWorld("world"), 3, 100, -4), "Farmin");
        farmingChest.getItems().add(
                new ItemBuilder(Material.DIAMOND_HOE)
                        .name("§3Hoe attention toi !")
                        .enchant(Enchantment.EFFICIENCY, 5)
                        .enchant(Enchantment.UNBREAKING, 3)
                        .build()
        );
        for (int i = 0; i < 5; i++) {
            int randomNumber = random.nextInt(6) + 1;
            farmingChest.getItems().add(
                    new ItemBuilder(Material.POTATO)
                            .amount(randomNumber)
                            .build()
            );
        }
        for (int i = 0; i < 5; i++) {
            int randomNumber = random.nextInt(6) + 1;
            farmingChest.getItems().add(
                    new ItemBuilder(Material.GOLDEN_CARROT)
                            .amount(randomNumber)
                            .build()
            );
        }
        for (int i = 0; i < 5; i++) {
            int randomNumber = random.nextInt(6) + 1;
            farmingChest.getItems().add(
                    new ItemBuilder(Material.CARROT)
                            .amount(randomNumber)
                            .build()
            );
        }
        this.getChests().add(farmingChest);

        CChest miningChest = new CChest(new Location(this.getPlugin().getServer().getWorld("world"), 3, 100, -4), "Mineur");
        miningChest.getItems().add(
                new ItemBuilder(Material.DIAMOND_PICKAXE)
                        .enchant(Enchantment.EFFICIENCY, 5)
                        .enchant(Enchantment.UNBREAKING, 3)
                        .build()
        );
        miningChest.getItems().add(
                new ItemBuilder(Material.NETHERITE_SCRAP)
                        .build()
        );
        for (int i = 0; i < 5; i++) {
            int randomNumber = random.nextInt(6) + 1;
            miningChest.getItems().add(
                    new ItemBuilder(Material.DIAMOND)
                            .amount(randomNumber)
                            .build()
            );
        }
        this.getChests().add(miningChest);

        CChest adventurerChest = new CChest(new Location(this.getPlugin().getServer().getWorld("world"), 3, 100, -4), "Explorateur");
        adventurerChest.getItems().add(
                new ItemBuilder(Material.SPYGLASS)
                        .build()
        );
        adventurerChest.getItems().add(
                new ItemBuilder(Material.HEART_OF_THE_SEA)
                        .build()
        );
        for (int i = 0; i < 5; i++) {
            int randomNumber = random.nextInt(6) + 1;
            adventurerChest.getItems().add(
                    new ItemBuilder(Material.EMERALD)
                            .amount(randomNumber)
                            .build()
            );
        }
        this.getChests().add(adventurerChest);
    }

    public void start() {
        this.getChasseAuTresorRunnable().runTaskTimer(this.getPlugin(), 0, 20);
        this.getBoardRunnable().runTaskTimer(this.getPlugin(), 0, 5);
    }

    @Override
    public void stop() {
        if(this.getBoardRunnable() != null) if(this.getBoardRunnable().isStart()) this.getBoardRunnable().cancel();
        if(this.getChasseAuTresorRunnable() != null) if(this.getChasseAuTresorRunnable().isStart()) this.getChasseAuTresorRunnable().cancel();
    }

    public void pause() {
    }
}
