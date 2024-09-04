package fr.tetemh.skycite.commands;

import fr.tetemh.skycite.SkyCite;
import lombok.Getter;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicInteger;

public class DebugCommand implements CommandExecutor {

    @Getter
    private final SkyCite plugin;
    public DebugCommand (SkyCite plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        if(args.length == 2 && args[0].equalsIgnoreCase("npc") && args[1].equalsIgnoreCase("count")) {
            AtomicInteger npcCount = new AtomicInteger();
            CitizensAPI.getNPCRegistry().forEach(npc -> npcCount.getAndIncrement());

            sender.sendMessage(Component.text("NPC Count : " + npcCount.get()));
        }
        if(args.length == 2 && args[0].equalsIgnoreCase("npc") && args[1].equalsIgnoreCase("kill")) {
            CitizensAPI.getNPCRegistry().forEach(NPC::destroy);
            sender.sendMessage(Component.text("NPC Kill"));
        }
        if(args.length == 2 && args[0].equalsIgnoreCase("npc") && args[1].equalsIgnoreCase("spawn")) {
            this.plugin.getShopsManager().init();
            this.plugin.getBank().setNPC();
            this.plugin.getBank().spawn();

            sender.sendMessage(Component.text("NPC Spawn"));
        }
        if(args.length == 2 && args[0].equalsIgnoreCase("npc") && args[1].equalsIgnoreCase("ok")) {
            if(this.getPlugin().getConfig().getString("npcs." + this.getConstantName()) != null) {
                this.setNpc(CitizensAPI.getNPCRegistry().getByUniqueId(UUID.fromString(this.getPlugin().getNpcConfig().getString("npcs." + this.getConstantName()))));
            }

            sender.sendMessage(Component.text("NPC Spawn"));
        }
        return false;
    }
}
