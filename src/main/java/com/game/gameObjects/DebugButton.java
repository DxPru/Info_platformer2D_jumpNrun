package com.game.gameObjects;

import com.game.util.math.Vector2f;

public class DebugButton extends Button {
    public DebugButton(Vector2f pos, String spritePath, int spriteYPos) {
        super(pos, spritePath, spriteYPos);
    }
    
    @Override
    public void action() {
        System.out.println("Debug Button is Pressed");
    }
}
