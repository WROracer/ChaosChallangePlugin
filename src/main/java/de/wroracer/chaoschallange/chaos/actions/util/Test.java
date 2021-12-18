package de.wroracer.chaoschallange.chaos.actions.util;

import org.bukkit.event.Listener;

@ActionInfo(name = "Test")
public class Test extends Action implements Listener {

    @Override
    public boolean setup() {
        return false;
    }

    @Override
    public void start() {
    System.out.println("test");
    }

    @Override
    public void stop() {

    }
}
