package de.wroracer.chaoschallange.chaos.actions.common;

import de.wroracer.chaoschallange.chaos.actions.util.Action;
import de.wroracer.chaoschallange.chaos.actions.util.ActionInfo;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@ActionInfo(name = "Random Enchantment")
public class RandomEnchantment extends Action {

    @Override
    public boolean setup() {
        Enchantment[] enchantments = Enchantment.values();
        Collections.addAll(enchantmentList, enchantments);
        return true;
    }

    private transient List<Enchantment> enchantmentList = new ArrayList<>();


    @Override
    public void start() {
        Random random = new Random();
        Bukkit.getOnlinePlayers().forEach(player -> {
            Inventory inv = player.getInventory();
            ItemStack[] items = inv.getContents();
            for (ItemStack item: items) {
                Enchantment enchantment = enchantmentList.get(random.nextInt(enchantmentList.size()));
                if (item!=null){
                    if (item.getEnchantments().containsKey(enchantment)){
                        ItemMeta itemMeta = item.getItemMeta();
                        itemMeta.addEnchant(enchantment,item.getEnchantmentLevel(enchantment)+1,true);
                        item.setItemMeta(itemMeta);
                    }else {
                        ItemMeta itemMeta = item.getItemMeta();
                        itemMeta.addEnchant(enchantment,1,true);
                        item.setItemMeta(itemMeta);
                    }
                }
            }
        });

    }

    @Override
    public void stop() {

    }
}
