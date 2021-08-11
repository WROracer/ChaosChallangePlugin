package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;

import java.util.List;

public class CreeperArmy extends Action{
    public CreeperArmy(ChaosManager manager) {
        super("Replace mobs with creepers", manager);
    }

    private int schedulerID = 0;

    @Override
    public void start() {
        schedulerID = Bukkit.getScheduler().scheduleSyncRepeatingTask(getManager().getPlugin(),()->{
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
        },20*5,20*5);
    }

    @Override
    public void stop() {
        Bukkit.getScheduler().cancelTask(schedulerID);
    }
}
