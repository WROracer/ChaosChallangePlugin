
package de.wroracer.chaoschallange.chaos.actions.common;

import de.wroracer.chaoschallange.chaos.actions.util.Action;
import de.wroracer.chaoschallange.chaos.actions.util.ActionInfo;
import de.wroracer.chaoschallange.chaos.actions.util.TimedAction;
import de.wroracer.chaoschallange.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

@ActionInfo(name = "PumpkinView")
public class PumpkinView extends Action {


    private transient HashMap<Player, ItemStack> helmets = new HashMap<>();

    @Override
    public boolean setup() {
        return false;
    }

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

