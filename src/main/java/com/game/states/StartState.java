package com.game.states;

import com.game.util.KeyHandler;
import com.game.util.MouseHandler;
import com.game.util.Settings;

import java.awt.*;

public class StartState extends GameState {
    public StartState(GameManager gameManager) {
    }
    
    @Override
    public void update(float dt) {
    
    }
    
    @Override
    public void input(MouseHandler mouse, KeyHandler keyboard) {
    
    }
    
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.black);
        g.drawString("Hello Start Scene", Settings.WIDTH / 2, Settings.HEIGHT / 2);
    }
}
