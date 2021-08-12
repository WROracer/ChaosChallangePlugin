package de.wroracer.chaoschallange.chaos.actions.bad;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.Action;
import de.wroracer.chaoschallange.chaos.actions.TimedAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;

import java.util.List;

public class CreeperArmy extends TimedAction {
    public CreeperArmy(ChaosManager manager) {
        super("Replace mobs with creepers", manager,20*5,20*5);
    }

    @Override
    public void trigger() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            List<Entity> nearestEntitys = player.getNearbyEntities(10,10,10);
            nearestEntitys.forEach(entity -> {
                if (entity instanceof Mob){
                    Mob mob = (Mob) entity;
                    if (mob instanceof Creeper)return;
                    Location modLoc = mob.getLocation();
                    player.getWorld().spawnEntity(entity.getLocation(), EntityType.CREEPER);
                    modLoc.setY(-10);
                    mob.teleport(modLoc);
                }
            });
        });
    }
}
