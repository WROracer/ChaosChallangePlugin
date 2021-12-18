package de.wroracer.chaoschallange.chaos.actions.common;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import de.wroracer.chaoschallange.chaos.actions.util.Action;

public class RenameItems extends Action {

    @Override
    public void start() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {

            List<String> names = new ArrayList<>();

            PlayerInventory inv = p.getInventory();

            // initiate names
            for (int i = 0; i < inv.getSize(); i++) {
                ItemStack item = inv.getItem(i);
                if (item != null) {

                    Material meta = item.getType();
                    names.add(meta.toString());
                }
            }
            System.out.println(names);
            // set names
            for (int i = 0; i < inv.getSize(); i++) {
                ItemStack item = inv.getItem(i);
                if (item != null) {

                    ItemMeta meta = item.getItemMeta();
                    if (meta != null) {
                        String name = names.get((int) (Math.random() * names.size()));
                        names.remove(name);

                        // beautify name so STONE_SHOVEL is Stone Shovel
                        name = name.toLowerCase();
                        name = name.replace("_", " ");

                        // uppercase every word
                        String[] words = name.split(" ");
                        StringBuilder sb = new StringBuilder();
                        for (String word : words) {
                            sb.append(word.substring(0, 1).toUpperCase()).append(word.substring(1)).append(" ");
                        }
                        name = sb.toString();

                        meta.setDisplayName("§r" + name);

                        item.setItemMeta(meta);

                        System.out.println("set name: " + name);
                    }
                }
            }
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean setup() {
        return true;
    }
}
