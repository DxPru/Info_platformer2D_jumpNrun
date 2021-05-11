package com.game.gameObjects;

import com.game.graphics.Sprite;
import com.game.util.AssetPool;
import com.game.util.KeyHandler;
import com.game.util.MouseHandler;
import com.game.util.RenderedImage;
import com.game.util.math.Vector2f;


public abstract class Button extends GameObject {
    private int spriteYPos, spriteXPos;
    private boolean focused;
    
    public Button(Vector2f pos, String spritePath, int spritePos) {
        super(pos, spritePath);
        this.spriteYPos = spritePos;
        this.spriteXPos = 0;
    }
    
    @Override
    public void update(float dt) {
    
    }
    
    @Override
    public void input(MouseHandler mouse, KeyHandler keyboard) {
        if (focused && keyboard.enter.clicked) {
            this.action();
        }
    }
    
    @Override
    public RenderedImage getRenderedImage() {
        renderFlag = false;
        Sprite sprite = AssetPool.getSpriteSheet(spritePath).getSprite(spriteYPos, spriteXPos);
        return new RenderedImage(sprite.getImg(), pos, sprite.getSize());
    }
    
    public abstract void action();
    
    public boolean isFocused() {
        return focused;
    }
    
    public void setFocused(boolean focused) {
        this.focused = focused;
        if (focused) {
            spriteXPos = 1;
        } else {
            spriteXPos = 0;
        }
    }
}
