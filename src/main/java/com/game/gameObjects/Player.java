package com.game.gameObjects;

import com.game.graphics.Sprite;
import com.game.states.GameManager;
import com.game.states.PlayState;
import com.game.util.*;
import com.game.util.math.Vector2f;

public class Player extends Entity {
    private Vector2f start;
    private float floor_height = Settings.FLOOR_HEIGHT;
    
    public Player(Vector2f pos, String spritePath) {
        super(pos, spritePath);
        start = new Vector2f(pos);
        animation.setDelay(100);
        acc = 0.1f;
        accFall = 0.2f;
        deacc = 0.1f;
        maxSpeed = 0.01f;
        maxFallSpeed = 0.02f;
    }
    
    private void move(float dt) {
        float dtSec = dt / 1000000000;
    
        if (jumping) {
            if (start.y - pos.y >= jumpHeight) {
                jumping = false;
            } else{
                dy -= accFall * dtSec;
                if (-dy > maxFallSpeed) {
                    dy = -maxFallSpeed;
                }
            }
        } else {
            dy += deacc * dtSec;
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
    
    private void collision() {
        if (collision.CollY(floor_height)) {
            if (dy >= 0.0f) {
                dy = 0.0f;
                pos.y = floor_height - collision.rect.getSize().y;
            }
            falling = false;
        } else {
            falling = true;
        }
        if (!collision.CollRect(Camera.getRect())) {
            GameManager.getInstance().add(Settings.GAMEOVER);
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
        pos.add(new Vector2f(dx, dy).mul(dt / 100000));
        collision.rect.setPos(pos);
        collision();
    }
    
    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        right = key.right.down;
        left = key.left.down;
        if (key.space.down && !falling) {
            jumping = true;
        }
    }
    
    @Override
    public RenderedImage getRenderedImage() {
        Sprite sprite = animation.getSprite();
        return new RenderedImage(sprite.getImg(), pos, sprite.getSize());
    }
}
