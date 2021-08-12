//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package de.wroracer.chaoschallange.chaos.actions.bad;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.Action;
import de.wroracer.chaoschallange.chaos.actions.TimedAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReplaceAllWater extends TimedAction {
    private final transient List<Block> toReplace = new ArrayList();

    public ReplaceAllWater(String name, ChaosManager manager) {
        super(name, manager,0L, 20L);
    }

    @Override
    public void stop() {
        super.stop();
        this.toReplace.clear();
    }

    @Override
    public void trigger() {
        Iterator var1 = Bukkit.getServer().getOnlinePlayers().iterator();

        while (var1.hasNext()) {
            Player p = (Player) var1.next();
            Location position = p.getLocation();
            int radius = 10;
            int startPosX = position.getBlockX() - radius / 2;
            int startPosY = position.getBlockY() - radius / 2;
            int startPosZ = position.getBlockZ() - radius / 2;

            for (int x = startPosX; x < radius + startPosX; ++x) {
                for (int y = startPosY; y < radius + startPosY; ++y) {
                    for (int z = startPosZ; z < radius + startPosZ; ++z) {
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

    }
}
