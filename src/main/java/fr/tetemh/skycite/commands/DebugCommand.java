package fr.tetemh.skycite.commands;

import de.oliver.fancynpcs.api.FancyNpcsPlugin;
import fr.tetemh.skycite.SkyCite;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

@Getter
public class DebugCommand implements CommandExecutor {

    private final SkyCite plugin;
    public DebugCommand (SkyCite plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(args.length == 2 && args[0].equalsIgnoreCase("npc") && args[1].equalsIgnoreCase("count")) {
            sender.sendMessage(Component.text("NPC total : " + (long) FancyNpcsPlugin.get().getNpcManager().getAllNpcs().size()));
        }
        if(args.length == 2 && args[0].equalsIgnoreCase("shop") && args[1].equalsIgnoreCase("count")) {
            sender.sendMessage(Component.text("NPC total : " + (long) this.getPlugin().getShopsManager().getShops().size()));
        }

        return false;
    }
}
