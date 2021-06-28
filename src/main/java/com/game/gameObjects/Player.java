package com.game.gameObjects;

import com.game.graphics.Sprite;
import com.game.states.GameManager;
import com.game.states.PlayState;
import com.game.util.*;
import com.game.util.math.Vector2f;
import com.game.util.physics.Rect;

import java.util.Stack;

public class Player extends Entity {
    private Vector2f start;
    private final float floor_height = Settings.FLOOR_HEIGHT;
    private boolean high_hop = true;
    
    public Player(Vector2f pos, String spritePath) {
        super(pos, spritePath);
        start = new Vector2f(pos);
        animation.setDelay(100);
        acc = 0.075f;
        accFall = 0.1f;
        accJump = 0.15f;
        deacc = 0.15f;
        maxSpeed = 0.0125f;
        maxFallSpeed = 0.02f;
        maxJumpSpeed = 0.02f;
        jumpHeight = 14f;
    }
    
    private void move(float dt) {
        float dtSec = (dt / 1000000000);
        float scoreMult = (float) PlayState.getScore() / 2000f;
        float scoreSpeedMult = scoreMult / 10000f;
        float flyMult = 0.25f;
    
        if (jumping) {
            if ((start.y - pos.y >= jumpHeight && high_hop) || (start.y - pos.y >= jumpHeight / 2 && !high_hop)) {
                jumping = false;
                landing = true;
            } else {
                dy -= accJump * dtSec;
                if (-dy > maxJumpSpeed) {
                    dy = -maxJumpSpeed;
                }
            }
        } else {
            dy += accFall * dtSec;
            if (dy > maxFallSpeed) {
                dy = maxFallSpeed;
            }
        }
        if (!falling) {
            if (right) {
                dx += (acc + scoreMult) * dtSec;
                if (dx > (maxSpeed + scoreSpeedMult)) {
                    dx = (maxSpeed + scoreSpeedMult);
                }
            } else {
                if (dx > 0) {
                    dx -= (deacc + scoreMult) * dtSec;
                    if (dx < 0) {
                        dx = 0;
                    }
                }
            }
            if (left) {
                dx -= (acc + scoreMult) * dtSec;
                if (dx < -(maxSpeed + scoreSpeedMult)) {
                    dx = -(maxSpeed + scoreSpeedMult);
                }
            } else {
                if (dx < 0) {
                    dx += (deacc + scoreMult) * dtSec;
                    if (dx > 0) {
                        dx = 0;
                    }
                }
            }
        } else {
            if (right) {
                dx += flyMult * (acc + scoreMult) * dtSec;
                if (dx > (maxSpeed + scoreSpeedMult)) {
                    dx = (maxSpeed + scoreSpeedMult);
                }
            } else {
                if (dx > 0) {
                    dx -= flyMult * (deacc + scoreMult) * dtSec;
                    if (dx < 0) {
                        dx = 0;
                    }
                }
            }
            if (left) {
                dx -= flyMult * (acc + scoreMult) * dtSec;
                if (dx < -(maxSpeed + scoreSpeedMult)) {
                    dx = -(maxSpeed + scoreSpeedMult);
                }
            } else {
                if (dx < 0) {
                    dx += flyMult * (deacc + scoreMult) * dtSec;
                    if (dx > 0) {
                        dx = 0;
                    }
                }
            }
        }
    }
    
    private void collision() {
    
        if (collision.CollYPos(floor_height)) {
            if (dy >= 0.0f) {
                dy = 0.0f;
                pos.y = floor_height - collision.rect.getSize().y;
                collision.rect.getPos().y = pos.y;
            }
            falling = false;
        } else {
            falling = true;
        }
        if (!collision.CollRect(Camera.getRect())) {
            GameManager.getInstance().add(Settings.GAMEOVER);
            return;
        }
        
        Stack<Rect> rects = PlayState.getTilemanager().getRects();
        int count = 0;
        
        for (Rect rect : rects) {
            if (collision.CollRect(rect)) {
                switch (collision.CollDir(rect)) {
                    case xPos:
                        pos.x = rect.getPos().x - collision.rect.getSize().x;
                        collision.rect.getPos().x = pos.x;
                        if (dx > 0) {
                            dx *= -0.3; // Bounce factor
                        }
                        // System.out.println("XPOS");
                        break;
                    case xNeg:
                        pos.x = rect.getPos().x + rect.getSize().x;
                        collision.rect.getPos().x = pos.x;
                        if (dx < 0) {
                            dx *= -0.3; // Bounce factor
                        }
                        // System.out.println("XNEG");
                        break;
                    case yPos:
                        pos.y = rect.getPos().y + rect.getSize().y;
                        collision.rect.getPos().y = pos.y;
                        if (dy < 0) {
                            dy = 0;
                        }
                        jumping = false;
                        landing = true;
                        //System.out.println("YPOS");
                        break;
                    case yNeg:
                        pos.y = rect.getPos().y - collision.rect.getSize().y;
                        collision.rect.getPos().y = pos.y;
                        if (dy > 0) {
                            dy = 0;
                        }
                        falling = false;
                        // System.out.println("YNEG");
                        break;
                    case noColl:
                        System.out.println("NOCOLL");
                        break;
                }
                
                count++;
            }
            if (count >= 9) {
                break;
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
            } else if (left) {
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
            } else if (left) {
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
        if (key.space.down && !falling && !landing) {
            if (!jumping) {
                start = new Vector2f(pos);
                jumping = true;
            }
            high_hop = true;
        } else if (!key.space.down) {
            high_hop = false;
            landing = false;
        }
    }
    
    @Override
    public RenderedImage getRenderedImage() {
        Sprite sprite = animation.getSprite();
        return new RenderedImage(sprite.getImg(), pos, sprite.getSize());
    }
}
