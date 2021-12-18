package de.wroracer.chaoschallange.chaos.actions.common;


import de.wroracer.chaoschallange.chaos.actions.util.ActionInfo;
import de.wroracer.chaoschallange.chaos.actions.util.TimedAction;
import org.bukkit.Bukkit;
import org.bukkit.World;

@ActionInfo(name = "Timelapse",delay = 2,period = 2)
public class Timelapse extends TimedAction {

    @Override
    public boolean setup() {
        return super.setup();
    }

    @Override
    public void trigger() {
        for(World world : Bukkit.getServer().getWorlds()){
            world.setTime(world.getTime()+100);
        }
    }
}
