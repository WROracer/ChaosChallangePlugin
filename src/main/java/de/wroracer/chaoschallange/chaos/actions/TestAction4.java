package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import org.bukkit.Bukkit;

public class TestAction4 extends Action{
    public TestAction4(String name, ChaosManager manager) {
        super(name, manager);
    }

    @Override
    public void start() {
        Bukkit.broadcastMessage("TEST 4");
    }

    @Override
    public void stop() {

    }
}
