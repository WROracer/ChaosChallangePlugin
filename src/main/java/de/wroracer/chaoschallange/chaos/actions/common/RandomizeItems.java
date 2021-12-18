package de.wroracer.chaoschallange.chaos.actions.common;

import de.wroracer.chaoschallange.chaos.actions.util.Action;
import de.wroracer.chaoschallange.chaos.actions.util.ActionInfo;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ActionInfo(name = "Inv Randomizer")
public class RandomizeItems extends Action {

    @Override
    public boolean setup() {
        return true;
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
