package com.demurestpie.tntstick;

import com.demurestpie.tntstick.commands.TntAmountCommand;
import com.demurestpie.tntstick.commands.TntSizeCommand;
import com.demurestpie.tntstick.commands.TntStickCommand;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.Objects;
import java.util.Random;

public final class TNTStick extends JavaPlugin implements Listener {

    public static int tntAmount = 10;
    public static float tntSize = 10.0f;
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(this.getCommand("tntstick")).setExecutor(new TntStickCommand());
        Objects.requireNonNull(this.getCommand("tntamount")).setExecutor(new TntAmountCommand());
        Objects.requireNonNull(this.getCommand("tntsize")).setExecutor(new TntSizeCommand());
    }
    @EventHandler
    public void onInteractEvent(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        ItemStack item = new ItemStack(player.getInventory().getItemInMainHand());

        if (item.getItemMeta().displayName() != null) {

            String itemName = PlainTextComponentSerializer.plainText().serialize(Objects.requireNonNull(item.getItemMeta().displayName()));

            if (itemName.equals("TNT Stick")) {

                if (event.getAction().isLeftClick()) {
                    launchPrimedTNT(player);
                }

                if (event.getAction().isRightClick()) {
                    launchFallingTnt(player);
                }
            }
        }
    }

    /**
     * Launches primed tnt in a spread in player's looking direction
     * @param player Player
     */
    private void launchPrimedTNT(Player player) {

        for (short i = 0; i < tntAmount; i++) {
            // Set min and max variability
            double min = -0.25;
            double max = 0.25;

            // Get player's looking direction
            double x = player.getEyeLocation().getDirection().getX();
            double y = player.getEyeLocation().getDirection().getY();
            double z = player.getEyeLocation().getDirection().getZ();

            // Create randomized vectors for tnt spread
            Vector direction = vectorRandomizer(min, max, x, y, z);

            // Spawn tnt
            TNTPrimed tnt = player.getWorld().spawn(player.getEyeLocation().add(direction), org.bukkit.entity.TNTPrimed.class);
            tnt.setYield(tntSize);
            tnt.setVelocity(direction.multiply(3));
        }
    }

    /**
     * Launches falling tnt blocks in a spread in player's looking direction
     * @param player Player
     */
    private void launchFallingTnt(Player player) {

        for (short i = 0; i < tntAmount; i++) {
            // Set min and max variability
            double min = -0.25;
            double max = 0.25;

            // Get player's looking direction
            double x = player.getEyeLocation().getDirection().getX();
            double y = player.getEyeLocation().getDirection().getY();
            double z = player.getEyeLocation().getDirection().getZ();

            // Create randomized vectors for tnt spread
            Vector direction = vectorRandomizer(min, max, x, y, z);

            // Spawn tnt
            FallingBlock block = player.getEyeLocation().getWorld().spawnFallingBlock(player.getEyeLocation().add(direction), Material.TNT.createBlockData());
            block.setVelocity(direction.multiply(3));
        }
    }

    /**
     * Adds or subtracts from a vector determined by a random number generator using max and min
     * @param min minimum inclusive double for rng
     * @param max maximum inclusive double for rng
     * @param x vector x
     * @param y vector y
     * @param z vector z
     * @return Vector
     */
    public Vector vectorRandomizer(double min, double max, double x, double y, double z) {
        Random random = new Random();
        max *= 2; // Multiply max by 2 to make it inclusive
        double randomX = (min + (max * random.nextDouble()) + x);
        double randomY = (min + (max * random.nextDouble()) + y);
        double randomZ = (min + (max * random.nextDouble()) + z);

        return new Vector(randomX, randomY, randomZ);
    }
}
