package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import org.bukkit.Bukkit;

public abstract class TimedAction extends Action {
    public TimedAction(String name, ChaosManager manager,long delay,long period) {
        super(name, manager);
        maxTime = getActionTime();
        this.period = period;
        this.delay = delay;
        time = 0;
    }
    private int time;
    private final int maxTime;

    private final transient long delay;
    private final transient long period;

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
