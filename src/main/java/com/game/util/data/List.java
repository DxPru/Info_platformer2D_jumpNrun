package com.game.util.data;

public class List {
    public Knot start;
    
    public List() {
        start = null;
    }
    
    public void insertEnd(PlayerData s) {
        Knot k = new Knot(s);
        if (start != null) {
            start.insertEnd(k);
        } else {
            start = k;
        }
        
    }
    
    public void insertFront(PlayerData s) {
        if (start == null) {
            insertEnd(s);
        } else {
            Knot k = new Knot(s);
            k.setFollower(start);
            start = k;
        }
    }
    
    public PlayerData searchName(String key) {
        if (start != null) {
            return start.searchName(key);
        } else {
            return null;
        }
    }
    
}

