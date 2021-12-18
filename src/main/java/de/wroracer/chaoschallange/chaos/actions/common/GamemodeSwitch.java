package de.wroracer.chaoschallange.chaos.actions.common;

import de.wroracer.chaoschallange.chaos.actions.util.Action;
import de.wroracer.chaoschallange.chaos.actions.util.ActionInfo;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

@ActionInfo(name = "Gamemode")
public class GamemodeSwitch extends Action {

    private HashMap<Player, GameMode> gameModeHashMap;

    @Override
    public boolean setup() {
        gameModeHashMap = new HashMap<>();
        return true;
    }

    @Override
    public int getActionTime() {
        return 5;
    }

    @Override
    public void start() {
        List<GameMode> gameModes = Arrays.asList(GameMode.values());
        Random random = new Random();
        Bukkit.getOnlinePlayers().forEach(player -> {
            gameModeHashMap.put(player,player.getGameMode());
            player.setGameMode(gameModes.get(random.nextInt(gameModes.size())));
            player.sendMessage("Du hast nun 5 sec "+player.getGameMode().name());
        });
    }

    @Override
    public void stop() {
        gameModeHashMap.forEach(HumanEntity::setGameMode);
        gameModeHashMap.clear();
    }
}
