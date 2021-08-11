//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package de.wroracer.chaoschallange.chaos.actions.common;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.Action;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;

public class DropAllItems extends Action {
    public DropAllItems(String name, ChaosManager manager) {
        super(name, manager);
    }

    public void start() {
        Iterator var1 = Bukkit.getServer().getOnlinePlayers().iterator();

        while(var1.hasNext()) {
            Player p = (Player)var1.next();
            ItemStack[] var3 = p.getInventory().getContents();
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                ItemStack item = var3[var5];
                if (item != null) {
                    p.getWorld().dropItemNaturally(p.getLocation(), item).setPickupDelay(40);
                }
            }

            p.getInventory().clear();
        }

    }

    public void stop() {
    }
}
