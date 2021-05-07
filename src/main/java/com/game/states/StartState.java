package com.game.states;

import com.game.util.KeyHandler;
import com.game.util.MouseHandler;
import com.game.views.StartView;

import java.awt.*;

public class StartState extends GameState {
    private StartView renderer;
    private GameManager gamemanager;
    
    public StartState(GameManager gamemanager) {
        this.gamemanager = gamemanager;
        renderer = new StartView();
    }
    
    @Override
    public void update(float dt) {
    
    }
    
    @Override
    public void input(MouseHandler mouse, KeyHandler keyboard) {
    
    }
    
    @Override
    public void render(Graphics2D g) {
        renderer.render(g);
    }
}
