package de.wroracer.chaoschallange.chaos.actions.good;

import java.util.List;

import de.wroracer.chaoschallange.chaos.actions.util.Action;
import de.wroracer.chaoschallange.chaos.actions.util.ActionInfo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;


@ActionInfo(name = "Kill near Hostile Mobs")
public class KillNearHostileMobs extends Action {


    @Override
    public boolean setup() {
        return true;
    }

    @Override
    public void start() {
        int perPlayer = 0;
        for (Player p : Bukkit.getOnlinePlayers()) {
            perPlayer = 0;
            List<Entity> near = p.getNearbyEntities(20.0D, 20.0D, 20.0D);
            for (Entity entity : near) {
                if (entity.getType() != EntityType.DROPPED_ITEM && entity.getType() != EntityType.PLAYER) {
                    if (entity instanceof Monster || entity.getType() == EntityType.GHAST
                            || entity.getType() == EntityType.SLIME) {
                        // entity is hostile
                        ((LivingEntity) entity).setHealth(0);
                        perPlayer++;
                    }
                }
            }
            p.sendMessage(perPlayer + " hostile mobs killed around you");
        }

    }

    @Override
    public void stop() {
    }

}
