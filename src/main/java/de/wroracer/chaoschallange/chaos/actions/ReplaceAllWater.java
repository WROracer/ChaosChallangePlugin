//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReplaceAllWater extends Action {
    private int schedulerID = 0;
    private List<Block> toReplace = new ArrayList();
    int radius = 10;

    public ReplaceAllWater(String name, ChaosManager manager) {
        super(name, manager);
    }

    public void start() {
        this.schedulerID = Bukkit.getScheduler().scheduleSyncRepeatingTask(this.getManager().getPlugin(), () -> {
            Iterator var1 = Bukkit.getServer().getOnlinePlayers().iterator();

            while (var1.hasNext()) {
                Player p = (Player) var1.next();
                Location position = p.getLocation();
                int startPosX = position.getBlockX() - this.radius / 2;
                int startPosY = position.getBlockY() - this.radius / 2;
                int startPosZ = position.getBlockZ() - this.radius / 2;

                for (int x = startPosX; x < this.radius + startPosX; ++x) {
                    for (int y = startPosY; y < this.radius + startPosY; ++y) {
                        for (int z = startPosZ; z < this.radius + startPosZ; ++z) {
                            Block currentBlock = p.getWorld().getBlockAt(x, y, z);
                            if (currentBlock.getType() == Material.WATER) {
                                this.toReplace.add(currentBlock);
                                currentBlock.setType(Material.AIR);
                            }
                        }
                    }
                }

                Iterator var11 = this.toReplace.iterator();

                while (var11.hasNext()) {
                    Block block = (Block) var11.next();
                    if (block.getType() == Material.AIR || block.getType() == Material.OBSIDIAN) {
                        block.setType(Material.LAVA);
                    }
                }

            }

        }, 0L, 20L);
    }

    public void stop() {
        this.toReplace.clear();
        Bukkit.getScheduler().cancelTask(this.schedulerID);
    }
}
