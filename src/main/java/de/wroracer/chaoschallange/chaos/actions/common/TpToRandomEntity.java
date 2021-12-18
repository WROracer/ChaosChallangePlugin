package de.wroracer.chaoschallange.chaos.actions.common;

import de.wroracer.chaoschallange.chaos.actions.util.Action;
import de.wroracer.chaoschallange.chaos.actions.util.ActionInfo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

@ActionInfo(name = "Tp to Random Entity")
public class TpToRandomEntity extends Action {

    @Override
    public boolean setup() {
        return true;
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
