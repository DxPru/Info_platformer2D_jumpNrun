package com.game.gameObjects;

import com.game.Gui;
import com.game.states.GameManager;
import com.game.util.math.Vector2f;

public class ExitButton extends Button {
    public ExitButton(Vector2f pos, String spritePath, int spritePos, GameManager gameManager) {
        super(pos, spritePath, spritePos, gameManager);
    }
    
    @Override
    public void action() {
        gameManager.save();
        Gui.close();
    }
}
