package de.wroracer.chaoschallange;

import de.wroracer.chaoschallange.chaos.ChaosManager;
import de.wroracer.chaoschallange.chaos.actions.Action;
import de.wroracer.chaoschallange.commands.StartCommand;
import de.wroracer.chaoschallange.commands.TestActionCommand;
import de.wroracer.chaoschallange.commands.VoteCommand;
import de.wroracer.chaoschallange.util.Settings;
import de.wroracer.chaoschallange.util.UserSettings;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChaosChallange extends JavaPlugin {

    private final String prefix = "[ChaosPL] ";

    public static ChaosChallange INSTANCE ;

    public Settings settings;

    private ChaosManager manager;
    @Override
    public void onEnable() {
        INSTANCE = this;
        // Plugin startup logic
        settings = Settings.getSettings();
        manager = new ChaosManager(this);
        Action.registerActions(manager);
        initCommands();
        System.out.println(prefix+"Commands Initzialisiert");


        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(manager,this);

    }

    @Override
    public void onDisable() {
        manager.stop();// Plugin shutdown logic
        settings.save();
    }


    private void initCommands(){
        getCommand("vote").setExecutor(new VoteCommand(manager));
        getCommand("start").setExecutor(new StartCommand(manager));
        TestActionCommand testActionCommand = new TestActionCommand(manager);
        getCommand("test").setExecutor(testActionCommand);
        getCommand("test").setTabCompleter(testActionCommand);
    }
}
