package de.wroracer.chaoschallange.chaos.actions.good;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.Action;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class MakeDay extends Action {
    public MakeDay(String name, ChaosManager manager) {
        super(name, manager);
    }

    @Override
    public void start() {
        for(World world : Bukkit.getServer().getWorlds()){
            world.setTime(0);
        }

    }

    @Override
    public void stop() {

    }
}
