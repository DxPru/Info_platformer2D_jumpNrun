package com.game.gameObjects;

import com.game.graphics.Sprite;
import com.game.util.KeyHandler;
import com.game.util.MouseHandler;
import com.game.util.RenderedImage;
import com.game.util.math.Vector2f;

public class Player extends Entity {
    
    public Player(Vector2f pos, String spritePath) {
        super(pos, spritePath);
        animation.setDelay(100);
        acc = 0.01f;
        maxSpeed = 0.1f;
    }
    
    private void move(float dt) {
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
    
    private void setDir() {
        int dir = animation.getDir();
        
        if (!jumping) {
            if (right) {
                if (dir != RIGHT) {
                    animation.setDir(RIGHT);
                }
            } else if (left ) {
                if (dir != LEFT) {
                    animation.setDir(LEFT);
                }
            } else {
                if (dir != IDLE) {
                    animation.setDir(IDLE);
                }
            }
        } else {
            if (right) {
                if (dir != RIGHT + JUMP) {
                    animation.setDir(RIGHT + JUMP);
                }
            } else if (left ) {
                if (dir != LEFT + JUMP) {
                    animation.setDir(LEFT + JUMP);
                }
            } else {
                if (dir != IDLE + JUMP) {
                    animation.setDir(IDLE + JUMP);
                }
            }
        }
    }
    
    @Override
    public void update(float dt) {
        setDir();
        move(dt);
        animation.update(dt);
        pos.add(new Vector2f(dx, dy).mul(dt));
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
