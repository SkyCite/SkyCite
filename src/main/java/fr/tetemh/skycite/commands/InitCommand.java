package fr.tetemh.skycite.commands;

import fr.tetemh.skycite.SkyCite;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class InitCommand implements CommandExecutor {

    private final SkyCite plugin;

    public InitCommand (SkyCite plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        this.plugin.getShopsManager().init();

        this.plugin.getBank().setNPC();
        this.plugin.getBank().spawn();
        return false;
    }
}
