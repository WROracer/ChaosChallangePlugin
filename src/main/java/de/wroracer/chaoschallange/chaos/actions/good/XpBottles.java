package de.wroracer.chaoschallange.chaos.actions.good;

import de.wroracer.chaoschallange.chaos.actions.util.Action;
import de.wroracer.chaoschallange.chaos.actions.util.ActionInfo;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

@ActionInfo(name = "Xp-Bottels")
public class XpBottles extends Action {
    @Override
    public boolean setup() {
        return true;
    }


    @Override
    public void start() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            Location pos = p.getLocation();
            World world = pos.getWorld();
            for (int i = 0; i< 50; i++){
            world.spawnEntity(pos, EntityType.THROWN_EXP_BOTTLE);
            }

        }
    }

    @Override
    public void stop() {

    }
}
