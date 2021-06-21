package com.game.states;

import com.game.util.KeyHandler;
import com.game.util.MouseHandler;
import com.game.util.Settings;

import java.awt.*;

public class PauseState extends GameState {
    public PauseState(GameManager gamemanager) {
        super(gamemanager);
    }
    
    @Override
    protected void init() {
    
    }
    
    @Override
    public void update(float dt) {
    
    }
    
    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        if (key.escape.clicked) {
            gamemanager.addAndPop(Settings.MENU);
            gamemanager.reset();
        }
    }
    
    @Override
    public void render(Graphics2D g) {
    
    }
}
