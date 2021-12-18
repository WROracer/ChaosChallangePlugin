package de.wroracer.chaoschallange.chaos.actions.good;

import de.wroracer.chaoschallange.chaos.actions.util.Action;
import de.wroracer.chaoschallange.chaos.actions.util.ActionInfo;
import org.bukkit.Bukkit;
import org.bukkit.World;

@ActionInfo(name = "Stop Rain")
public class StopRain extends Action {

    @Override
    public boolean setup() {
        return true;
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
