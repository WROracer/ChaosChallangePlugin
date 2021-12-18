package de.wroracer.chaoschallange.commands;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.util.Action;
import de.wroracer.chaoschallange.config.MainConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TestActionCommand implements CommandExecutor, TabCompleter {
    private ChaosManager chaosManager;
    private List<Action> actions;
    private final MainConfig config;

    public TestActionCommand(ChaosManager chaosManager) {
        this.chaosManager = chaosManager;
        actions = chaosManager.getActions();
        this.config = new MainConfig();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        String cmd= "";
        for (String a:strings) {
            cmd+=" "+a;
        }
        String finalCmd = cmd;
        commandSender.sendMessage(cmd.replaceFirst(" ", ""));
        actions.forEach(action -> {
            if (action.getName().equals(finalCmd.replaceFirst(" ", ""))){
                action.start();
                Bukkit.getScheduler().scheduleSyncDelayedTask(chaosManager.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        action.stop();
                    }
                }, 20*action.getActionTime());
            }
        });
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> back = new ArrayList<>();
        if (strings.length >= 1) {
            actions.forEach(action -> {
                if (action.getName().startsWith(strings[0])) {
                    back.add(action.getName());
                }
            });
        }
        return back;
    }
}
