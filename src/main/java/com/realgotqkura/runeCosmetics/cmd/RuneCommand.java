package com.realgotqkura.runeCosmetics.cmd;

import com.realgotqkura.runeCosmetics.gui.RunesGUI;
import com.realgotqkura.runeCosmetics.utils.RandomUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RuneCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("This Command is only for players!");
            return true;
        }

        Player player = (Player) sender;

        if(label.equalsIgnoreCase("runes")){
            RunesGUI gui = new RunesGUI(false);
            gui.createInv(player);
        }
        return false;
    }
}
