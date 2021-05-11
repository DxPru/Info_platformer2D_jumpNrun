package com.game.gameObjects;

import com.game.graphics.SpriteSheet;
import com.game.util.KeyHandler;
import com.game.util.MouseHandler;

public abstract class GameObject {
    protected SpriteSheet spriteSheet;
    
    public abstract void update(float dt);
    public abstract void input(MouseHandler mouse, KeyHandler keyboard);

}