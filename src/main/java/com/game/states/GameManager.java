/*
    This class handles the different States off the Game (Start, Play, Pause, Menu, etc)
    the List Scenes contains the currently loaded Scenes the displayed scene is also referred to in the currentState Var
 */

package com.game.states;

import com.game.util.Camera;
import com.game.util.KeyHandler;
import com.game.util.MouseHandler;
import com.game.util.Settings;

import java.awt.*;
import java.util.ArrayList;

public class GameManager {
    private static ArrayList<GameState> states;
    // TODO ArrayList zu Stack (only first one is rendered and updated)
    public GameManager() {
        states = new ArrayList<GameState>();
        
        states.add(new StartState(this)); // Starting welcome screen
        // states.add(new PlayState(this)); // loading PlayState in preparation
    }
    
    public void pop(int state) {
        states.remove(state);
    }
    
    public void add(int state) {
        switch (state) {
            case Settings.MENU:
                states.add(new MenuState(this));
                break;
            case Settings.PLAY:
                states.add(new PlayState(this));
                break;
            case Settings.PAUSE:
                states.add(new PauseState(this));
                break;
            case Settings.GAMEOVER:
                states.add(new GameOverState(this));
                break;
            case Settings.START:
                states.add(new StartState(this));
                break;
        }
    }
    
    public void addAndPop(int state) {
        states.remove(0);
        add(state);
    }
    
    public void update(float dt) {
        if (dt >= 0) {
            Camera.updateProjection();
            for (GameState state : states) {
                state.update(dt);
            }
        }
    }
    
    public void input(MouseHandler mouse, KeyHandler key) {
        for (GameState state : states) {
            state.input(mouse, key);
        }
    }
    
    public void render(Graphics2D g) {
        for (GameState state : states) {
            state.render(g);
        }
    }
}
