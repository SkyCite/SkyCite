package fr.tetemh.events.commands;

import fr.tetemh.events.customclass.Event;
import fr.tetemh.events.events.ChasseAuTresor;
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
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

//        if(commandSender instanceof Player player) {

            if(args.length == 0) {
                commandSender.sendMessage(Component.text("§eListe des évènements :"));
                this.getPlugin().getEvents().getEventsManager().getEvents().values().forEach(e -> {
                    Event event = (Event) e;
                    commandSender.sendMessage(Component.text("- " + event.getName() + " [" + Utils.upperFirstOfString(event.getStatus().name()) + "]"));
                });
            }

            if(args.length == 1) {
                Event event = (Event) this.getPlugin().getEvents().getEventsManager().getEvents().get(args[0]);
                if(event == null) commandSender.sendMessage(Component.text("Usage : /event <eventName> <command>"));
                commandSender.sendMessage(Component.text("§e----<" + event.getName() + ">----"));
                commandSender.sendMessage(Component.text("§eStatus : " + Utils.upperFirstOfString(event.getStatus().name())));
            }

            if(args.length >= 2) {
                Event event = (Event) this.getPlugin().getEvents().getEventsManager().getEvents().get(args[0]);
                if(event == null) commandSender.sendMessage(Component.text("Usage : /event <eventName> <command>"));

                switch(args[0]) {
                    case "cat" -> {
                        ChasseAuTresor cat = (ChasseAuTresor) event;
                        switch(args[1]) {
                            case "start" -> cat.start();
                            case "stop" -> cat.stop();
                            case "pause" -> cat.pause();
                        }
                    }
                }
            }

//        }


        return false;
    }
}
