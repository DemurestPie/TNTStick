package com.demurestpie.tntstick.commands;

import com.demurestpie.tntstick.TNTStick;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class TntAmountCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof org.bukkit.entity.Player)) {
            sender.sendMessage("Only players can use this command!");
            return false;
        }

        if (!sender.hasPermission("tntstick.tntamount")) {
            sender.sendMessage("You do not have permission to use this command!");
            return false;
        }

        try {
            TNTStick.tntAmount = Integer.parseInt(args[0]);
            sender.sendMessage("TNT amount set to " + TNTStick.tntAmount);
        }
        catch (NumberFormatException e) {
            sender.sendMessage("Invalid argument!");
        }
        return true;
    }
}
