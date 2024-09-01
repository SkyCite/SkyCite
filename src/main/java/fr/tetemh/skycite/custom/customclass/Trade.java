package fr.tetemh.skycite.custom.customclass;

import fr.tetemh.fastinv.ItemBuilder;
import fr.tetemh.skycite.utils.SkullBuilder;
import fr.tetemh.skycite.utils.Utils;
import lombok.Data;
import org.bukkit.Material;

@Data
public class Trade {

    private Material item;
    private int amountItem;
    private int price;

    public Trade(Material item, int amountItem, int price) {
        this.setItem(item);
        this.setAmountItem(amountItem);
        this.setPrice(price);
    }

    public ItemBuilder getSellItem() {
        return new ItemBuilder(this.item).amount(this.amountItem);
    }

    public SkullBuilder getArrowItem() {
        return new SkullBuilder().withUrl("http://textures.minecraft.net/texture/19bf3292e126a105b54eba713aa1b152d541a1d8938829c56364d178ed22bf").name("§r§7→");
    }

    public ItemBuilder getPriceItem() {
        return new ItemBuilder(Material.EMERALD).amount(this.price).name("§r§f" + this.price + " Emeraude");
    }
}
