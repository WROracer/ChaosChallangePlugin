package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;

public class Test extends Action{
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
