package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;

public abstract class Action {

    private String name;
    public Action(String name, ChaosManager manager){
        this.name = name;
        manager.addAction(this);
    }

    public String getName(){return name;}

    public abstract void start();
    public abstract void stop();

    public static void registerActions(ChaosManager manager){
        new TestAction1("TEST1",manager);
        new TestAction2("TEST2",manager);
        new TestAction3("TEST3",manager);
        new TestAction4("TEST4",manager);
    }



}
