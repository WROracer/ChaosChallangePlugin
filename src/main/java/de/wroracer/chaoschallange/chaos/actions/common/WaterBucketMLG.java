package de.wroracer.chaoschallange.chaos.actions.common;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.Action;
import de.wroracer.chaoschallange.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class WaterBucketMLG extends Action {
    public WaterBucketMLG(String name, ChaosManager manager) {
        super(name, manager);
    }

    private transient HashMap<Player, Location> positions = new HashMap<>();

    private int schedulerID = 0;
    private final int dropHeight = 100;

    @Override
    public void start() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            ItemStack iteminMainHand = player.getInventory().getItemInMainHand();
            if (player.getWorld().getName().equals("world_the_nether")) {
                player.getInventory().setItemInMainHand(new ItemBuilder(Material.SLIME_BLOCK).setName("MLG-Block").buid());
            } else {
                player.getInventory().setItemInMainHand(new ItemBuilder(Material.WATER_BUCKET).setName("MLG-Bucket").buid());
            }
            player.getInventory().addItem(iteminMainHand);
            Location playerloc = player.getLocation();
            positions.put(player, playerloc.clone());
            boolean found = false;


            for (int y = 0; !found; y++){
                Block block = getBlockAt(playerloc, y + 1);
                if (block.getType() == Material.AIR) {
                    found = true;
                    for (int h = 0; h< dropHeight; h++){
                        if (getBlockAt(playerloc, y + h).getType() != Material.AIR) {
                            found = false;
                        }
                    }
                }

            playerloc.setY(y+dropHeight);
            }
            player.teleport(playerloc);


        });
            schedulerID = Bukkit.getScheduler().scheduleSyncRepeatingTask(getManager().getPlugin(), () -> {
                positions.forEach((player, location) -> {
                    player.teleport(location);
                });
                Bukkit.getScheduler().cancelTask(schedulerID);
            }, 20* 8, 20*5);
    }

    @Override
    public void stop() {


    }
    private Block getBlockAt(Location location, int y) {
        Location location2 = location.clone();
        location2.setY(y);
        return location2.getWorld().getBlockAt(location2);
    }
}
