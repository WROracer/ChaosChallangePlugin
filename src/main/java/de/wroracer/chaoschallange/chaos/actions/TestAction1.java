package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import org.bukkit.Bukkit;

public class TestAction1 extends Action{
    public TestAction1(String name, ChaosManager manager) {
        super(name, manager);
    }

    @Override
    public void start() {

        Bukkit.broadcastMessage("TEST 1");
    }

    @Override
    public void stop() {

    }
}
