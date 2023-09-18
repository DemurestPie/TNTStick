package com.demurestpie.tntstick.commands;

import com.demurestpie.tntstick.TNTStick;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class TntSizeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // Check if sender is a player
        if (!(sender instanceof org.bukkit.entity.Player)) {
            sender.sendMessage("Only players can use this command!");
            return false;
        }

        // Check if user has permission
        if (!sender.hasPermission("tntstick.tntsize")) {
            sender.sendMessage("You do not have permission to use this command!");
            return false;
        }

        // Sets tnt size to argument
        try {
            TNTStick.tntSize = Float.parseFloat(args[0]);
            sender.sendMessage("TNT size set to " + TNTStick.tntSize);
        }
        catch (NumberFormatException e) {
            sender.sendMessage("Invalid argument!");
        }
        return true;
    }
}
