package com.game.graphics;

import com.game.util.AssetPool;

public class Animation {
    private String filepath;
    private int dir;
    private int numDirs;
    private int currentFrame;
    private int numFrames;
    
    private int count;
    private int delay;
    
    private int timesplayed;
    
    public Animation() {
        timesplayed = 0;
    }
    
    public Animation(String filepath, int dir) {
        timesplayed = 0;
        setFrames(filepath, dir);
    }
    
    public void setFrames(String filepath, int dir) {
        this.filepath = filepath;
        this.dir = dir;
        count = 0;
        currentFrame = 0;
        delay = 2;
        SpriteSheet spritesheet = AssetPool.getSpriteSheet(filepath);
        numDirs = spritesheet.getHeight();
        numFrames = spritesheet.getWidth();
    }
    
    public void update(float dt) {
        if (delay == -1) return;
        
        count += (int) dt;
        
        if (count >= delay) {
            currentFrame++;
            count = 0;
        }
        
        if (currentFrame >= numFrames) {
            currentFrame = 0;
            timesplayed++;
        }
    }
    
    public Sprite getSprite() {
        return AssetPool.getSpriteSheet(filepath).getSprite(dir, currentFrame);
    }
    
    public boolean hasPlayed() {
        return timesplayed >= 1;
    }
    
    public int getTimesplayed() {
        return timesplayed;
    }
    
    public float getCount() {
        return count;
    }
    
    public int getFrame() {
        return currentFrame;
    }
    
    public void setFrame(int frame) {
        this.currentFrame = frame;
    }
    
    public float getDelay() {
        return delay;
    }
    
    public void setDelay(int delay) {
        this.delay = delay;
    }
    
    public int getDir() {
        return dir;
    }
    
    public void setDir(int dir) {
        this.dir = dir;
    }
}
