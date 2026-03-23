package com.realgotqkura.runeCosmetics.events;

import com.realgotqkura.runeCosmetics.utils.RandomUtils;
import com.realgotqkura.runeCosmetics.utils.Rune;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RuneGUIEvents implements Listener {


    private static List<String> allowedStrings = new ArrayList<>(
            Arrays.asList("AXE", "HOE", "SHOVEL", "SWORD", "BOW", "CROSSBOW") //Pickaxe overlaps axe
    );

    @EventHandler
    public void click(InventoryClickEvent event){
        if(!event.getClickedInventory().equals(event.getWhoClicked().getInventory())) return;

        if(event.getCursor() == null) return;
        if(event.getCurrentItem() == null) return;

        ItemStack cursorItem = event.getCursor();
        ItemStack currentItem = event.getCurrentItem();

        boolean allowed = false;

        for(String s : allowedStrings){
            if(currentItem.getType().toString().contains(s)) {
                allowed = true;
                break;
            }
        }

        if(!allowed) return;

        if(!RandomUtils.isRune(cursorItem)) return;

        event.setCancelled(true);

        Rune rune = RandomUtils.getItemRuneRune(cursorItem);

        ItemMeta meta = currentItem.getItemMeta();
        meta.getPersistentDataContainer().set(RandomUtils.nskContainer.get("ItemRune"), PersistentDataType.STRING, rune.getName());
        currentItem.setItemMeta(meta);
        event.getClickedInventory().setItem(event.getSlot(), currentItem);

        int amount = cursorItem.getAmount();

        if(amount > 1){
            ItemStack newCursor = cursorItem.clone();
            newCursor.setAmount(cursorItem.getAmount()-1);
            event.getWhoClicked().getInventory().addItem(newCursor);
        }else{
            event.getWhoClicked().setItemOnCursor(new ItemStack(Material.AIR));
        }
        ItemStack newItem = RandomUtils.updateItem(currentItem, rune);

        if(newItem == null) return;

        event.getClickedInventory().setItem(event.getSlot(), newItem);
        ((Player) event.getWhoClicked()).sendTitle(RandomUtils.color("&6&LRune Applied!"), "", 10, 20, 10);
        event.getWhoClicked().closeInventory();
    }
}
