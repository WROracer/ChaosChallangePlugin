package de.wroracer.chaoschallange.chaos.actions.common;

import de.wroracer.chaoschallange.chaos.actions.util.ActionInfo;
import de.wroracer.chaoschallange.chaos.actions.util.TimedAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Objects;
import java.util.Random;

@ActionInfo(name = "Thunder Storm",delay = 20,period = 20)
public class ThunderStorm extends TimedAction {

    @Override
    public boolean setup() {
        return super.setup();
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
