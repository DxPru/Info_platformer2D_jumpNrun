package com.game.gameObjects;

import com.game.states.GameManager;
import com.game.util.KeyHandler;
import com.game.util.MouseHandler;
import com.game.util.math.Vector2f;

public class LoginButton extends Button{
    private String name;
    
    public LoginButton(Vector2f pos, String spritePath, int spritePos, GameManager gameManager) {
        super(pos, spritePath, spritePos, gameManager);
        done = true;
    }
    
    @Override
    public void action() {
        if (done) {
            done = false;
        } else {
            if (!name.equals("")) {
                gameManager.setLoginname(name);
                done = true;
            }
        }
    }
    
    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        super.input(mouse, key);
        if (focused && !done) {
            key.setTyping(true);
            name = key.getString();
            if (!name.equals("")) {
                gameManager.setDisplayname(name);
            } else {
                gameManager.setDisplayname("Username");
            }
        } else {
            key.setTyping(false);
        }
    }
}
