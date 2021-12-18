package de.wroracer.chaoschallange.chaos.actions.bad;

import de.wroracer.chaoschallange.chaos.actions.util.Action;
import de.wroracer.chaoschallange.chaos.actions.util.ActionInfo;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.checkerframework.checker.units.qual.A;

import java.util.List;

@ActionInfo(name = "Animal Explode")
public class AnimalExplode extends Action {

    @Override
    public boolean setup() {
        return true;
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
