package com.realgotqkura.runeCosmetics.gui;

import com.realgotqkura.runeCosmetics.RuneCosmetics;
import com.realgotqkura.runeCosmetics.RuneItems;
import com.realgotqkura.runeCosmetics.utils.GuiSorting;
import com.realgotqkura.runeCosmetics.utils.RandomUtils;
import com.realgotqkura.runeCosmetics.utils.RuneUIHolder;
import com.realgotqkura.runeCosmetics.utils.Rune;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class RunesGUI implements Listener {

    RuneCosmetics plugin;

    public RunesGUI(RuneCosmetics plugin){
        this.plugin = plugin;
    }

    public RunesGUI(boolean usePlugin){
        if(usePlugin) this.plugin = JavaPlugin.getPlugin(RuneCosmetics.class);

    }

    public static final String GUI_NAME = ChatColor.of("#B132E7") + "Runes GUI";

    private Rune[] runesInOrder = new Rune[]{
            Rune.REDSTONE
    };

    public void createInv(Player player){
        Inventory inv = Bukkit.createInventory(new RuneUIHolder(), 54, GUI_NAME);
        GuiSorting.fillLowestRow(inv);
        inv.setItem(49, GuiItems.guiClose());
        for(int i = 0; i < runesInOrder.length; i++){
            if(i > 44) break;

            inv.setItem(0, RuneItems.giveRune(runesInOrder[i], true));
        }

        player.openInventory(inv);
    }


    @EventHandler
    public void click(InventoryClickEvent event){
        if(!RandomUtils.isRightInv(event, GUI_NAME)) return;


        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();

        if(event.getSlot() == 49) player.closeInventory();

        if(event.getSlot() < 44){
            if(event.getSlot() >= runesInOrder.length) return;

            player.getInventory().addItem(RuneItems.giveRune(runesInOrder[event.getSlot()], false));
            player.sendMessage(RandomUtils.color("&aGave Rune!"));
        }


    }


}
