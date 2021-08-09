
package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class PumpkinView extends Action {
    public PumpkinView(String name, ChaosManager manager) {
        super(name, manager);
    }

    private HashMap<Player, ItemStack> helmets = new HashMap<>();

    @Override
    public void start() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            ItemStack helmet = p.getInventory().getHelmet();
            helmets.put(p, helmet);
            ItemStack pumpkin = new ItemBuilder(Material.CARVED_PUMPKIN).buid();
            ItemMeta itemMeta = pumpkin.getItemMeta();
            itemMeta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
            pumpkin.setItemMeta(itemMeta);
            p.getInventory().setHelmet(pumpkin);
        }
    }


    @Override
    public void stop() {

        helmets.forEach((player, helmet) -> {
            player.getInventory().setHelmet(helmet);
        });

    }
}

