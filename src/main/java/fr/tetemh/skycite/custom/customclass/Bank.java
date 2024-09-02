package fr.tetemh.skycite.custom.customclass;

import de.oliver.fancynpcs.api.FancyNpcsPlugin;
import de.oliver.fancynpcs.api.Npc;
import de.oliver.fancynpcs.api.NpcData;
import de.oliver.fancynpcs.api.utils.SkinFetcher;
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

import java.util.UUID;

@Data
public class Bank {

    private final SkyCite plugin;
    private final String name;
    private final Location location;
    private Npc npc;
    private FastInv inventory;


    public Bank (SkyCite plugin) {
        this.plugin = plugin;

        this.name = this.getPlugin().getNpcConfig().getString("npcs.bank.name");
        this.location = this.getPlugin().getNpcConfig().getLocation("npcs.bank.location");

//        this.name = "Banquier";
//        this.location = new Location(Bukkit.getWorld("world"), 0, 100, 5);

        this.genInventory();
    }

    public void spawn () {
        NpcData data = new NpcData("banquier", UUID.fromString("56699713a9ae470fbb494d78dd9574cc"), this.getLocation());
        SkinFetcher skin = new SkinFetcher("https://minecraft.novaskin.me/download/1027933118");
        data.setSkin(skin);
        data.setDisplayName(this.getName());


        this.setNpc(FancyNpcsPlugin.get().getNpcAdapter().apply(data));
        FancyNpcsPlugin.get().getNpcManager().registerNpc(this.getNpc());
        this.getNpc().create();
        this.getNpc().getData().setOnClick(player -> {
            this.getInventory().open(player);
        });
        this.getNpc().spawnForAll();
    }

    public void kill() {
        this.getNpc().removeForAll();
    }

    private void genInventory() {
        this.setInventory(new BankGui(this.getPlugin(), this));
    }

    public void disable() {
        this.getPlugin().getNpcConfig().set("npcs.bank.name", this.getName());
        this.getPlugin().getNpcConfig().set("npcs.bank.location", this.getLocation());

        this.kill();
    }
}
