package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public abstract class ActionListener extends Action implements Listener {
    public ActionListener(String name, ChaosManager manager) {
        super(name, manager);
        Bukkit.getPluginManager().registerEvents(this,manager.getPlugin());
        maxTime = getActionTime();
        time = 0;
    }

    public transient boolean isActive = false;
    private transient int schedulerID;
    private int time;
    private final int maxTime;

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
