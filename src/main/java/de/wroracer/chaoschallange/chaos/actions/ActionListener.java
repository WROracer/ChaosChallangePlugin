package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public abstract class ActionListener extends Action implements Listener {
    public ActionListener(String name, ChaosManager manager) {
        super(name, manager);
        Bukkit.getPluginManager().registerEvents(this,manager.getPlugin());
    }
}
