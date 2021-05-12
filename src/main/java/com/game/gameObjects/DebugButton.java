package com.game.gameObjects;

import com.game.states.GameManager;
import com.game.util.math.Vector2f;

public class DebugButton extends Button {
    public DebugButton(Vector2f pos, String spritePath, int spriteYPos, GameManager gameManager) {
        super(pos, spritePath, spriteYPos, gameManager);
    }
    
    @Override
    public void action() {
        System.out.println("Debug Button is Pressed");
    }
}
