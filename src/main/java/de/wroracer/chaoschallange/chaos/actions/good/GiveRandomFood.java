package de.wroracer.chaoschallange.chaos.actions.good;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import de.wroracer.chaoschallange.chaos.actions.util.Action;
import de.wroracer.chaoschallange.chaos.actions.util.ActionInfo;
import de.wroracer.chaoschallange.util.ItemBuilder;

@ActionInfo(name = "Give Random Food")
public class GiveRandomFood extends Action {
    private static final int minFood = 1;
    private static final int maxFood = 10;

    @Override
    public boolean setup() {
        return true;
    }

    @Override
    public void start() {
        // make a list with all possible food items

        List<Material> items = new ArrayList<>();
        for (Material item : Material.values()) {
            if (item.isItem() && item.isEdible()) {
                // add item to list
                items.add(item);
            }
        }
        // add item of random amount to every player
        for (Player p : Bukkit.getOnlinePlayers()) {
            Random randomizer = new Random();
            int random = randomizer.nextInt(items.size());
            int itemAmount = randomizer.nextInt(maxFood - minFood + 1) + minFood;
            // add random amount of food to player
            p.getInventory().addItem(new ItemBuilder(items.get(random)).setAmount(itemAmount).buid());
            // p.getInventory().addItem(new
            // ItemBuilder(items.get(random)).setAmount(randomizer).buid());
            // p.getInventory().addItem(new ItemBuilder(items.get(random)).buid());
            p.sendMessage("You got " + itemAmount + " " + items.get(random).name());
        }

    }

    @Override
    public void stop() {

    }

}
