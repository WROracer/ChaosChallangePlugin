package de.wroracer.chaoschallange.chaos.actions.bad;

import de.wroracer.chaoschallange.chaos.actions.util.Action;
import de.wroracer.chaoschallange.chaos.actions.util.ActionInfo;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.Random;

@ActionInfo(name = "Zombie Attack")
public class ZombieAttack extends Action {

    @Override
    public boolean setup() {
        return true;
    }

    @Override
    public void start() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.getWorld().setTime(18000);
            for (int i = 20;i!=0;i--){
                Random random = new Random();
                int rnd;
                do {
                    rnd = random.nextInt(15);
                }while (rnd <= 4);
                Location location = getLocationInCircle(player.getLocation(),rnd);
                location.setY(255);
                while (location.getBlock().getType()== Material.AIR){
                    location.setY(location.getY()-1);
                }
                player.getWorld().spawnEntity(location, EntityType.ZOMBIE);
            }
        });
    }
    public static Location getLocationInCircle(Location origin, Integer radius) {
        Random random = new Random();
        double angle = random.nextInt() * 360;
        return origin.add(Math.cos(angle) * radius, 0, Math.sin(angle) * radius);
    }

    @Override
    public void stop() {

    }
}
