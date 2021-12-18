package de.wroracer.chaoschallange.chaos.actions.good;

import de.wroracer.chaoschallange.chaos.actions.util.Action;
import de.wroracer.chaoschallange.chaos.actions.util.ActionInfo;
import de.wroracer.chaoschallange.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@ActionInfo(name = "Random Stuff")
public class GiveRandomStuff extends Action {

    @Override
    public boolean setup() {
        return true;
    }


    @Override
    public void start() {
        //list of possible matreials
        List<String> materials = new ArrayList<>();
        materials.add("wooden_");
        materials.add("stone_");
        materials.add("golden_");
        materials.add("iron_");
        materials.add("diamond_");
        materials.add("netherite_");

        // list of all possible items
        List<Material> items = new ArrayList<>();

        for (String m : materials) {
            for (Material item : Material.values()) {
                if (item.isItem() && !item.isBlock()) {
                    String name = item.toString().toLowerCase();
                    if (name.startsWith(m)) {
                        items.add(item);
                    }
                }
            }
        }
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
