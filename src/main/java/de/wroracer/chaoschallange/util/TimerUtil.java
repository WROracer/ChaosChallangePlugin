package de.wroracer.chaoschallange.util;

import de.wroracer.chaoschallange.chaos.ActionManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerUtil {

    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(0);
    private int remainingTime;

    public void start() {
        executorService.scheduleAtFixedRate(() -> {
            remainingTime += 100;
            int hours = (remainingTime / 3600000);
            int minutes = (remainingTime / 60000) % 60;
            int seconds = (remainingTime / 1000) % 60;
            int milliseconds = (remainingTime / 10) % 100;

            StringBuilder builder = new StringBuilder("§7Timer§8: §6§l");
            builder.append(String.format("%02d", hours)).append(":");
            builder.append(String.format("%02d", minutes)).append(":");
            builder.append(String.format("%02d", seconds)).append(".");
            builder.append(String.format("%02d", milliseconds));

            Bukkit.getOnlinePlayers().forEach(player -> sendActionBar(player, builder.toString()));


        }, 0, 100, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        executorService.shutdownNow();
    }

    public void reset(){
        remainingTime = 0;
    }

    private void sendActionBar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message+" §8| §7Next Action§8: §6§l"+ ActionManager.INSTANCE.getNextActionTime()));
    }
}
