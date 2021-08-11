package de.wroracer.chaoschallange.chaos.actions.common;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.Action;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomizeItems extends Action {
    public RandomizeItems( ChaosManager manager) {
        super("Randomize Item", manager);
    }

    @Override
    public void start() {
        Bukkit.getOnlinePlayers().forEach(p->{
            PlayerInventory inv = p.getInventory();
            List<ItemStack> items = new ArrayList<>();
            for (int i =0;i!=inv.getSize();i++){
                items.add(inv.getItem(i));
            }
            Collections.shuffle(items);
            for (int i = 0; i!= items.size();i++){
                inv.setItem(i,items.get(i));
            }
        });
    }

    @Override
    public void stop() {

    }
}
