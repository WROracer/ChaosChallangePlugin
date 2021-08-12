package de.wroracer.chaoschallange.chaos.actions.common;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.Action;
import de.wroracer.chaoschallange.chaos.actions.TimedAction;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class Timelapse extends TimedAction {
    public Timelapse(ChaosManager manager) {
        super("Timelapse", manager,2,2);
    }

    @Override
    public void trigger() {
        for(World world : Bukkit.getServer().getWorlds()){
            world.setTime(world.getTime()+100);
        }
    }
}
