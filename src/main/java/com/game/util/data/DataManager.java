package com.game.util.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataManager {
    private final List playerData = new List();
    private String dataPath;
    
    public DataManager() {
        dataPath = "res/data/login.json";
    }
    
    public boolean isValid(String name) {
        return playerData.searchName(name) != null;
    }
    
    public void insertPlayer(String name, int highScore) {
        if (!isValid(name)) {
            playerData.insertFront(new PlayerData(name, highScore));
        } else {
            setHighScore(name, highScore);
        }
    }
    
    public void insertPlayer(String name) {
        if (!isValid(name)) {
            playerData.insertFront(new PlayerData(name));
        }
    }
    
    public void insertPlayer(PlayerData playerData) {
        if (!isValid(playerData.getName())) {
            this.playerData.insertFront(playerData);
        }
    }
    
    public int getHighScore(String name) {
        if (isValid(name)) {
            return playerData.searchName(name).getHighScore();
        }
        return 0;
    }
    
    public void setHighScore(String name, int highScore) {
        if (!name.equals("")) {
            if (!isValid(name)) {
                insertPlayer(name);
            }
            playerData.searchName(name).setHighScore(highScore);
        }
    }
    
    public void save() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        try {
            FileWriter writer  = new FileWriter(dataPath);
            writer.write(gson.toJson(this.playerData.getData()));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void load() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        String inFile = "";
        
        try {
            inFile = new String(Files.readAllBytes(Paths.get(dataPath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if (!inFile.equals("")) {
            PlayerData[] players = gson.fromJson(inFile, PlayerData[].class);
            
            if (players != null) {
                for (PlayerData data : players) {
                    playerData.insertFront(data);
                }
            }
        }
    }
}
