package de.wroracer.chaoschallange.chaos.actions.good;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.Action;

public class KillNearHostileMobs extends Action {

    public KillNearHostileMobs(String name, ChaosManager manager) {
        super(name, manager);
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
