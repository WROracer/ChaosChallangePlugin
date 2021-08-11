package de.wroracer.chaoschallange.chaos.actions.common;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.Action;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Objects;
import java.util.Random;

public class ThunderStorm extends Action {
    public ThunderStorm(String name, ChaosManager manager) {
        super(name, manager);
    }

    private int schedulerID = 0;

    @Override
    public void start() {
        Objects.requireNonNull(Bukkit.getServer().getWorld("world")).setThundering(true);
        Random random = new Random();
        int raduis = 50;
        schedulerID = Bukkit.getScheduler().scheduleSyncRepeatingTask(getManager().getPlugin(),()->{
            Bukkit.getOnlinePlayers().forEach(player -> {
               int x = player.getLocation().getBlockX() - (raduis/2) + random.nextInt(raduis);
               int y = player.getLocation().getBlockY() - (raduis/2) + random.nextInt(raduis);
               int z = player.getLocation().getBlockZ() - (raduis/2) + random.nextInt(raduis);
               player.getWorld().strikeLightning(new Location(player.getWorld(),x,y,z));
            });
        },20, 20);
    }

    @Override
    public void stop() {
        Bukkit.getScheduler().cancelTask(schedulerID);
    }
}
