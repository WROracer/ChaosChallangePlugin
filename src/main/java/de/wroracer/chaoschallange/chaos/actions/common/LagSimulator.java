package de.wroracer.chaoschallange.chaos.actions.common;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.Action;
import de.wroracer.chaoschallange.chaos.actions.TimedAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class LagSimulator extends TimedAction {
    public LagSimulator(String name, ChaosManager manager) {
        super(name, manager,20, 20);
    }

    private transient HashMap<Player, Location[]> locs = new HashMap<>();

    private Location[] changePos(Location[] locs){
        locs[1] = locs[0];
        return locs;
    }

    @Override
    public void trigger() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            locs.computeIfAbsent(player, k -> new Location[2]);
            locs.get(player)[0] = player.getLocation();
            if (locs.get(player)[1]!=null){
                player.teleport(locs.get(player)[1]);
            }
            locs.put(player,changePos(locs.get(player)));
        });
    }
}
