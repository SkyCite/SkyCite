package fr.tetemh.skycite.commands;

import fr.tetemh.skycite.SkyCite;
import lombok.Getter;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadNPCCommand implements CommandExecutor {

    @Getter
    private final SkyCite plugin;

    public ReloadNPCCommand(SkyCite plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        //Realod Bank
        this.getPlugin().getBank().disable();
        this.getPlugin().getBank().setNPC();
        this.getPlugin().getBank().spawn();

        //Reload Shops
        this.getPlugin().getShopsManager().getShops().values().forEach(shop -> {
            shop.disable();
            shop.setNpc();
            shop.spawn();
        });

        CitizensAPI.getNPCRegistry().forEach(npc -> sender.sendMessage(npc.getName()));

        return false;
    }
}
