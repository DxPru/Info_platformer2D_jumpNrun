package com.game.states;

import com.game.util.KeyHandler;
import com.game.util.MouseHandler;

import java.awt.*;

public abstract class GameState {

    public GameState(){

    }
    
    public abstract void update(float dt);
    
    public abstract void input(MouseHandler mouse, KeyHandler keyboard);
    
    public abstract void render(Graphics2D g);
}
