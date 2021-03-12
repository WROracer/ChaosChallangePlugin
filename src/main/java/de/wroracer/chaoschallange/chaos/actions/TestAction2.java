package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import org.bukkit.Bukkit;

public class TestAction2 extends Action{
    public TestAction2(String name, ChaosManager manager) {
        super(name, manager);
    }

    @Override
    public void start() {
        Bukkit.broadcastMessage("TEST 2");
    }

    @Override
    public void stop() {

    }
}
