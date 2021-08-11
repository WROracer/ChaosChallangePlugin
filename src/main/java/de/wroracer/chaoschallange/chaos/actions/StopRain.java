package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class StopRain extends Action{
    public StopRain(String name, ChaosManager manager) {
        super(name, manager);
    }

    @Override
    public void start() {
        for(World world : Bukkit.getServer().getWorlds()){
            world.setStorm(false);
            world.setThundering(false);
        }

    }

    @Override
    public void stop() {

    }
}
