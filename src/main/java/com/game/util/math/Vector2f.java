package com.game.util.math;

public class Vector2f {
    public float x;
    public float y;
    
    public Vector2f() {
        this.x = 0;
        this.y = 0;
    }
    
    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public Vector2f(Vector2f v2f) {
        this.x = v2f.x;
        this.y = v2f.y;
    }
    
    public Vector2f add(Vector2f v2f) {
        this.x += v2f.x;
        this.y += v2f.y;
        
        return this;
    }
    
    public Vector2f sub(Vector2f v2f) {
        this.x -= v2f.x;
        this.y -= v2f.y;
        
        return this;
    }
    
    public Vector2f mul(Vector2f v2f) {
        this.x = (this.y * v2f.x) - (this.x * v2f.y);
        this.y = (this.x * v2f.y) - (this.y * v2f.x);
        
        return this;
    }
    
    public Vector2f mul(float multiplier) {
        this.x *= multiplier;
        this.y *= multiplier;
        return this;
    }
    
    public Vector2f normalize() {
        this.mul(1 / abs());
        return this;
    }
    
    public float abs() {
        return (float) Math.sqrt((this.x * this.x) + (this.y * this.y));
    }
    
    public void print() {
        System.out.println("V2f: " + x + " | " + y);
    }
}
