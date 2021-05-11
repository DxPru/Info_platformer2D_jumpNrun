package com.game.gameObjects;

import com.game.graphics.SpriteSheet;
import com.game.util.math.Vector2f;

public abstract class Entity extends GameObject{
    protected static final int IDLE = 0;
    protected static final int RIGHT = 1;
    protected static final int LEFT = 2;
    protected static final int JUMP = 3;
    protected int Currentframe;
    
    protected boolean right = false;
    protected boolean left = false;
    protected boolean falling = false;
    protected boolean jumping = false;
    protected boolean landing = false;
    
    public Entity(Vector2f pos, String spritePath) {
        super(pos, spritePath);
    }
}
