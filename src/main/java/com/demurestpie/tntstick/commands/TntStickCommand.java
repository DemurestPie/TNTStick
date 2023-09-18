package com.demurestpie.tntstick.commands;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;

public class TntStickCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // Check if sender is a player
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this command!");
            return false;
        }

        // Check if user has permission
        if (!sender.hasPermission("tntstick.tntstick")) {
            sender.sendMessage("You do not have permission to use this command!");
            return false;
        }

        // Gives player a tnt stick
        ItemStack item = new ItemStack(Material.STICK);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(PlainTextComponentSerializer.plainText().deserialize("TNT Stick"));
        item.setItemMeta(meta);
        player.getInventory().addItem(item);
        sender.sendMessage("TNT Stick given!");
        return true;
    }
}
