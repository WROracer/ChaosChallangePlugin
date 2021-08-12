package de.wroracer.chaoschallange.chaos.actions.common;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.Action;
import de.wroracer.chaoschallange.chaos.actions.TimedAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Objects;
import java.util.Random;

public class ThunderStorm extends TimedAction {
    public ThunderStorm(String name, ChaosManager manager) {
        super(name, manager,20, 20);
    }

    private transient Random random = new Random();
    private transient int raduis = 50;

    @Override
    public void start() {
        super.start();
        Objects.requireNonNull(Bukkit.getServer().getWorld("world")).setThundering(true);
    }

    @Override
    public void trigger() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            int x = player.getLocation().getBlockX() - (raduis/2) + random.nextInt(raduis);
            int y = player.getLocation().getBlockY() - (raduis/2) + random.nextInt(raduis);
            int z = player.getLocation().getBlockZ() - (raduis/2) + random.nextInt(raduis);
            player.getWorld().strikeLightning(new Location(player.getWorld(),x,y,z));
        });
    }
}
