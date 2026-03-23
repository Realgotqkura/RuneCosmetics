package com.realgotqkura.runeCosmetics.utils;

import com.realgotqkura.runeCosmetics.RuneCosmetics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RandomUtils {

    public static HashMap<String, NamespacedKey> nskContainer = new HashMap<>();


    public static void loadNskContainer(RuneCosmetics plugin){
        nskContainer.put("ItemRune", new NamespacedKey(plugin, "item_rune_runecosmetics"));
        nskContainer.put("ItemRuneGiver", new NamespacedKey(plugin, "item_rune_giver_runecosmetics"));
    }

    public static String color(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static boolean isRightInv(InventoryEvent event, String guiName){
        return (event.getView().getTitle().equals(guiName) && event.getInventory().getHolder() instanceof RuneUIHolder);
    }

    public static boolean hasRune(ItemStack item){
        if(item == null) return false;
        if(item.getItemMeta() == null) return false;
        ItemMeta meta = item.getItemMeta();

        if(meta.getPersistentDataContainer().has(RandomUtils.nskContainer.get("ItemRune"))) return true;

        return false;
    }

    public static boolean isRune(ItemStack item){
        if(item == null) return false;
        if(item.getItemMeta() == null) return false;
        ItemMeta meta = item.getItemMeta();

        if(meta.getPersistentDataContainer().has(RandomUtils.nskContainer.get("ItemRuneGiver"))) return true;

        return false;
    }

    public static Rune getItemRuneRune(ItemStack item){
        return Rune.valueOf(item.getItemMeta().getPersistentDataContainer().get(RandomUtils.nskContainer.get("ItemRuneGiver"), PersistentDataType.STRING).toUpperCase());
    }

    public static Rune getItemRune(ItemStack item){
        return Rune.valueOf(item.getItemMeta().getPersistentDataContainer().get(RandomUtils.nskContainer.get("ItemRune"), PersistentDataType.STRING).toUpperCase());
    }

    public static ItemStack updateItem(ItemStack item, Rune rune){

        boolean editItem = false;
        List<String> lore;
        if(item.getItemMeta().getLore() == null){
            lore = new ArrayList<>();
        }else{
            lore = item.getItemMeta().getLore();
        }

        if(lore.size() == 0) editItem = true;
        if(lore.size() != 0){
            if(!lore.get(lore.size()-1).equals(rune.getColor() + "" + ChatColor.BOLD + "" + rune.getName() + " Rune")); editItem = true;
        }
        if(!editItem) return null;

        ItemStack newItem = item.clone();
        ItemMeta meta = newItem.getItemMeta();

        if(lore.size() == 0) {
            lore.add("");
            lore.add(rune.getColor() + "" + ChatColor.BOLD + "" + rune.getName() + " Rune");
        }else{
            lore.set(lore.size()-1, rune.getColor() + "" + ChatColor.BOLD + "" + rune.getName() + " Rune");
        }
        meta.setLore(lore);
        newItem.setItemMeta(meta);
        return newItem;
    }

    private static String cleanString(String s) {
        return s
                .replace("\uFE0F", "") // remove variation selector
                .trim();
    }
}
