package com.realgotqkura.runeCosmetics.gui;

import com.realgotqkura.runeCosmetics.utils.RandomUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GuiItems {

    public static ItemStack guiDarkGlass(){
        ItemStack stack = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(RandomUtils.color("&0."));
        stack.setItemMeta(meta);
        return stack;
    }

    public static ItemStack guiClose(){
        ItemStack stack = new ItemStack(Material.IRON_DOOR);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(RandomUtils.color("&cClose GUI"));
        stack.setItemMeta(meta);
        return stack;
    }
}
