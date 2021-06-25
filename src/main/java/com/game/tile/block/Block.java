package com.game.tile.block;

import com.game.graphics.Sprite;
import com.game.util.Camera;
import com.game.util.RenderedImage;
import com.game.util.math.Vector2f;
import com.game.util.physics.Rect;

public class Block {
    public Rect rect;
    private final Vector2f pos;
    private final Sprite sprite;
    private boolean del = false;
    
    public Block(Vector2f pos, Sprite sprite) {
        this.pos = new Vector2f(pos);
        this.sprite = sprite;
        rect = new Rect(pos, sprite.getSize());
    }
    
    public void update(float dt) {
        if (Camera.getProjection()[0].x >= pos.x + sprite.getWidth()) {
            del = true;
        }
    }
    
    public Vector2f getPos() {
        return new Vector2f(pos);
    }
    
    public RenderedImage getRenderedImage() {
        return new RenderedImage(this.sprite.getImg(), this.pos, this.sprite.getSize());
    }
    
    public boolean getDel() {
        return del;
    }
}
