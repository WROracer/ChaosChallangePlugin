package de.wroracer.chaoschallange.chaos.actions.good;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.Action;
import de.wroracer.chaoschallange.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GiveRandomItems extends Action {
    public GiveRandomItems(String name, ChaosManager manager) {
        super(name, manager);
    }

    @Override
    public void start() {
        List<Material> items = Arrays.asList(Material.values());
        // give item for every player
        for (Player p : Bukkit.getOnlinePlayers()) {
            Random randomizer = new Random();
            Material random = items.get(randomizer.nextInt(items.size()));
            p.getInventory().addItem(new ItemBuilder(random).buid());
            p.sendMessage("You got item: " + random.name());

        }

    }

    @Override
    public void stop() {

    }
}
