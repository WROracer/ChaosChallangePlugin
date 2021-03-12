package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import org.bukkit.Bukkit;

public class TestAction3 extends Action{
    public TestAction3(String name, ChaosManager manager) {
        super(name, manager);
    }

    @Override
    public void start() {
        Bukkit.broadcastMessage("TEST 3");
    }

    @Override
    public void stop() {

    }
}
