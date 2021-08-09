
package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PumpkinView extends Action {
    public PumpkinView(String name, ChaosManager manager) {
        super(name, manager);
    }

    @Override
    public void start() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            p.getInventory().forEach(item -> {
                if(item != null){

                System.out.println(item);
                }
            });
        }
    }

    @Override
    public void stop() {

    }
}

