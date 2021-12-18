package de.wroracer.chaoschallange.chaos.actions.bad;

import de.wroracer.chaoschallange.chaos.actions.util.ActionInfo;
import de.wroracer.chaoschallange.chaos.actions.util.ActionListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@ActionInfo(name = "Deal no Damage")
public class DealNoDamage extends ActionListener {

    @Override
    public boolean setup() {
        return true;
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
