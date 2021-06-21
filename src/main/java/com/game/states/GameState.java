package com.game.states;

import com.game.util.KeyHandler;
import com.game.util.MouseHandler;
import com.game.views.View;

import java.awt.*;

public abstract class GameState {
    protected GameManager gamemanager;
    protected View renderer;
    public GameState(GameManager gamemanager) {
        this.gamemanager = gamemanager;
    }
    
    public void reset() {
        this.init();
    }
    protected abstract void init();
    
    public abstract void update(float dt);
    
    public abstract void input(MouseHandler mouse, KeyHandler key);
    
    public abstract void render(Graphics2D g);
}
