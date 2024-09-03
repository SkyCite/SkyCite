package fr.tetemh.skycite.custom.customclass;

import fr.tetemh.fastinv.FastInv;
import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.guis.bank.BankGui;
import lombok.Data;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.trait.Gravity;
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
        this.name = "Banquier";
        this.location = new Location(Bukkit.getWorld("world"), 0, 100, 5);

        this.genInventory();
    }

    public void setNPC() {
        for (NPC npc : CitizensAPI.getNPCRegistry()) {
            if (npc.getName().equals(this.getName())) {
                this.setNpc(npc);
                return;
            }
        }

        // Créer un nouveau NPC
        this.setNpc(CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, this.getName()));
    }

    public void spawn () {
        if(this.getNpc().isSpawned()) return;
        //Citizen Conf
        this.getNpc().setFlyable(true);
        this.getNpc().setUseMinecraftAI(false);
        this.getNpc().setProtected(true);

        //Set Skin
        SkinTrait skinTrait = this.getNpc().getOrAddTrait(SkinTrait.class);
        skinTrait.setSkinName("tetemhjpd");
        this.getNpc().addTrait((Trait) skinTrait);

        //Add Parametres
        this.getNpc().getOrAddTrait(Gravity.class).toggle();
        this.getNpc().data().set("npc_type", "bank");

        // Spawn Entity
        this.getNpc().spawn(this.getLocation());
    }

    private void genInventory() {
        this.setInventory(new BankGui(this.getPlugin(), this));
    }

    public void disable() {
        this.getNpc().despawn();
        this.getNpc().destroy();
    }
}
