package com.game.gameObjects;

import com.game.graphics.Sprite;
import com.game.util.AssetPool;
import com.game.util.KeyHandler;
import com.game.util.MouseHandler;
import com.game.util.RenderedImage;
import com.game.util.math.Vector2f;

public class Player extends Entity {
    
    public Player(Vector2f pos, String spritePath) {
        super(pos, spritePath);
        acc = 2.0f;
        maxSpeed = 3.0f;
    }
    
    public void move(float dt) {
        if (jumping) {
            // TODO initialize jump
        }
        if (falling) {
            // TODO falling down
        }
        if (landing) {
            // TODO landing animation
        }
        if (right) {
            dx += acc;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        } else {
            if (dx > 0) {
                dx -= deacc;
                if (dx < 0) {
                    dx = 0;
                }
            }
        }
        if (left) {
            dx -= acc;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else {
            if (dx < 0) {
                dx += deacc;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }
    }
    
    @Override
    public void update(float dt) {
        move(dt);
        animation.update(dt);
        pos.add(new Vector2f(dx, dy));
    }
    
    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        if (key.right.down) {
            right = true;
        } else {
            right = false;
        }
        if (key.left.down) {
            left = true;
        } else {
            left = false;
        }
        if (key.space.down) {
            jumping = true;
        }
    }
    
    @Override
    public RenderedImage getRenderedImage() {
        Sprite sprite = animation.getSprite();
        return new RenderedImage(sprite.getImg(), pos, sprite.getSize());
    }
}
