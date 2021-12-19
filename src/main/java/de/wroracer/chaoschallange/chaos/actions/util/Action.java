package de.wroracer.chaoschallange.chaos.actions.util;

import de.wroracer.chaoschallange.chaos.ActionManager;
import de.wroracer.chaoschallange.util.LoggerHelper;

import java.util.Objects;

public abstract class Action implements LoggerHelper {

    private String name;
    private transient ActionManager manager;

    private transient final ActionInfo info;

    public Action() {
        this.info = getClass().getDeclaredAnnotation(ActionInfo.class);
        if (info == null) {
            log().warning("Anotation in Classe: " + getClass().getName() + " nicht vorhanden.");
            name = "NaN";
        } else {
            log().info("Registering Action: " + getClass().getSimpleName());
            name = info.name();
        }
    }

    public ActionInfo getInfo() {
        return info;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManager(ActionManager manage) {
        this.manager = manage;
    }

    public abstract boolean setup();

    public String getName() {
        return name;
    }

    public ActionManager getManager() {
        return manager;
    }

    public int getActionTime() {
        return manager.getVoteTime();
    }

    public abstract void start();

    public abstract void stop();

   /* public static void registerActions(ChaosManager manager) {

        *//*
         * new AnimalExplode("Animal Explodes",manager);
         * new ZombieAttack("Zombie Attack",manager);
         * new WaterBucketMLG("Water MLG",manager);
         * new PumpkinView("Pumpkin View",manager);
         * new Timelapse(manager);
         * new CreeperArmy(manager);
         * new ReplaceAllWater("Replace water with lava", manager);
         * new DropAllItems("Drop all items", manager);
         * new LagSimulator("Lag",manager);
         * new ThunderStorm("ThunderStorm",manager);
         * new TpToCave("Teleport to next cave", manager);
         * 
         * new DealNoDamage("No Damage",manager);
         * new TpToRandomEntity("Teleport to random entity", manager);
         * new DelayDamage("Delay damage", manager);
         * 
         * new PlayRandomSound(manager);
         * 
         * new GamemodeSwitch(manager);
         * 
         * 
         * 
         * 
         * // Good Stuff
         * new XpBottles("Give Xp", manager);
         * new RandomEnchantment("Random Enchantment",manager);
         * new RandomPotionEffect("Random Effect",manager);
         * new MakeDay("Make day", manager);
         * new TpToSurface("Tp to surface", manager);
         * new StopRain("Stop rain", manager);
         * new GiveRandomStuff("Give random equipment", manager);
         * new GiveRandomItems("Give random items", manager);
         * 
         * new RandomizeItems(manager);
         *//*

        // Temp Disabled (Bugy)
        // new FakeDeath("Death",manager);

        // new Test("TEST",manager);
        // new TestListender("TEST",manager);
    }*/

}
