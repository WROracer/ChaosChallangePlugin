package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;

import java.util.List;


public class AnimalExplode extends Action{
    public AnimalExplode(String name, ChaosManager manager) {
        super(name, manager);
    }

    @Override
    public void start() {

        for(Player p : Bukkit.getServer().getOnlinePlayers()) {
            List<Entity> near = p.getNearbyEntities(10.0D, 10.0D, 10.0D);
            for(Entity entity : near) {
                if (entity.getType() != EntityType.DROPPED_ITEM && entity.getType() != EntityType.PLAYER){
                    Location pos = entity.getLocation();
                    pos.getWorld().createExplosion(pos, 5);
                    //pos.getWorld().spawnEntity(pos, EntityType.PRIMED_TNT);
                }
            }
        }

    }

    @Override
    public void stop() {

    }
}
