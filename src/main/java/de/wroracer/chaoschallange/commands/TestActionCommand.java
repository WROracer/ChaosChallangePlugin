package de.wroracer.chaoschallange.commands;

import de.wroracer.chaoschallange.chaos.ActionManager;
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
    private ActionManager chaosManager;
    private List<Action> actions;
    private final MainConfig config;

    public TestActionCommand(ActionManager chaosManager) {
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
        actions.forEach(action -> {
            if (action.getName().toLowerCase().equals(finalCmd.toLowerCase().replaceFirst(" ", ""))){
                Bukkit.getServer().broadcastMessage("§4Testing Action: §2"+action.getName());
                chaosManager.testAction(action);
            }
        });
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        List<String> back = new ArrayList<>();
        if (strings.length >= 1) {
            actions.forEach(action -> {
                if (action.getName().toLowerCase().startsWith(strings[0].toLowerCase())) {
                    back.add(action.getName());
                }
            });
        }
        return back;
    }
}
