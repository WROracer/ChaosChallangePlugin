package de.wroracer.chaoschallange.chaos.actions.bad;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.ActionListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DealNoDamage extends ActionListener {
    public DealNoDamage(String name, ChaosManager manager) {
        super(name, manager);
    }

    @EventHandler
    public void playerDamageDellEvent(EntityDamageByEntityEvent event){
        if (isActive) {
            if (event.getDamager() instanceof Player) {
                ((Player) event.getDamager()).damage(event.getDamage());
                event.setCancelled(true);
            }
        }
    }
}
