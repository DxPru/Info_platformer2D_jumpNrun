package com.game.gameObjects;

import com.game.states.GameManager;
import com.game.util.math.Vector2f;

public class PlayButton extends Button {
    public PlayButton(Vector2f pos, String spritePath, int spritePos, GameManager gameManager) {
        super(pos, spritePath, spritePos, gameManager);
    }
    
    @Override
    public void action() {
        if (gameManager.isNameValid()) {
            gameManager.pop();
        }
    }
}
