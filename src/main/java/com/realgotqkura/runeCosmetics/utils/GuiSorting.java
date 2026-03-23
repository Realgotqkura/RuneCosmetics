package com.realgotqkura.runeCosmetics.utils;

import com.realgotqkura.runeCosmetics.gui.GuiItems;
import org.bukkit.inventory.Inventory;

public class GuiSorting {

    public static void fillLowestRow(Inventory inv){
        int size = inv.getSize();
        int startingIndex = size - 9;
        for(int i = startingIndex; i < size; i++){
            inv.setItem(i, GuiItems.guiDarkGlass());
        }
    }
}
