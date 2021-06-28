package com.game.gameObjects;

import com.game.graphics.Animation;
import com.game.util.math.Vector2f;
import com.game.util.physics.Collision;
import com.game.util.physics.Rect;

public abstract class Entity extends GameObject {
    protected static final int IDLE = 0;
    protected static final int RIGHT = 1;
    protected static final int LEFT = 2;
    protected static final int JUMP = 3;
    
    protected boolean right = false;
    protected boolean left = false;
    protected boolean falling = false;
    protected boolean jumping = false;
    protected boolean landing = false;
    
    protected float dx;
    protected float dy;
    
    protected float maxSpeed = 4f;
    protected float jumpHeight = 20.0f;
    protected float maxFallSpeed = 4.0f;
    protected float maxJumpSpeed = 8.0f;
    protected float accFall = 3.0f;
    protected float accJump = 2.0f;
    protected float acc = 3.0f;
    protected float deacc = 0.75f;
    
    protected Animation animation;
    protected Collision collision;
    
    public Entity(Vector2f pos, String spritePath) {
        super(pos, spritePath);
        animation = new Animation(spritePath, IDLE);
        collision = new Collision(new Rect(pos, animation.getSprite().getSize()));
    }
}
