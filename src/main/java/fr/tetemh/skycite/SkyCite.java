package fr.tetemh.skycite;

import fr.tetemh.fastinv.FastInvManager;
import fr.tetemh.skycite.commands.DebugCommand;
import fr.tetemh.skycite.commands.InitCommand;
import fr.tetemh.skycite.commands.MoneyCommand;
import fr.tetemh.skycite.custom.customEvent.callCustomEvent.OnPlayerInteractEntityEvent;
import fr.tetemh.skycite.custom.customclass.Bank;
import fr.tetemh.skycite.events.OnJoin;
import fr.tetemh.skycite.events.OnQuit;
import fr.tetemh.skycite.events.shop.NPCClientEvent;
import fr.tetemh.skycite.managers.BoardsManager;
import fr.tetemh.skycite.managers.PlayersManager;
import fr.tetemh.skycite.managers.ShopsManager;
import lombok.Getter;
import lombok.Setter;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
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
    private BoardsManager boardsManager;
    @Getter @Setter
    private ShopsManager shopsManager;
    @Getter @Setter
    private Bank bank;

    // Config File
    @Getter @Setter
    private YamlConfiguration npcConfig;
    @Getter @Setter
    private YamlConfiguration tradesConfig;


    @Getter
    public static SkyCite instance;

    @Override
    public void onEnable() {

        SkyCite.instance = this;

        // Text Plugin ON
        Arrays.stream(enableText).forEach(l -> this.getLogger().info(l));

        // Config Files
        try {
            File npcFile = new File(this.getDataFolder(), "npc.yml");
            this.setNpcConfig(YamlConfiguration.loadConfiguration(npcFile));
            this.getNpcConfig().save(npcFile);

            File tradesFile = new File(this.getDataFolder(), "trades.yml");
            this.setTradesConfig(YamlConfiguration.loadConfiguration(tradesFile));
            this.getTradesConfig().save(tradesFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Define Managers
        this.setPlayersManager(new PlayersManager(this));
        this.setBoardsManager(new BoardsManager(this));
        this.setShopsManager(new ShopsManager(this));
        this.setBank(new Bank(this));

        // Register FastInv Event
        FastInvManager.register(this);

        // Define Command
        this.getCommand("init").setExecutor(new InitCommand(this));
        this.getCommand("money").setExecutor(new MoneyCommand(this));
        this.getCommand("debug").setExecutor(new DebugCommand());


        // Calling Basic Event
        this.getServer().getPluginManager().registerEvents(new OnJoin(this), this);
        this.getServer().getPluginManager().registerEvents(new OnQuit(this), this);

        // Event for Calling Custom Event
        this.getServer().getPluginManager().registerEvents(new OnPlayerInteractEntityEvent(this), this);

        // Calling Custom Event
        this.getServer().getPluginManager().registerEvents(new NPCClientEvent(this), this);



        // Init Shop More simple for debug
        this.getShopsManager().init();
        // Spawn Bank
        this.getBank().spawn();
    }

    @Override
    public void onDisable() {
//        this.getShopsManager().disable();
//        this.getBank().disable();

        //Kill All NPC
        this.getBank().getNpc().destroy();

        Arrays.stream(disableText).forEach(l -> this.getLogger().info(l));
    }
}
