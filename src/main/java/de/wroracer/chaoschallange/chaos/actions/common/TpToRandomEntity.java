package de.wroracer.chaoschallange.chaos.actions.common;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.Action;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public class TpToRandomEntity extends Action {
    public TpToRandomEntity(String name, ChaosManager manager) {
        super(name, manager);
    }

    @Override
    public void start() {
        for (Player p : Bukkit.getOnlinePlayers()){
            List<Entity> entities = p.getWorld().getEntities();

            Random randomizer = new Random();
            Entity random = entities.get(randomizer.nextInt(entities.size()));
            p.teleport(random);
        }
    }

    @Override
    public void stop() {

    }
}
