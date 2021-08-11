package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.bad.*;
import de.wroracer.chaoschallange.chaos.actions.bad.AnimalExplode;
import de.wroracer.chaoschallange.chaos.actions.bad.CreeperArmy;
import de.wroracer.chaoschallange.chaos.actions.bad.ReplaceAllWater;
import de.wroracer.chaoschallange.chaos.actions.bad.ZombieAttack;
import de.wroracer.chaoschallange.chaos.actions.common.*;
import de.wroracer.chaoschallange.chaos.actions.common.DropAllItems;
import de.wroracer.chaoschallange.chaos.actions.common.LagSimulator;
import de.wroracer.chaoschallange.chaos.actions.common.MobRegen;
import de.wroracer.chaoschallange.chaos.actions.common.PumpkinView;
import de.wroracer.chaoschallange.chaos.actions.common.RandomEnchantment;
import de.wroracer.chaoschallange.chaos.actions.common.RandomPotionEffect;
import de.wroracer.chaoschallange.chaos.actions.common.RandomizeItems;
import de.wroracer.chaoschallange.chaos.actions.common.ThunderStorm;
import de.wroracer.chaoschallange.chaos.actions.common.Timelapse;
import de.wroracer.chaoschallange.chaos.actions.common.TpToCave;
import de.wroracer.chaoschallange.chaos.actions.common.WaterBucketMLG;
import de.wroracer.chaoschallange.chaos.actions.good.MakeDay;
import de.wroracer.chaoschallange.chaos.actions.good.StopRain;
import de.wroracer.chaoschallange.chaos.actions.good.XpBottles;

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

    public int getActionTime(){
        return manager.getVoteTime();
    }


    public abstract void start();
    public abstract void stop();

    public static void registerActions(ChaosManager manager){

        new AnimalExplode("Animal Explodes",manager);
        new ZombieAttack("Zombie Attack",manager);
        new WaterBucketMLG("Water MLG",manager);
        new PumpkinView("Pumpkin View",manager);
        new Timelapse(manager);
        new CreeperArmy(manager);
        new ReplaceAllWater("Replace water with lava", manager);
        new DropAllItems("Drop all items", manager);
        new LagSimulator("Lag",manager);
        new ThunderStorm("ThunderStorm",manager);
        new TpToCave("Teleport to next cave", manager);

        new DealNoDamage("No Damage",manager);




        // Good Stuff
        new XpBottles("Give Xp", manager);
        new RandomEnchantment("Random Enchantment",manager);
        new RandomPotionEffect("Random Effect",manager);
        new MakeDay("Make day", manager);
        new TpToSurface("Tp to surface", manager);
        new StopRain("Stop rain", manager);

        new RandomizeItems(manager);
        new MobRegen(manager);

        //Temp Disabled (Bugy)
        //new FakeDeath("Death",manager);
      
        //new Test("TEST",manager);
        //new TestListender("TEST",manager);
    }

}
