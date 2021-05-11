package com.game.util.data;

public class PlayerData {
    private final String name;
    private int highScore = 0;
    
    public PlayerData(String n) {
        name = n;
    }
    
    public PlayerData(String n, int highScore) {
        name = n;
        this.highScore = highScore;
    }
    
    public boolean equals(String key) {
        return key.equals(name);
    }
    
    public int getHighScore() {
        return highScore;
    }
    
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
