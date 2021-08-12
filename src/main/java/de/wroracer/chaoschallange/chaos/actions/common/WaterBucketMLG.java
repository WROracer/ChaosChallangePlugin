package de.wroracer.chaoschallange.chaos.actions.common;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.ActionListener;
import de.wroracer.chaoschallange.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class WaterBucketMLG extends ActionListener {
    public WaterBucketMLG(String name, ChaosManager manager) {
        super(name, manager);
    }

    private transient HashMap<Player, Location> positions = new HashMap<>();

    private transient boolean isActivated = false;

    private transient final int dropHeight = 100;
    private transient final int maxDropHeight = 255 + dropHeight;

    @Override
    public void start() {
        super.start();
        isActivated = true;
        Bukkit.getOnlinePlayers().forEach(player -> {
            ItemStack iteminMainHand = player.getInventory().getItemInMainHand();
            if (player.getWorld().getName().equals("world_nether")) {
                player.getInventory().setItemInMainHand(new ItemBuilder(Material.SLIME_BLOCK).setName("MLG-Block").buid());
            } else {
                player.getInventory().setItemInMainHand(new ItemBuilder(Material.WATER_BUCKET).setName("MLG-Bucket").buid());
            }
            player.getInventory().addItem(iteminMainHand);
            Location playerloc = player.getLocation();
            positions.put(player, playerloc.clone());


            int x = playerloc.getBlockX();
            int z = playerloc.getBlockZ();
            playerloc.setX(x);
            playerloc.setZ(z);


            boolean found = false;
            boolean failed = false;


            for (int y = 0; !found; y++) {
                if (failed) {
                    failed = false;
                    y = 0;
                    x += 1;
                    z += 1;
                    playerloc.setX(x);
                    playerloc.setZ(z);
                }
                Block block = getBlockAt(playerloc, y+1);
                //System.out.println(block.getType());
                if (block.getType() == Material.AIR) {
                    //System.out.println(getBlockAt(playerloc, playerloc.getBlockY()-1).getType());

                    found = getBlockAt(playerloc, y-1).getType() != Material.WATER;
                    //System.out.println(found);

                    if (found) {

                        for (int h = 0; h < dropHeight; h++) {
                            if (getBlockAt(playerloc, y + h).getType() != Material.AIR) {
                                found = false;
                            }
                        }
                    }
                    else failed = true;
                }
                if (y > maxDropHeight) failed = true;

                playerloc.setY(y + dropHeight);
            }
            playerloc.setX(playerloc.getBlockX() + 0.5);
            playerloc.setZ(playerloc.getBlockZ() + 0.5);
            player.teleport(playerloc);


        });
    }

    @Override
    public int getActionTime() {
        return 8;
    }

    @Override
    public void stop() {
        super.stop();
        isActivated = false;
        positions.forEach(Entity::teleport);
        positions.clear();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (isActivated) {
            positions.remove(event.getEntity());
        }
    }

    private Block getBlockAt(Location location, int y) {
        Location location2 = location.clone();
        location2.setY(y);
        return location2.getWorld().getBlockAt(location2);
    }
}
