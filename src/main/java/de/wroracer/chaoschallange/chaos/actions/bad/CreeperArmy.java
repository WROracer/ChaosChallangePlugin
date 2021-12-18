package de.wroracer.chaoschallange.chaos.actions.bad;

import de.wroracer.chaoschallange.chaos.actions.util.ActionInfo;
import de.wroracer.chaoschallange.chaos.actions.util.TimedAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;

import javax.xml.ws.Action;
import java.util.List;

@ActionInfo(name = "Creeper",delay = 20*5,period = 20*5)
public class CreeperArmy extends TimedAction {

    @Override
    public boolean setup() {
        return super.setup();
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
