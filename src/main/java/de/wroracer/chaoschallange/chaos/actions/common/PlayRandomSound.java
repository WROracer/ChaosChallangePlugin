package de.wroracer.chaoschallange.chaos.actions.common;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.Action;
import org.bukkit.Bukkit;
import org.bukkit.Sound;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PlayRandomSound extends Action {
    public PlayRandomSound( ChaosManager manager) {
        super("Random Sound", manager);
    }

    @Override
    public void start() {
        List<Sound> sounds = Arrays.asList(Sound.values());
        Random ran = new Random();
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.playSound(player.getLocation(),sounds.get(ran.nextInt(sounds.size())),1.0f, 1.0f);
        });
    }

    @Override
    public void stop() {

    }
}
