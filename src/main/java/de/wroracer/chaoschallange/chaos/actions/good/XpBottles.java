package de.wroracer.chaoschallange.chaos.actions.good;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.Action;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class XpBottles extends Action {
    public XpBottles(String name, ChaosManager manager) {
        super(name, manager);
    }


    @Override
    public void start() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            Location pos = p.getLocation();
            World world = pos.getWorld();
            for (int i = 0; i< 50; i++){
            world.spawnEntity(pos, EntityType.THROWN_EXP_BOTTLE);
            }

        }
    }

    @Override
    public void stop() {

    }
}
