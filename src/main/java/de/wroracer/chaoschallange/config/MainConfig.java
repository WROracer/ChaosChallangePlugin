package de.wroracer.chaoschallange.config;

import de.wroracer.chaoschallange.ChaosChallange;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainConfig {
    private final File file;
    private final File folder;
    private final YamlConfiguration cfg;

    private final String fileName = "config.yml";
    private final String filePath = "plugins//ChaosChallange";

    public MainConfig(){
        folder = new File(filePath);
        file = new File(filePath, fileName);
        cfg = YamlConfiguration.loadConfiguration(file);

        if (!folder.exists()) {
            folder.mkdirs();
        }
        if (!file.exists()) {
            ChaosChallange.getPlugin(ChaosChallange.class).saveDefaultConfig();
                /*cfg.set("Twitch.Channel", new ArrayList<String>(Arrays.asList("Twitch Channels","")));
                cfg.set("Twitch.OAuth2","Twitch Username OAuth2");
                cfg.set("Chaos.Voting.Time",60);
                cfg.set("Chaos.rest.port",4567);
                cfg.save(file);*/
        }
    }
    public List<String> getChannel(){
        return cfg.getStringList("Twitch.Channel");
    }
    public String getOAuth2(){
        return cfg.getString("Twitch.OAuth2");
    }
    public int getVotingTime(){
        return cfg.getInt("Chaos.Voting.Time");
    }
    public int getRestPort(){
        return cfg.getInt("Chaos.rest.port");
    }
}
