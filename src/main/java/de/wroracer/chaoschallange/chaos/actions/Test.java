package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import org.bukkit.event.Listener;

public class Test extends Action implements Listener {
    public Test(String name, ChaosManager manager) {
        super(name, manager);
    }

    @Override
    public void start() {
    System.out.println("test");
    }

    @Override
    public void stop() {

    }
}
