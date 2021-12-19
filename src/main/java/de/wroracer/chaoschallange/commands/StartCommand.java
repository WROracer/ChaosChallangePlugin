package de.wroracer.chaoschallange.commands;

import de.wroracer.chaoschallange.chaos.ActionManager;
import de.wroracer.chaoschallange.util.Constants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommand implements CommandExecutor {

    private ActionManager manager;

    public StartCommand(ActionManager manager) {
        this.manager = manager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0){
            sender.sendMessage(Constants.PREFIX+"§aYou must Specify start or stop");
        }else {
            switch (args[0]){
                case "start":manager.startChaos();break;
                case "stop":manager.stopChaos();break;
            }
        }
        return false;
    }
}
