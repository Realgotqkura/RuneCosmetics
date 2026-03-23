package com.realgotqkura.runeCosmetics;

import com.realgotqkura.runeCosmetics.utils.RandomUtils;
import com.realgotqkura.runeCosmetics.utils.Rune;
import com.realgotqkura.runeCosmetics.utils.SkullGiver;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class RuneItems {

    public static ItemStack giveRune(Rune rune, boolean gui){
        ItemStack stack = SkullGiver.getSkullFromBase64(rune.getBase64());
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(rune.getColor() + rune.getSymbol() + " " + rune.getName() + " Rune " + rune.getSymbol());
        List<String> lore = new ArrayList<>();
        lore.add(RandomUtils.color("&8Applicable to all"));
        lore.add(RandomUtils.color("&8equipment"));
        if(gui){
            lore.add("");
            lore.add(RandomUtils.color("&bLeft Click &eto get it"));
        }else{
            lore.add("");
            lore.add(RandomUtils.color("&8Click on top of an"));
            lore.add(RandomUtils.color("&8inventory item to apply!"));
            lore.add(ChatColor.of("#B132E7") + "" + ChatColor.BOLD + "RUNE");
        }

        meta.getPersistentDataContainer().set(RandomUtils.nskContainer.get("ItemRuneGiver"), PersistentDataType.STRING, rune.toString());
        meta.setLore(lore);
        stack.setItemMeta(meta);
        return stack;
    }
}
