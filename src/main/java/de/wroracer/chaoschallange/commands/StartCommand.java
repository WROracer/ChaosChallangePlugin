package de.wroracer.chaoschallange.commands;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommand implements CommandExecutor {

    private ChaosManager manager;

    public StartCommand(ChaosManager manager) {
        this.manager = manager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (manager.isActivated){
            manager.deactivate();
        }else {
            manager.activate();
        }
        return false;
    }
}
