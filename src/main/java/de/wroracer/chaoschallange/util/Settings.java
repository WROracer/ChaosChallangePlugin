package de.wroracer.chaoschallange.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Settings {
    private static final String SETTINGS_FOLDER = "plugins//ChaosChallange//data";
    private static final String SETTINGS_FILE = "settings.json";

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static Settings getSettings(){
        File file = getFile();
        StringBuilder jsonBuilder = new StringBuilder();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                jsonBuilder.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String json = jsonBuilder.toString();

        if (json.isEmpty()){
            return new Settings();
        }

        return gson.fromJson(json,Settings.class);
    }

    private static File getFile(){
        File folder = new File(SETTINGS_FOLDER);
        if (!folder.exists()){
            folder.mkdirs();
        }
        File file = new File(folder,SETTINGS_FILE);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }



    private HashMap<String,UserSettings> userSettings;

    private Settings(){
        userSettings = new HashMap<>();
    }

    public void saveUserSettings(String uuid, UserSettings settings){
        userSettings.put(uuid, settings);
    }

    public UserSettings getUserSettings(String uuid){
       return userSettings.get(uuid);
    }

    public void save(){
        String json = gson.toJson(this);
        try {
            FileWriter writer = new FileWriter(getFile());
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
