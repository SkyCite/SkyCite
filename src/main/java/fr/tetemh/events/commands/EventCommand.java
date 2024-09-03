package fr.tetemh.events.commands;

import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.utils.Utils;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EventCommand implements CommandExecutor {

    @Getter
    private final SkyCite plugin;

    public EventCommand (SkyCite plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(commandSender instanceof Player player) {

            if(strings.length == 0) {
                player.sendMessage(Component.text("§eListe des évènements :"));
                this.getPlugin().getEvents().getEventsManager().getEvents().values().forEach(event -> {
                    player.sendMessage(Component.text("- " + event.getName() + " [" + Utils.upperFirstOfString(event.getStatus().name()) + "]"));
                });
            }

        }


        return false;
    }
}
