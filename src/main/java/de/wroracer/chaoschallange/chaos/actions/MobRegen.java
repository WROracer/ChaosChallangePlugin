package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MobRegen extends ActionListener{
    public MobRegen( ChaosManager manager) {
        super("Mob Regen", manager);
    }

    private int schedulerID = 0;

    @Override
    public void start() {
        List<EntityType> entitys = new ArrayList<>();
        for (EntityType type : EntityType.values()) {
            if (type.isAlive() && type != EntityType.ENDER_DRAGON){
                entitys.add(type);
            }
        }
        entitys.remove(EntityType.PLAYER);
        Random random = new Random();
        int raduis = 50;
        schedulerID = Bukkit.getScheduler().scheduleSyncRepeatingTask(getManager().getPlugin(),()->{
          Bukkit.getOnlinePlayers().forEach(player -> {
              int x = player.getLocation().getBlockX() - (raduis/2) + random.nextInt(raduis);
              int y = player.getLocation().getBlockY() + 30;
              int z = player.getLocation().getBlockZ() - (raduis/2) + random.nextInt(raduis);
              Entity e = player.getWorld().spawnEntity(new Location(player.getWorld(),x,y,z),entitys.get(random.nextInt(entitys.size())));
              System.out.println(e +" | "+ e.getLocation());
          });
        },20, 20);
    }

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent event){
        if (schedulerID == 0) return;
        if (event.getEntity() instanceof LivingEntity){
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL){
                event.setCancelled(true);
            }
        }
    }

    @Override
    public void stop() {
        Bukkit.getScheduler().cancelTask(schedulerID);
        schedulerID = 0;
    }
}
