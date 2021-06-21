package com.game.util.physics;

import com.game.util.math.Vector2f;

public class Rect {
    private Vector2f pos = new Vector2f();
    private Vector2f size = new Vector2f();
    
    public Rect() {
    
    }
    
    public Rect(Vector2f pos, Vector2f size) {
        this.pos = pos;
        this.size = size;
    }
    
    public Vector2f getPos() {
        return pos;
    }
    
    public void setPos(Vector2f pos) {
        this.pos = pos;
    }
    
    public Vector2f getSize() {
        return size;
    }
    
    public void setSize(Vector2f size) {
        this.size = size;
    }
}
