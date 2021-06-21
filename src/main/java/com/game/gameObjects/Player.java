package com.game.gameObjects;

import com.game.graphics.Sprite;
import com.game.util.KeyHandler;
import com.game.util.MouseHandler;
import com.game.util.RenderedImage;
import com.game.util.math.Vector2f;

public class Player extends Entity {
    Vector2f start;
    float jumpHeight = 20.0f;
    
    public Player(Vector2f pos, String spritePath) {
        super(pos, spritePath);
        start = new Vector2f(pos);
        animation.setDelay(100);
        acc = 0.1f;
        deacc = 0.1f;
        maxSpeed = 0.01f;
    }
    
    private void move(float dt) {
        float dtSec = dt / 1000000000;
    
        if (falling) {
            dy += deacc * dtSec;
            if (start.y - pos.y <= 0) {
                dy = 0;
                falling = false;
                jumping = false;
            }
        }else if (jumping) {
            if (start.y - pos.y >= jumpHeight) {
                falling = true;
            } else{
                dy -= acc * dtSec * 2;
                if (-dy > maxSpeed * 2) {
                    dy = -maxSpeed * 2;
                }
            }
        } else {
            dy = 0.0f;
        }
        if (right) {
            dx += acc * dtSec;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        } else {
            if (dx > 0) {
                dx -= deacc * dtSec;
                if (dx < 0) {
                    dx = 0;
                }
            }
        }
        if (left) {
            dx -= acc * dtSec;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else {
            if (dx < 0) {
                dx += deacc * dtSec;
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
        move(dt); // dt in sec
        animation.update(dt);
        pos.add(new Vector2f(dx, dy).mul(dt / 100000));
    }
    
    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        right = key.right.down;
        left = key.left.down;
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
