package de.wroracer.chaoschallange.chaos.actions.common;

import de.wroracer.chaoschallange.chaos.actions.util.ActionInfo;
import de.wroracer.chaoschallange.chaos.actions.util.TimedAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

@ActionInfo(name = "Lag",period = 20,delay = 20)
public class LagSimulator extends TimedAction {

    @Override
    public boolean setup() {
        return super.setup();
    }

    private transient HashMap<Player, Location[]> locs = new HashMap<>();

    private Location[] changePos(Location[] locs){
        locs[1] = locs[0];
        return locs;
    }

    @Override
    public void trigger() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            Location loc = player.getLocation();
            Bukkit.getScheduler().scheduleSyncDelayedTask(getManager().getPlugin(), ()->player.teleport(loc),10);
           /* locs.computeIfAbsent(player, k -> new Location[2]);
            if (locs.get(player)[1]!=null){
                player.teleport(locs.get(player)[1]);
            }
            locs.get(player)[0] = player.getLocation();
            locs.put(player,changePos(locs.get(player)));*/
        });
    }
}
