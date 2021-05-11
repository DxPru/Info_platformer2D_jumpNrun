package com.game.gameObjects;

import com.game.Gui;
import com.game.util.math.Vector2f;

public class ExitButton extends Button{
    public ExitButton(Vector2f pos, String spritePath, int spritePos) {
        super(pos, spritePath, spritePos);
    }
    
    @Override
    public void action() {
        Gui.close();
    }
}
