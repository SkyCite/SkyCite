package fr.tetemh.skycite.commands;

import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.custom.customclass.CPlayer;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Getter
public class MoneyCommand implements CommandExecutor {

    private final SkyCite plugin;

    public MoneyCommand(SkyCite plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player player) {
            CPlayer cPlayer = this.getPlugin().getPlayersManager().getPlayers().get(player.getUniqueId());
            player.sendMessage(Component.text("§c[DEBUG] §r§eVotre money est de : " + cPlayer.getMoney()));
        }

        return false;
    }
}
