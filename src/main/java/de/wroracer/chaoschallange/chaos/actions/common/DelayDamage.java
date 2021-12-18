package de.wroracer.chaoschallange.chaos.actions.common;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.util.ActionInfo;
import de.wroracer.chaoschallange.chaos.actions.util.ActionListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

@ActionInfo(name = "Delay Damage")
public class DelayDamage extends ActionListener {


    private transient double totalDamage = 0;
    private transient boolean isActive = false;

    @Override
    public void start() {
        totalDamage = 0;
        isActive = true;
    }

    @Override
    public void stop() {
        isActive = false;
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            p.sendMessage(this.getName() +" §9got §4" + totalDamage  /2  + " §9total hearts damage");
            p.damage(totalDamage);
        }
    }

    @Override
    public int getActionTime() {
        return super.getActionTime() * 2;
    }

    @EventHandler
    public void EntityDamageEvent(EntityDamageEvent event) {
        if (isActive) {
            if (event.getEntityType() == EntityType.PLAYER) {

                totalDamage += event.getDamage();
                event.setDamage(0);
            }
        }
    }
}
