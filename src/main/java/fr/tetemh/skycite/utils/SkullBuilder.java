package fr.tetemh.skycite.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class SkullBuilder {
    private ItemStack headItem = new ItemStack(Material.PLAYER_HEAD);
    private SkullMeta meta = (SkullMeta) headItem.getItemMeta();

    public SkullBuilder withName(String name) {
        meta.setOwner(name);
        return this;
    }

    public SkullBuilder withUUID(UUID id) {
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(id));
        return this;
    }

    public SkullBuilder withUrl(String url) {
        return this.itemWithBase64(urlToBase64(url));
    }

    public SkullBuilder itemWithBase64(String base64) {
        mutateItemMeta(base64);
        return this;
    }

    public SkullBuilder name(String name) {
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        return this;
    }

    public SkullBuilder lore(String... lore) {
        return this.lore(Arrays.asList(lore));
    }

    public SkullBuilder lore(String lore) {
        return this.lore(Collections.singletonList(lore));
    }

    public SkullBuilder lore(List<String> lore) {
        this.meta.setLore(lore);
        return this;
    }

    public SkullBuilder addLore(String line) {
        List<String> lore = this.meta.getLore();

        if (lore == null) {
            return lore(line);
        }

        lore.add(line);
        return this.lore(lore);
    }

    public ItemStack build() {
        headItem.setItemMeta(meta);
        return headItem;
    }

    private String urlToBase64(String url) {

        URI actualUrl;
        try {
            actualUrl = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String toEncode = "{\"textures\":{\"SKIN\":{\"url\":\"" + actualUrl.toString() + "\"}}}";
        return Base64.getEncoder().encodeToString(toEncode.getBytes());
    }

    private GameProfile makeProfile(String b64) {
        UUID id = new UUID(
                b64.substring(b64.length() - 20).hashCode(),
                b64.substring(b64.length() - 10).hashCode()
        );
        GameProfile profile = new GameProfile(id, "Player");
        profile.getProperties().put("textures", new Property("textures", b64));
        return profile;
    }

    private void mutateItemMeta(String b64) {
        try {
            Method metaSetProfileMethod = this.meta.getClass().getDeclaredMethod("setProfile", GameProfile.class);
            metaSetProfileMethod.setAccessible(true);
            metaSetProfileMethod.invoke(this.meta, makeProfile(b64));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        }
    }
}