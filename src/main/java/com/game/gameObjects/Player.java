package com.game.gameObjects;

import com.game.graphics.Sprite;
import com.game.graphics.SpriteSheet;
import com.game.util.AssetPool;
import com.game.util.KeyHandler;
import com.game.util.MouseHandler;
import com.game.util.RenderedImage;
import com.game.util.math.Vector2f;

public class Player extends Entity{

    public Player(Vector2f pos, String spritePath) {
        super(pos, spritePath);
    }
    
    @Override
    public void update(float dt) {
    
    }
    
    @Override
    public void input(MouseHandler mouse, KeyHandler keyboard) {
    
    }
    
    @Override
    public RenderedImage getRenderedImage() {
        Sprite sprite = AssetPool.getSpriteSheet(spritePath).getSprite(0,0);
        return new RenderedImage(sprite.getImg(), new Vector2f(), sprite.getSize());
    }
}
