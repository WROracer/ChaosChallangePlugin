package de.wroracer.chaoschallange.chaos.actions.util;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public abstract class ActionListener extends Action implements Listener {

    @Override
    public boolean setup() {
        Bukkit.getPluginManager().registerEvents(this,getManager().getPlugin());
        maxTime = getActionTime();
        time = 0;
        return true;
    }

    public transient boolean isActive = false;
    private transient int schedulerID;
    private int time;
    private int maxTime;

    @Override
    public void start() {
        time = 0;
        schedulerID = Bukkit.getScheduler().scheduleSyncRepeatingTask(getManager().getPlugin(), ()->{
            time++;
        },20,20);
        isActive = true;
    }

    @Override
    public void stop() {
        Bukkit.getScheduler().cancelTask(schedulerID);
        isActive = false;
    }

    public int getTime() {
        return time;
    }

    public int getMaxTime() {
        return maxTime;
    }
}
