package fr.tetemh.skycite.utils;

import fr.tetemh.fastinv.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.translation.GlobalTranslator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Utils {

    public static void spawnCircleParticle(Player player, Location location, double radius) {
        for (double t = 0; t <= 2*Math.PI*radius; t += 0.4) {
            double x = (radius * Math.cos(t)) + location.getX();
            double z = location.getZ() + radius * Math.sin(t);
            Location particle = new Location(location.getWorld(), x, location.getY() + 1, z);
            player.spawnParticle(Particle.HAPPY_VILLAGER, particle, 0, 0, 0, 0);
        }
    }
    public static void spawnCircleParticle(Location location, double radius) {
        for (double t = 0; t <= 2*Math.PI*radius; t += 0.4) {
            double x = (radius * Math.cos(t)) + location.getX();
            double z = location.getZ() + radius * Math.sin(t);
            Location particle = new Location(location.getWorld(), x, location.getY() + 1, z);
            location.getWorld().spawnParticle(Particle.HAPPY_VILLAGER, particle, 0, 0, 0, 0);
        }
    }

    public static String upperFirstOfString(String input) {
        String[] words = input.split("_");
        StringBuilder formattedString = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                formattedString.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase());
            }
            formattedString.append(" ");
        }
        return formattedString.toString().trim();
    }

    public static String getTranslatedMaterialName(Material material) {
        return LegacyComponentSerializer.legacySection().serialize(GlobalTranslator.render(Component.translatable(material.translationKey()), Locale.FRANCE));
    }

    public static String normalizeString(String input) {
        input = input.toLowerCase();
        input = input.replace(" ", "_");
        input = Normalizer.normalize(input, Normalizer.Form.NFD);
        input = input.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return input;
    }

    public static ItemBuilder getBorderItem() {
        return new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).name(" ");
    }

    public static String convertSecondToFormat(int secondes) {
        int hours = secondes / 3600;
        int minutes = (secondes % 3600) / 60;
        int seconds = secondes % 60;

        // Affichage en format hh:mm:ss
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static void fillInventoryWithRandomItems(Inventory inventory, List<ItemStack> items) {
        List<Integer> slots = new ArrayList<>();
        for (int i = 0; i < inventory.getSize(); i++) slots.add(i);
        Collections.shuffle(slots);
        for (ItemStack item : items) {
            if (!slots.isEmpty()) {
                int slot = slots.removeFirst();
                inventory.setItem(slot, item);
            }
        }
    }

}