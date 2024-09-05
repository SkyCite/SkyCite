package fr.tetemh.skycite.custom.customclass;

import de.oliver.fancynpcs.api.FancyNpcsPlugin;
import de.oliver.fancynpcs.api.Npc;
import de.oliver.fancynpcs.api.NpcData;
import de.oliver.fancynpcs.api.utils.SkinFetcher;
import fr.tetemh.fastinv.FastInv;
import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.guis.bank.BankGui;
import fr.tetemh.skycite.utils.Utils;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.NPC;

import java.util.UUID;

@Data
public class Bank {

    private final SkyCite plugin;
    private final String name;
    private final String constantName;
    private final Location location;
    private Npc npc;
    private FastInv inventory;


    public Bank (SkyCite plugin) {
        this.plugin = plugin;
        this.name = "Banquier";
        this.constantName = Utils.normalizeString(this.getName());
        this.location = new Location(Bukkit.getWorld("world"), 0, 100, 5);

        this.genInventory();
    }


    /* DEBUT GESTION NPC */
    public void setNpc() {
        NpcData data = new NpcData(this.getConstantName(), UUID.randomUUID(), this.getLocation());
        SkinFetcher skin = new SkinFetcher("tetemhjpd");
        data.setSkin(skin);
        data.setDisplayName(this.getName());

        this.setNpc(FancyNpcsPlugin.get().getNpcAdapter().apply(data));
        FancyNpcsPlugin.get().getNpcManager().registerNpc(npc);
        npc.create();
        npc.spawnForAll();
    }

    public void disable() {
    }

    /* FIN GESTION NPC */

    private void genInventory() {
        this.setInventory(new BankGui(this.getPlugin(), this));
    }
}
