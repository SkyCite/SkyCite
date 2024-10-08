package fr.tetemh.skycite.custom.customclass;

import de.oliver.fancynpcs.api.FancyNpcsPlugin;
import de.oliver.fancynpcs.api.Npc;
import de.oliver.fancynpcs.api.NpcData;
import de.oliver.fancynpcs.api.utils.SkinFetcher;
import fr.tetemh.fastinv.FastInv;
import fr.tetemh.fastinv.ItemBuilder;
import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.managers.InventoryManager;
import fr.tetemh.skycite.utils.Utils;
import lombok.Data;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Data
public class Shop {

    private SkyCite plugin;

//    private NPC npc;
    private Npc npc;
    private String name;
    private String constantName;
    private Location location;
    private List<Trade> trades = new ArrayList<>();
    private InventoryManager inventoryManager;

    public Shop(SkyCite plugin, String shopName, Location location) {
        this.setPlugin(plugin);

        this.setName(shopName);
        this.setConstantName(Utils.normalizeString(shopName));
        this.setLocation(location);
        this.setInventoryManager(new InventoryManager());

//        if(this.getPlugin().getConfig().getString("npcs." + this.getConstantName()) != null) {
//            this.setNpc(CitizensAPI.getNPCRegistry().getByUniqueId(UUID.fromString(this.getPlugin().getNpcConfig().getString("npcs." + this.getConstantName()))));
//        } else {
//            this.setNpc();
//            this.spawn();
//        }
        this.setNpc();
    }

    /* DEBUT GESTION NPC */
    public void setNpc() {
        System.out.println("ok");
        NpcData data = new NpcData(this.getConstantName(), UUID.randomUUID(), this.getLocation());
        SkinFetcher skin = new SkinFetcher("tetemhjpd");
        data.setSkin(skin);
        data.setDisplayName(this.getName());

        this.setNpc(FancyNpcsPlugin.get().getNpcAdapter().apply(data));
        FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);
        npc.create();
        npc.spawnForAll();
        System.out.println(this.getNpc().getData().isSpawnEntity());
    }

    public void disable() {
    }

    /* FIN GESTION NPC */

    public void genInventory() {

        AtomicInteger nbTotalSellItem = new AtomicInteger(0);
        AtomicInteger nbItem = new AtomicInteger(0);
        AtomicInteger first = new AtomicInteger(12);
        AtomicInteger second = new AtomicInteger(13);
        AtomicInteger three = new AtomicInteger(14);
        AtomicReference<FastInv> inv = new AtomicReference<>(this.inventoryManager.createInventory(this.getName()));

        this.getTrades().forEach(trade -> {
            if(nbItem.get() == 4) {

                inv.get().setItem(52, new ItemBuilder(Material.ARROW).name("§7→").build(), event -> {
                    this.getInventoryManager().nextView().open((Player) event.getWhoClicked());
                });

                this.inventoryManager.addInventory(inv.get());
                inv.set(this.inventoryManager.createInventory(this.getName()));

                inv.get().setItem(46, new ItemBuilder(Material.ARROW).name("§7←").build(), event -> {
                    this.getInventoryManager().previousView().open((Player) event.getWhoClicked());
                });

                nbItem.set(0);
                first.set(12);
                second.set(13);
                three.set(14);
            }

            inv.get().setItem(first.get(), trade.getSellItem().build());
            inv.get().setItem(second.get(), trade.getArrowItem().build());
            inv.get().setItem(three.get(), trade.getPriceItem().build(), e -> this.processTrade((Player) e.getWhoClicked(), trade));

            if(nbTotalSellItem.incrementAndGet() == this.getTrades().size())
                this.inventoryManager.addInventory(inv.get());

            first.addAndGet(9);
            second.addAndGet(9);
            three.addAndGet(9);

            nbItem.incrementAndGet();
        });
    }

    /*   TRADE SYSTEME   */

    public boolean processTrade(Player player, Trade trade) {
        Inventory inventory = player.getInventory();

        Material requiredMaterial = trade.getItem();
        int requiredAmount = trade.getAmountItem();
        if (!hasRequiredItems(inventory, requiredMaterial, requiredAmount)) {
            player.sendMessage("Vous n'avez pas les items nécessaires pour ce trade !");
            return false;
        }

        int emeraldBlockCount = trade.getPrice() / 9;
        int emeraldCount = trade.getPrice() % 9;

        ItemStack emeralds = new ItemStack(Material.EMERALD, emeraldCount);
        ItemStack emeraldBlocks = null;
        if(emeraldBlockCount > 0) {
            emeraldBlocks = new ItemStack(Material.EMERALD_BLOCK, emeraldBlockCount);
        }

        if (!hasEnoughSpace(inventory, emeraldBlocks, emeralds)) {
            player.sendMessage("Vous n'avez pas assez de place dans votre inventaire !");
            return false;
        }
        removeItems(inventory, requiredMaterial, requiredAmount);
        if(emeraldBlocks != null) inventory.addItem(emeraldBlocks);
        inventory.addItem(emeralds);

        player.sendMessage(Component.text("§8[§r§a+§r§8] §ax" + trade.getPrice() + " emeralds"));
        player.sendMessage(Component.text("§8[§r§c-§r§8] §cx" + trade.getAmountItem() + " " + Utils.getTranslatedMaterialName(trade.getItem())));
        return true;
    }

    private boolean hasRequiredItems(Inventory inventory, Material requiredMaterial, int requiredAmount) {
        int foundAmount = 0;

        for (ItemStack item : inventory.getContents()) {
            if (item != null && item.getType() == requiredMaterial) {
                foundAmount += item.getAmount();
                if (foundAmount >= requiredAmount) return true;
            }
        }
        return false;
    }

    private void removeItems(Inventory inventory, Material requiredMaterial, int requiredAmount) {
        int remainingAmount = requiredAmount;

        for (ItemStack item : inventory.getContents()) {
            if (item != null && item.getType() == requiredMaterial) {
                if (item.getAmount() >= remainingAmount) {
                    item.setAmount(item.getAmount() - remainingAmount);
                    break;
                } else {
                    remainingAmount -= item.getAmount();
                    inventory.remove(item);
                }
            }
        }
    }

    private boolean hasEnoughSpace(Inventory inventory, ItemStack... items) {
        int availableSlots = 0;
        AtomicInteger partialSpace = new AtomicInteger();

        for (ItemStack item : inventory.getStorageContents()) {
            if (item == null || item.getType() == Material.AIR) {
                availableSlots++;
            } else {
                for (ItemStack stack : items) {
                    if (item.isSimilar(stack)) {
                        partialSpace.addAndGet((item.getMaxStackSize() - item.getAmount()));
                    }
                }
            }
        }

        int totalItems = 0;
        for (ItemStack stack : items) if(stack != null) totalItems += stack.getAmount();
        int fullSlotsNeeded = totalItems / 64;
        int remainingItems = totalItems % 64;
        return availableSlots >= fullSlotsNeeded + (remainingItems > 0 ? 1 : 0);
    }
}
