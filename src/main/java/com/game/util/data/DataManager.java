package com.game.util.data;

public class DataManager {
   
   private final List playerData = new List();

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

   public int getHighScore(String name) {
       return playerData.searchName(name).getHighScore();
   }

   public void setHighScore(String name, int highScore) {
       playerData.searchName(name).setHighScore(highScore);
   }
}
