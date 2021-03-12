package de.wroracer.chaoschallange.config;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MainConfig {
    private final File file;
    private final File folder;
    private final YamlConfiguration cfg;

    private final String fileName = "config.yml";
    private final String filePath = "plugins//chaos";

    public MainConfig(){
        folder = new File(filePath);
        file = new File(filePath, fileName);
        cfg = YamlConfiguration.loadConfiguration(file);

        if (!folder.exists()) {
            folder.mkdirs();
        }
        if (!file.exists()) {
            try {
               cfg.set("Twitch.Channel","Twitch LiveStream Kannal");
                cfg.set("Twitch.OAuth2","Twitch Username OAuth2");

                cfg.save(file);
            } catch (IOException ignored) {
            }
        }
    }
    public String getChannel(){
        return cfg.getString("Twitch.Channel");
    }
    public String getOAuth2(){
        return cfg.getString("Twitch.OAuth2");
    }
}
