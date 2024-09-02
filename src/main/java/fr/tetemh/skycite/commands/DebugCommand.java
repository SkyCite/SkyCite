package fr.tetemh.skycite.commands;

import net.citizensnpcs.api.CitizensAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DebugCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player) {
            CitizensAPI.getNPCRegistry().forEach(npc -> player.sendMessage(npc.getName()));
        }
        return false;
    }
}
