package com.realgotqkura.runeCosmetics.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.lang.reflect.Field;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

public class SkullGiver {

    public static ItemStack getSkullFromBase64(String input) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();

        try {
            // 🔍 Decode Base64 → extract URL
            String decoded = new String(Base64.getDecoder().decode(input));

            // crude but effective extraction
            String url = decoded.split("\"url\":\"")[1].split("\"")[0];

            PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID(), "custom_head");
            PlayerTextures textures = profile.getTextures();
            textures.setSkin(new URL(url));
            profile.setTextures(textures);

            meta.setOwnerProfile(profile);

        } catch (Exception e) {
            e.printStackTrace();
        }

        head.setItemMeta(meta);
        return head;
    }
}
