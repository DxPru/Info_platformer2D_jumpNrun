/*
    This class handles the different States off the Game (Start, Play, Pause, Menu, etc)
    the List Scenes contains the currently loaded Scenes the displayed scene is also referred to in the currentState Var
 */

package com.game.states;

import com.game.util.Camera;
import com.game.util.KeyHandler;
import com.game.util.MouseHandler;
import com.game.util.Settings;
import com.game.util.data.DataManager;

import java.awt.*;
import java.util.Stack;

public class GameManager {
    private static Stack<GameState> states;
    private static GameManager instance;
    private DataManager data;
    private String loginname = "";
    private String displayname = "Username";
    private int highScore = 0;
    private int score = 0;
    
    public GameManager() {
        states = new Stack<GameState>();
        instance = this;
        
        data = new DataManager();
        
        states.add(new PlayState(this)); // loading PlayState in preparation
        states.add(new MenuState(this)); // preloading Menustate
        states.add(new StartState(this)); // Starting welcome screen
    }
    
    public static GameManager getInstance() {
        return instance;
    }
    
    public void remove(int state) {
        states.get(state).save();
        states.remove(state);
    }
    
    public void pop() {
        states.lastElement().save();
        states.pop();
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
        states.pop();
        add(state);
    }
    
    public void reset() {
        for (GameState state : states) {
            state.reset();
        }
    }
    
    public void update(float dt) {
        if (dt >= 0) {
            Camera.updateProjection();
            states.lastElement().update(dt);
        }
    }
    
    public void input(MouseHandler mouse, KeyHandler key) {
        states.lastElement().input(mouse, key);
    }
    
    public void render(Graphics2D g) {
        states.lastElement().render(g);
    }
    
    public GameState getGameState(int index) {
        return  states.get(index);
    }
    
    public DataManager getData() {
        return data;
    }
    
    public void setLoginname(String name) {
        loginname = name;
        data.insertPlayer(name);
        highScore = data.getHighScore(name);
        reset();
    }
    
    public void setScore(int score) {
        this.score = score;
        if (score > data.getHighScore(loginname)) {
            highScore = score;
            data.setHighScore(loginname, score);
        }
    }
    
    public void save() {
        for (GameState state : states) {
            state.save();
        }
        data.save();
    }
    
    public int getHighScore() {
        return highScore;
    }
    
    public int getScore() {
        return  score;
    }
    
    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }
    
    public String getDisplayname() {
        return displayname;
    }
    
    public String getLoginname() {
        return loginname;
    }
    
    public boolean isNameValid() {
        return data.isValid(loginname);
    }
}
