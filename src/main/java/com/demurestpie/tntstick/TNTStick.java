package com.demurestpie.tntstick;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public final class TNTStick extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }
    @EventHandler
    public void onInteractEvent(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        ItemStack item = new ItemStack(player.getInventory().getItemInMainHand());

        if (item.getItemMeta().getDisplayName().equals("bazooka") && item.getType() == Material.STICK)
        {
            if (event.getAction().isRightClick())
            {
                launchTNT(player);
            }
        }
    }

    private void launchTNT(Player player) {
        Vector direction = player.getEyeLocation().getDirection();
        org.bukkit.entity.TNTPrimed tnt = player.getWorld().spawn(player.getEyeLocation().add(direction), org.bukkit.entity.TNTPrimed.class);
        tnt.setFuseTicks(40);

        org.bukkit.entity.Sheep sheep = player.getWorld().spawn(player.getEyeLocation().add(direction), org.bukkit.entity.Sheep.class);
        sheep.setColor(DyeColor.PINK);
        sheep.setHealth(40.0);
        tnt.addPassenger(sheep);

        tnt.setYield(20.0f);
        tnt.setVelocity(direction.multiply(2));
    }
}
