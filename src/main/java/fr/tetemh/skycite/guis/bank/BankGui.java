package fr.tetemh.skycite.guis.bank;

import fr.tetemh.fastinv.FastInv;
import fr.tetemh.fastinv.ItemBuilder;
import fr.tetemh.skycite.SkyCite;
import fr.tetemh.skycite.custom.customclass.Bank;
import fr.tetemh.skycite.custom.customclass.CPlayer;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class BankGui extends FastInv {

    @Getter
    private final SkyCite plugin;

    public BankGui(SkyCite plugin, Bank bank) {
        super(3*9, bank.getName());

        this.plugin = plugin;

        this.setItem(13, new ItemBuilder(Material.EMERALD).name("§r§fDéposer vos émeraude").build(), event -> {
            if(event.getWhoClicked() instanceof Player player) {
                CPlayer cPlayer = this.getPlugin().getPlayersManager().getPlayers().get(player.getUniqueId());

                AtomicInteger emeralds = new AtomicInteger();
                AtomicInteger emerald_blocks = new AtomicInteger();
                Arrays.stream(player.getInventory().getContents()).forEach(stacks -> {
                    if(stacks == null) return;
                    if(stacks.getType().equals(Material.EMERALD)) emeralds.addAndGet(stacks.getAmount());
                    if(stacks.getType().equals(Material.EMERALD_BLOCK)) emerald_blocks.addAndGet(stacks.getAmount());
                });

                int money = (9 * emerald_blocks.get()) + emeralds.get();

                player.getInventory().remove(Material.EMERALD);
                player.getInventory().remove(Material.EMERALD_BLOCK);

                cPlayer.setMoney(cPlayer.getMoney() + money);
            }
        });
    }
}
