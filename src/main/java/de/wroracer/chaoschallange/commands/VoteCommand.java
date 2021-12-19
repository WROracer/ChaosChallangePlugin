package de.wroracer.chaoschallange.commands;

import de.wroracer.chaoschallange.chaos.ActionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class VoteCommand implements CommandExecutor {
    private ActionManager manager;

    public VoteCommand(ActionManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1){
            int vote = Integer.parseInt(args[0]);
            manager.registerVote(vote,sender.getName());
        }
        return false;
    }
}
