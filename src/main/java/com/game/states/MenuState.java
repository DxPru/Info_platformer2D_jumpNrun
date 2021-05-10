package com.game.states;

import com.game.util.KeyHandler;
import com.game.util.MouseHandler;
import com.game.views.MenuView;
import com.game.views.View;

import java.awt.*;

public class MenuState extends GameState {
    private GameManager gamemanager;
    private View renderer;
    
    public MenuState(GameManager gamemanager) {
        super(gamemanager);
        this.gamemanager = gamemanager;
        renderer = new MenuView();
    }
    
    @Override
    public void update(float dt) {
    }
    
    @Override
    public void input(MouseHandler mouse, KeyHandler keyboard) {
        if (keyboard.escape.down) {
            System.out.println("Closing");
            gamemanager.gui.close();
        }
    }
    
    @Override
    public void render(Graphics2D g) {
        renderer.render(g, 0);
    }
}
