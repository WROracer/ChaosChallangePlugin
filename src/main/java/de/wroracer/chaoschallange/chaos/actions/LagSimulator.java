package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class LagSimulator extends Action{
    public LagSimulator(String name, ChaosManager manager) {
        super(name, manager);
    }

    private int schedulerID = 0;

    @Override
    public void start() {
        HashMap<Player, Location[]> locs = new HashMap<>();
        schedulerID = Bukkit.getScheduler().scheduleSyncRepeatingTask(getManager().getPlugin(),()->{
            Bukkit.getOnlinePlayers().forEach(player -> {
                if (locs.get(player) == null){
                    locs.put(player,new Location[2]);
                }
                locs.get(player)[0] = player.getLocation();
                if (locs.get(player)[1]!=null){
                    player.teleport(locs.get(player)[1]);
                }
                locs.put(player,changePos(locs.get(player)));
            });
        },20, 20);
    }

    private Location[] changePos(Location[] locs){
        locs[1] = locs[0];
        return locs;
    }

    @Override
    public void stop() {
        Bukkit.getScheduler().cancelTask(schedulerID);
    }
}
