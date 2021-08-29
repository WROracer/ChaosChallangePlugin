package de.wroracer.chaoschallange.chaos.actions.common;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.Action;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class TpToCave extends Action {
    public TpToCave(String name, ChaosManager manager) {
        super(name, manager);
    }

    @Override
    public void start() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            Location pos = p.getLocation();

            int x = pos.getBlockX();
            int z = pos.getBlockZ();


            boolean found = false;
            boolean failed = false;

            while (!found) {
                found = false;
                failed = false;

                pos.setX(x);
                pos.setZ(z);
                pos.setY(0);


                for (int y = 0; !found && !failed; y++) {
                    Block block = getBlockAt(pos, y);
                    if (block.getType() == Material.CAVE_AIR|| block.getType() == Material.AIR) {
                        block = getBlockAt(pos, y + 1);
                        if (block.getType() == Material.CAVE_AIR || block.getType() == Material.AIR) {

                            found = true;

                            block = getBlockAt(pos, y - 1);
                            if (block.getType() == Material.LAVA) {
                                block.setType(Material.GLASS);
                            }
                            pos.setY(y);
                            pos.setX(pos.getBlockX() +0.5);
                            pos.setZ(pos.getBlockZ()+0.5);

                            p.teleport(pos);
                        }
                    }
                    if (y == 255) failed = true;
                }
                z++;
                x++;

            }
        }
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

