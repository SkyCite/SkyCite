package fr.tetemh.skycite.managers;

import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.custom.customclass.Shop;
import fr.tetemh.skycite.custom.customclass.Trade;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShopsManager {

    private SkyCite plugin;
    private List<Shop> shops = new ArrayList<>();

    public ShopsManager(SkyCite skyCite) {
        this.plugin = plugin;
    }

    public void init() {

        /*  SHOP MINEUR */
        Shop mineur = new Shop("Mineur");
        mineur.setLocation(0, 100, 0, 0, 0);

        // Trades
        mineur.getTrades().add(new Trade(Material.STONE, 1, 64));
        mineur.getTrades().add(new Trade(Material.STONE, 64, 1));
        mineur.getTrades().add(new Trade(Material.STONE, 1, 1));
        mineur.getTrades().add(new Trade(Material.STONE, 1, 1));
        mineur.getTrades().add(new Trade(Material.STONE, 1, 1));
        mineur.getTrades().add(new Trade(Material.STONE, 1, 1));
        mineur.getTrades().add(new Trade(Material.STONE, 1, 1));
        mineur.getTrades().add(new Trade(Material.STONE, 1, 1));
        mineur.getTrades().add(new Trade(Material.STONE, 1, 1));
        mineur.getTrades().add(new Trade(Material.STONE, 1, 1));
        mineur.getTrades().add(new Trade(Material.STONE, 1, 1));
        mineur.getTrades().add(new Trade(Material.STONE, 1, 1));

        // Ajout Shop
        shops.add(mineur);

        /*  SHOP FLEURISTE */
        Shop fleuriste = new Shop("Fleuriste");
        fleuriste.setLocation(2, 100, 0, 0, 0);

        // Trades
        fleuriste.getTrades().add(new Trade(Material.STONE, 1, 64));
        fleuriste.getTrades().add(new Trade(Material.STONE, 64, 1));
        fleuriste.getTrades().add(new Trade(Material.STONE, 1, 1));
        fleuriste.getTrades().add(new Trade(Material.STONE, 1, 1));
        fleuriste.getTrades().add(new Trade(Material.STONE, 1, 1));

        // Ajout Shop
        shops.add(fleuriste);

        // Boucle Spawn Shop
        shops.forEach(Shop::genInventory);
        shops.forEach(Shop::spawn);
    }

    public void disable() {
        this.shops.forEach(Shop::kill);
    }

    public Optional<Shop> getShop(String name) {
        return this.shops.stream().filter((shop) -> {
            return name.equalsIgnoreCase(shop.getConstantName());
        }).findFirst();
    }
}
