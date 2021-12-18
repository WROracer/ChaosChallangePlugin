package de.wroracer.chaoschallange.chaos.actions.common;

import de.wroracer.chaoschallange.chaos.actions.util.Action;
import de.wroracer.chaoschallange.chaos.actions.util.ActionInfo;
import org.bukkit.Bukkit;
import org.bukkit.Sound;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@ActionInfo(name = "Random Sound")
public class PlayRandomSound extends Action {

    @Override
    public boolean setup() {
        return true;
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
