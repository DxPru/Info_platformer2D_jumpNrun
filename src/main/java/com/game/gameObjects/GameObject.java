package com.game.gameObjects;

import com.game.graphics.SpriteSheet;
import com.game.util.AssetPool;
import com.game.util.KeyHandler;
import com.game.util.MouseHandler;
import com.game.util.RenderedImage;
import com.game.util.math.Vector2f;

public abstract class GameObject {
    protected String spritePath;
    protected Vector2f pos;
    protected boolean renderFlag = true;
    
    public GameObject(Vector2f pos, String spritePath) {
        this.pos = pos;
        this.spritePath = spritePath;
    }
    
    public abstract void update(float dt);
    
    public abstract void input(MouseHandler mouse, KeyHandler keyboard);
    
    public abstract RenderedImage getRenderedImage();
    
    public boolean getRenderFlag() {
        return renderFlag;
    }
    
    public SpriteSheet getSpriteSheet() {
        return AssetPool.getSpriteSheet(spritePath);
    }
    
    public Vector2f getPos() {
        return pos;
    }
}