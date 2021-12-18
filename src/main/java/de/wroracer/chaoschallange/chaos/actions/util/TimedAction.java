package de.wroracer.chaoschallange.chaos.actions.util;

import org.bukkit.Bukkit;

public abstract class TimedAction extends Action {

    @Override
    public boolean setup() {
        maxTime = getActionTime();

        this.period = getInfo().period();
        this.delay = getInfo().delay();
        time = 0;
        return true;

    }

    private int time;
    private int maxTime;

    private transient long delay;
    private transient long period;

    private transient int timeSchedulerID;
    private transient int actionSchedulerID;

    @Override
    public void start() {
        time = 0;
        timeSchedulerID = Bukkit.getScheduler().scheduleSyncRepeatingTask(getManager().getPlugin(), ()->{
            time++;
        },20,20);
        actionSchedulerID = Bukkit.getScheduler().scheduleSyncRepeatingTask(getManager().getPlugin(), this::trigger,delay, period);
    }

    @Override
    public void stop() {
        Bukkit.getScheduler().cancelTask(timeSchedulerID);
        Bukkit.getScheduler().cancelTask(actionSchedulerID);
    }

    public abstract void trigger();

    public int getMaxTime() {
        return maxTime;
    }

    public int getTime() {
        return time;
    }
}
