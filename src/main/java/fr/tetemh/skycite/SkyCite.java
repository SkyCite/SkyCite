package fr.tetemh.skycite;

import com.destroystokyo.paper.profile.ProfileProperty;
import fr.tetemh.fastinv.FastInvManager;
import fr.tetemh.skycite.commands.InitCommand;
import fr.tetemh.skycite.commands.MoneyCommand;
import fr.tetemh.skycite.custom.customEvent.callCustomEvent.OnPlayerInteractEntityEvent;
import fr.tetemh.skycite.custom.customclass.Bank;
import fr.tetemh.skycite.events.OnJoin;
import fr.tetemh.skycite.events.OnQuit;
import fr.tetemh.skycite.events.shop.OpenShopEvent;
import fr.tetemh.skycite.managers.PlayersManager;
import fr.tetemh.skycite.managers.ShopsManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.profile.PlayerProfile;

import java.util.Arrays;

public final class SkyCite extends JavaPlugin {

    String[] enableText = {
            "  ____  _           ____ _ _          ___  _   _ ",
            " / ___|| | ___   _ / ___(_) |_ ___   / _ \\| \\ | |",
            " \\___ \\| |/ / | | | |   | | __/ _ \\ | | | |  \\| |",
            "  ___) |   <| |_| | |___| | ||  __/ | |_| | |\\  |",
            " |____/|_|\\_\\\\__, |\\____|_|\\__\\___|  \\___/|_| \\_|",
            "             |___/                               "
    };
    String[] disableText = {
            "  ____  _           ____ _ _          ___  _____ _____ ",
            " / ___|| | ___   _ / ___(_) |_ ___   / _ \\|  ___|  ___|",
            " \\___ \\| |/ / | | | |   | | __/ _ \\ | | | | |_  | |_   ",
            "  ___) |   <| |_| | |___| | ||  __/ | |_| |  _| |  _|  ",
            " |____/|_|\\_\\\\__, |\\____|_|\\__\\___|  \\___/|_|   |_|    ",
            "             |___/                                     "
    };

    @Getter @Setter
    private PlayersManager playersManager;
    @Getter @Setter
    private ShopsManager shopsManager;
    @Getter @Setter
    private Bank bank;


    @Getter
    public static SkyCite instance;

    @Override
    public void onEnable() {
        SkyCite.instance = this;

        Arrays.stream(enableText).forEach(l -> this.getLogger().info(l));

        FastInvManager.register(this);

        this.setPlayersManager(new PlayersManager(this));
        this.setShopsManager(new ShopsManager(this));
        this.setBank(new Bank(this));


        this.getCommand("init").setExecutor(new InitCommand(this));
        this.getCommand("money").setExecutor(new MoneyCommand(this));


        // Calling Basic Event
        this.getServer().getPluginManager().registerEvents(new OnJoin(this), this);
        this.getServer().getPluginManager().registerEvents(new OnQuit(this), this);

        // Event for Calling Custom Event
        this.getServer().getPluginManager().registerEvents(new OnPlayerInteractEntityEvent(this), this);

        // Calling Custom Event
        this.getServer().getPluginManager().registerEvents(new OpenShopEvent(this), this);



        // Init Shop More simple for debug
        this.getShopsManager().init();
        // Spawn Bank
        this.getBank().spawn();
    }

    @Override
    public void onDisable() {
        this.getShopsManager().disable();
        this.getBank().kill();

        Arrays.stream(disableText).forEach(l -> this.getLogger().info(l));
    }
}
