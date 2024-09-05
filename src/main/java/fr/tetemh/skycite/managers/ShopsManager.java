package fr.tetemh.skycite.managers;

import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.custom.customclass.Shop;
import fr.tetemh.skycite.custom.customclass.Trade;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Data
public class ShopsManager {

    private SkyCite plugin;
    private HashMap<String, Shop> shops = new HashMap<>();

    public ShopsManager(SkyCite plugin) {
        this.setPlugin(plugin);
    }

    public void init() {

        /*  SHOP MINEUR */
        Shop mineur = new Shop(this.getPlugin(), "Mineur", new Location(Bukkit.getWorld("world"), 0, 100, 0, 0, 0));

        mineur.getTrades().add(new Trade(Material.STONE, 1, 20));
        mineur.getTrades().add(new Trade(Material.STONE, 1, 20));
        mineur.getTrades().add(new Trade(Material.STONE, 1, 20));

        this.getShops().put(mineur.getConstantName(), mineur);

        /*  SHOP FLEURISTE */
        Shop fleuriste = new Shop(this.getPlugin(), "Fleuriste", new Location(Bukkit.getWorld("world"), 2, 100, 0, 0, 0));

        fleuriste.getTrades().add(new Trade(Material.STONE, 1, 20));
        fleuriste.getTrades().add(new Trade(Material.STONE, 1, 20));
        fleuriste.getTrades().add(new Trade(Material.STONE, 1, 20));
        fleuriste.getTrades().add(new Trade(Material.STONE, 1, 20));
        fleuriste.getTrades().add(new Trade(Material.STONE, 1, 20));
        fleuriste.getTrades().add(new Trade(Material.STONE, 1, 20));
        fleuriste.getTrades().add(new Trade(Material.STONE, 1, 20));
        fleuriste.getTrades().add(new Trade(Material.STONE, 1, 20));
        fleuriste.getTrades().add(new Trade(Material.STONE, 1, 20));
        fleuriste.getTrades().add(new Trade(Material.STONE, 1, 20));
        fleuriste.getTrades().add(new Trade(Material.STONE, 1, 20));
        fleuriste.getTrades().add(new Trade(Material.STONE, 1, 20));
        fleuriste.getTrades().add(new Trade(Material.STONE, 1, 20));

        this.getShops().put(fleuriste.getConstantName(), fleuriste);

        // Genere les invManager des shops pour le multi page
        this.getShops().values().forEach(Shop::genInventory);
    }

    public void disable() {
        this.shops.values().forEach(shop -> {
            shop.getNpc().removeForAll();
        });
    }

    public Optional<Shop> getShop(String name) {
        return this.shops.values().stream().filter((shop) -> {
            return name.equalsIgnoreCase(shop.getConstantName());
        }).findFirst();
    }
}
