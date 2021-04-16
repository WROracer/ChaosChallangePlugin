package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class WaterBucketMLG extends Action{
    public WaterBucketMLG(String name, ChaosManager manager) {
        super(name, manager);
    }

    @Override
    public void start() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            ItemStack iteminMainHand = player.getInventory().getItemInMainHand();
            player.getInventory().setItemInMainHand(new ItemBuilder(Material.WATER_BUCKET).buid());
            player.getInventory().addItem(iteminMainHand);
            Location playerloc = player.getLocation();
            playerloc.setY(255);
            player.teleport(playerloc);
        });
    }

    @Override
    public void stop() {

    }
}
