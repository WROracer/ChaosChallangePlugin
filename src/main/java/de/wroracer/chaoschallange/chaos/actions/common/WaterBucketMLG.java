package de.wroracer.chaoschallange.chaos.actions.common;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.Action;
import de.wroracer.chaoschallange.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class WaterBucketMLG extends Action {
    public WaterBucketMLG(String name, ChaosManager manager) {
        super(name, manager);
    }

    @Override
    public void start() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            ItemStack iteminMainHand = player.getInventory().getItemInMainHand();
            if (player.getWorld().getName().equals("world_the_nether")){
                player.getInventory().setItemInMainHand(new ItemBuilder(Material.SLIME_BLOCK).setName("MLG-Block").buid());
            }else {
                player.getInventory().setItemInMainHand(new ItemBuilder(Material.WATER_BUCKET).setName("MLG-Bucket").buid());
            }
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
