package de.wroracer.chaoschallange.chaos.actions.common;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.Action;
import org.bukkit.Bukkit;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomPotionEffect extends Action {
    private transient List<PotionEffectType> potionEffects = new ArrayList<>();
    private transient ChaosManager chaosManager;
    public RandomPotionEffect(String name, ChaosManager manager) {
        super(name, manager);
        PotionEffectType[] potionEffectstemp = PotionEffectType.values();
        Collections.addAll(potionEffects, potionEffectstemp);
        this.chaosManager = manager;
    }

    @Override
    public void start() {
        Random random = new Random();
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.addPotionEffect(potionEffects.get(random.nextInt(potionEffects.size())).createEffect(chaosManager.getVoteTime()*20,random.nextInt(10)));
        });
    }

    @Override
    public void stop() {

    }
}
