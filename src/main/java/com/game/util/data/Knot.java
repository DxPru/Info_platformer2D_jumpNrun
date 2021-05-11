package com.game.util.data;

public class Knot {
    private final PlayerData playerData;
    private Knot follower;
    
    public Knot(PlayerData s) {
        playerData = s;
    }
    
    public void setFollower(Knot k) {
        follower = k;
    }
    
    public void insertEnd(Knot k) {
        if (follower != null) {
            follower.insertEnd(k);
        } else {
            setFollower(k);
        }
    }
    
    public PlayerData searchName(String key) {
        if (playerData.equals(key)) {
            return playerData;
        } else {
            if (follower != null) {
                return follower.searchName(key);
            } else {
                return null;
            }
        }
    }
}
