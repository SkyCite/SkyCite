package fr.tetemh.skycite.custom.customclass;

import fr.tetemh.fastinv.FastInv;
import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.guis.bank.BankGui;
import lombok.Data;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

@Data
public class Bank {

    private final SkyCite plugin;
    private final String name;
    private final Location location;
    private NPC npc;
    private FastInv inventory;


    public Bank (SkyCite plugin) {
        this.plugin = plugin;

        // this.name = this.getPlugin().getNpcConfig().getString("npcs.bank.name");
        // this.location = this.getPlugin().getNpcConfig().getLocation("npcs.bank.location");

        this.name = "Banquier";
        this.location = new Location(Bukkit.getWorld("world"), 0, 100, 5);

        this.genInventory();
        CitizensAPI.getNPCRegistry().get
        this.setNpc(CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, this.getName()));
    }

    public void spawn () {

        //Citizen Conf
        this.getNpc().setFlyable(true);
        this.getNpc().setUseMinecraftAI(false);
        this.getNpc().setProtected(true);

        SkinTrait skinTrait = npc.getOrAddTrait(SkinTrait.class);
        skinTrait.setSkinName("NomDuJoueurAvecCeSkin");
        this.getNpc().addTrait((Trait) skinTrait);

        // Spawn Entity
        this.getNpc().spawn(this.getLocation());

        // Param on Entity
        this.getNpc().getEntity().getPersistentDataContainer().set(new NamespacedKey(this.plugin, "npc_type"), PersistentDataType.STRING, "bank");
        this.getNpc().getEntity().setGravity(false);
    }

    public void kill() {
        this.getNpc().despawn();
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
