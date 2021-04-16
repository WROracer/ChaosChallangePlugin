package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomPotionEffect extends Action{
    List<PotionEffectType> potionEffects = new ArrayList<>();
    private ChaosManager chaosManager;
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
