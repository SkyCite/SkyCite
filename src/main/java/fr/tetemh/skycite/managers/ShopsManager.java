package fr.tetemh.skycite.managers;

import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.custom.customclass.Shop;
import fr.tetemh.skycite.custom.customclass.Trade;
import lombok.Data;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Data
public class ShopsManager {

    private SkyCite plugin;
    private HashMap<String, Shop> shops = new HashMap<>();

    public ShopsManager(SkyCite skyCite) {
        this.setPlugin(plugin);
    }

    public void init() {

        /*  SHOP MINEUR */
        Shop mineur = new Shop(this.getPlugin(), "Mineur");
        mineur.setLocation(0, 100, 0, 0, 0);
        this.getShops().put(mineur.getConstantName(), mineur);

        /*  SHOP FLEURISTE */
        Shop fleuriste = new Shop(this.getPlugin(), "Fleuriste");
        fleuriste.setLocation(2, 100, 0, 0, 0);
        this.getShops().put(fleuriste.getConstantName(), fleuriste);

        // Boucle Spawn Shop
        this.getShops().values().forEach(shop -> {
            shop.genInventory();
            shop.spawn();
        });
    }

    public void disable() {
        this.shops.values().forEach(shop -> {
//            this.getPlugin().getNpcConfig().set("npcs.shops." + shop.getConstantName() + ".name", shop.getName());
//            this.getPlugin().getNpcConfig().set("npcs.shops." + shop.getConstantName() + ".constant_name", shop.getConstantName());
//            this.getPlugin().getNpcConfig().set("npcs.shops." + shop.getConstantName() + ".location", shop.getLocation());
            this.getPlugin().getNpcConfig().set("npcs.shops." + shop.getConstantName(), shop);
        });
    }

    public Optional<Shop> getShop(String name) {
        return this.shops.values().stream().filter((shop) -> {
            return name.equalsIgnoreCase(shop.getConstantName());
        }).findFirst();
    }
}
