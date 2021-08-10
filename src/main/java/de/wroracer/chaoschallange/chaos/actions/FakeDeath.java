package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;

public class FakeDeath extends Action{
    public FakeDeath(String name, ChaosManager manager) {
        super(name, manager);
    }

    @Override
    public void start() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            List<ItemStack> inv = getInventory(player.getInventory());
            Location loc = player.getLocation();
            player.setHealth(0);
            restoreInventory(inv, player.getInventory());
            player.teleport(loc);
        });
    }

    private List<ItemStack> getInventory(PlayerInventory p){
        List<ItemStack> ret = new ArrayList<>();
        for (int i = 0;i!=44;i++){
            ret.add(i,p.getItem(i));
        }
        System.out.println(ret);
        return ret;
    }

    private void restoreInventory(List<ItemStack> inv,PlayerInventory p){
        for (int i = 0;i!=44;i++){
            p.setItem(i,inv.get(i));
        }
    }

    @Override
    public void stop() {

    }
}
