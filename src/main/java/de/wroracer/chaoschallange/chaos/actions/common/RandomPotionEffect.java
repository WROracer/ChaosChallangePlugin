package de.wroracer.chaoschallange.chaos.actions.common;

import de.wroracer.chaoschallange.chaos.actions.util.Action;
import de.wroracer.chaoschallange.chaos.actions.util.ActionInfo;
import org.bukkit.Bukkit;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@ActionInfo(name = "Random Potion Effect")
public class RandomPotionEffect extends Action {
    private transient List<PotionEffectType> potionEffects;

    @Override
    public boolean setup() {
        potionEffects = new ArrayList<>();
        PotionEffectType[] potionEffected = PotionEffectType.values();
        Collections.addAll(potionEffects, potionEffected);
        return true;
    }

    @Override
    public void start() {
        Random random = new Random();
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.addPotionEffect(potionEffects.get(Math.abs(random.nextInt(potionEffects.size()))).createEffect(getManager().getVoteTime()*20,Math.abs(random.nextInt(10))));
        });
    }

    @Override
    public void stop() {

    }
}
