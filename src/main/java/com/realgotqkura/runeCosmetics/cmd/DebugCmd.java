package com.realgotqkura.runeCosmetics.cmd;

import com.realgotqkura.runeCosmetics.utils.RandomUtils;
import com.realgotqkura.runeCosmetics.utils.Rune;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class DebugCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("runes_debug")){
            if(args[0].equalsIgnoreCase("giveRune")){
                Rune rune = Rune.valueOf(args[1].toUpperCase());
                Player player = (Player) sender;


                ItemStack item = player.getInventory().getItemInMainHand();
                ItemMeta meta = item.getItemMeta();
                meta.getPersistentDataContainer().set(RandomUtils.nskContainer.get("ItemRune"), PersistentDataType.STRING, rune.getName());
                item.setItemMeta(meta);
                player.getInventory().setItemInMainHand(item);
                return true;
            }
            return true;
        }
        return false;
    }
}
