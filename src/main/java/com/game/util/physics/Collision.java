package com.game.util.physics;

import com.game.util.math.Vector2f;

public class Collision {
    public Rect rect = new Rect();
    
    public Collision() {}
    
    public Collision(Rect rect) {
        this.rect = rect;
    }
    
    public boolean CollRect(Rect rect) {
        return !(this.rect.getPos().y >= rect.getPos().y + rect.getSize().y ||
                rect.getPos().y >= this.rect.getPos().y + this.rect.getSize().y ||
                this.rect.getPos().x >= rect.getPos().x + rect.getSize().x ||
                rect.getPos().x >= this.rect.getPos().x + this.rect.getSize().x);
        
    }
    
    public boolean CollRectXPos(Rect rect) {
        return this.rect.getPos().x + this.rect.getSize().x >= rect.getPos().x;
    }
    
    public boolean CollRectXNeg(Rect rect) {
        return this.rect.getPos().x <= rect.getPos().x + rect.getSize().x;
    }
    
    public boolean CollRectYPos(Rect rect) {
        return this.rect.getPos().y + this.rect.getSize().y >= rect.getPos().y;
    }
    
    public boolean CollRectYNeg(Rect rect) {
        return this.rect.getPos().y <= rect.getPos().y + rect.getSize().y;
    }
    
    public boolean CollX(float x) {
        return this.rect.getPos().x <= x && this.rect.getPos().x + this.rect.getSize().x >= x;
    }
    
    public boolean CollY(float y) {
        return this.rect.getPos().y <= y && this.rect.getPos().y + this.rect.getSize().y >= y;
    }
    
    public boolean CollVf(Vector2f pos) {
        return this.CollX(pos.x) && this.CollY(pos.y);
    }
}
