package com.game.tile.block;

import com.game.graphics.Sprite;
import com.game.util.math.Vector2f;
import com.game.util.RenderedImage;

public class Block {
    private Vector2f pos;
    private Sprite sprite;
    
    public Block(Vector2f pos, Sprite sprite) {
        this.pos = new Vector2f(pos);
        this.sprite = sprite;
    }
    
    public void update(float dt) {
        // TODO update Block
    }
    
    public Vector2f getPos() {
        return new Vector2f(pos);
    }
    
    public RenderedImage getRenderedImage() {
        return new RenderedImage(this.sprite.getImg(), this.pos, this.sprite.getSize());
    }
}
