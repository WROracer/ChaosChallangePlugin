package de.wroracer.chaoschallange.chaos.actions.util;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

@ActionInfo(name = "Test Listender")
public class TestListender extends ActionListener{

    private boolean isActivated;

    @Override
    public boolean setup() {
        isActivated = false;
        return false;
    }

    @Override
    public void start() {
        isActivated = true;
    }

    @Override
    public void stop() {
        isActivated = false;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        if (isActivated){
            event.setMessage("NE");
        }
    }
}
