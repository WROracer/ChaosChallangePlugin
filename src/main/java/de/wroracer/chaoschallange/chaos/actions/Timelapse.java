package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class Timelapse extends Action{
    public Timelapse(ChaosManager manager) {
        super("Timelapse", manager);
    }

    private int schedulerID = 0;

    @Override
    public void start() {
        schedulerID = Bukkit.getScheduler().scheduleSyncRepeatingTask(getManager().getPlugin(),()->{
            for(World world : Bukkit.getServer().getWorlds()){
                world.setTime(world.getTime()+100);
            }
        },2,2);
    }

    @Override
    public void stop() {
        Bukkit.getScheduler().cancelTask(schedulerID);
    }
}
