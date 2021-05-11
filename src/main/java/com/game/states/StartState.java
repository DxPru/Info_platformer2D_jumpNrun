package com.game.states;

import com.game.util.KeyHandler;
import com.game.util.MouseHandler;
import com.game.util.Settings;
import com.game.views.StartView;
import com.game.views.View;

import java.awt.*;
import java.util.ArrayList;

public class StartState extends GameState {
    private View renderer;
    private GameManager gamemanager;
    private int timeLeft = 1000;
    private int frame = 0;
    
    public StartState(GameManager gamemanager) {
        super(gamemanager);
        this.gamemanager = gamemanager;
        renderer = new StartView();
    }
    
    @Override
    public void update(float dt) {
        if ((int) dt >= 0) {
            timeLeft -= (int) dt;
        }
        if (timeLeft <= 0 && frame < 3) {
            frame++;
            timeLeft = 600;
        } else if (frame >= 3 && timeLeft <= 0) {
            gamemanager.addAndPop(Settings.MENU);
        }
    }
    
    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
    
    }
    
    @Override
    public void render(Graphics2D g) {
        renderer.render(g, frame, new ArrayList<>());
    }
}
