package de.wroracer.chaoschallange.chaos.actions;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class TestListender extends ActionListener{

    private boolean isActivated;

    public TestListender(String name, ChaosManager manager) {
        super(name, manager);
        isActivated = false;
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
