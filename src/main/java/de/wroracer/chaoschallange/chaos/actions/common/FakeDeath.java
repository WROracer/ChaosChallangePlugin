package de.wroracer.chaoschallange.chaos.actions.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import de.wroracer.chaoschallange.chaos.actions.util.ActionInfo;
import de.wroracer.chaoschallange.chaos.actions.util.ActionListener;

@ActionInfo(name = "Fake Death")
public class FakeDeath extends ActionListener {

    private transient HashMap<Player, Location> positions = new HashMap<>();

    @Override
    public void start() {
        // positions.clear();
        Bukkit.getOnlinePlayers().forEach(player -> {
            List<ItemStack> inv = getInventory(player.getInventory());
            Location loc = player.getLocation();
            positions.put(player, loc);
            player.getInventory().clear();
            player.setHealth(0);
            restoreInventory(inv, player.getInventory());

        });
    }

    private List<ItemStack> getInventory(PlayerInventory p) {
        List<ItemStack> ret = new ArrayList<>();
        for (int i = 0; i != 44; i++) {
            ret.add(i, p.getItem(i));
        }
        // System.out.println(ret);
        return ret;
    }

    private void restoreInventory(List<ItemStack> inv, PlayerInventory p) {
        for (int i = 0; i != 44; i++) {
            p.setItem(i, inv.get(i));
        }
    }

    @Override
    public void stop() {

    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) { // WHY DOES THIS NOT TRIGGER???????
        // Player player = event.getPlayer();
        // System.out.println("baum");
        // System.out.println("Player " + event.getPlayer().getName() + " respawned");

        if (positions.keySet().stream().anyMatch(p -> event.getPlayer().getUniqueId().equals(p.getUniqueId()))) {
            Player player = event.getPlayer();
            event.setRespawnLocation(positions.get(player));
            // player.teleport(location);
            positions.remove(player);
        }

    }
}
