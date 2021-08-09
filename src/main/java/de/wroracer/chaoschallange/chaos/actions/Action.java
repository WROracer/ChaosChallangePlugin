package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;

public abstract class Action {

    private String name;
    private ChaosManager manager;
    public Action(String name, ChaosManager manager){
        this.name = name;
        this.manager = manager;
        manager.addAction(this);
    }

    public String getName(){return name;}

    public ChaosManager getManager(){return manager;}

    public abstract void start();
    public abstract void stop();

    public static void registerActions(ChaosManager manager){

        new AnimalExplode("Animal Explodes",manager);
        new RandomEnchantment("Random Enchantment",manager);
        new RandomPotionEffect("Random Effect",manager);
        new ZombieAttack("Zombie Attack",manager);
        new WaterBucketMLG("Water MLG",manager);
        new PumpkinView("Pumpkin View",manager);
        new XpBottles("Xp Bottles", manager);
        new TpToCave("Teleport to cave", manager);
        //new Test("TEST",manager);

    }



}
