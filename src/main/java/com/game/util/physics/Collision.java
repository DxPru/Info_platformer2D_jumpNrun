package com.game.util.physics;

import com.game.util.math.Vector2f;

public class Collision {
    public Rect rect = new Rect();
    
    public Collision() {
    }
    
    public Collision(Rect rect) {
        this.rect = rect;
    }
    
    public boolean CollRect(Rect rect) {
        return CollRectX(rect) && CollRectY(rect);
        
    }
    
    public boolean CollRectX(Rect rect) {
        return this.rect.getPos().x < rect.getPos().x + rect.getSize().x &&
                this.rect.getPos().x + this.rect.getSize().x > rect.getPos().x;
    }
    
    public boolean CollRectY(Rect rect) {
        return this.rect.getPos().y < rect.getPos().y + rect.getSize().y &&
                this.rect.getPos().y + this.rect.getSize().y > rect.getPos().y;
    }
    
    public boolean CollX(float x) {
        return this.rect.getPos().x <= x && this.rect.getPos().x + this.rect.getSize().x >= x;
    }
    
    public boolean CollY(float y) {
        return this.rect.getPos().y <= y && this.rect.getPos().y + this.rect.getSize().y >= y;
    }
    
    public boolean CollYPos(float y) {
        return this.rect.getPos().y + this.rect.getSize().y >= y;
    }
    
    public boolean CollVf(Vector2f pos) {
        return this.CollX(pos.x) && this.CollY(pos.y);
    }
    
    public CollisionType CollDir(Rect rect) {
        Vector2f posRect = new Vector2f(rect.getPos()).add(new Vector2f(rect.getSize()).mul(0.5f));
        Vector2f posColl = new Vector2f(this.rect.getPos()).add(new Vector2f(this.rect.getSize()).mul(0.5f));
        Vector2f vec = new Vector2f(posRect).sub(posColl);
        // vec.normalize(); // nicht unbedingt nötig aber leichter nach zu vollziehen
        
        // float tan = (float) Math.toDegrees(Math.tanh(vec.y / vec.x));
        // float len = vec.abs();
        // System.out.println(tan);
        
        // minimalste Collision unterschiedet sich nicht von maximaler Collision (float rundungsfehler lösen Collisionen im edge case aus)
        
        if (Math.abs(vec.x) > Math.abs(vec.y)) {
            if (vec.x > Math.abs(vec.y)) {
                return CollisionType.xPos;
            } else if (-vec.x > Math.abs(vec.y)) {
                return CollisionType.xNeg;
            }
        } else if (Math.abs(vec.x) < Math.abs(vec.y)) {
            if (-vec.y > Math.abs(vec.x)) {
                return CollisionType.yNeg;
            } else if (vec.y > Math.abs(vec.x)) {
                return CollisionType.yPos;
            }
        }
        
        return CollisionType.noColl;
    }
}
