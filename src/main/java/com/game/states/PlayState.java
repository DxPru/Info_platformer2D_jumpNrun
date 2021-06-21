package com.game.states;

import com.game.Gui;
import com.game.gameObjects.GameObject;
import com.game.gameObjects.Player;
import com.game.graphics.SpriteSheet;
import com.game.util.*;
import com.game.util.math.Vector2f;
import com.game.views.PlayView;

import java.awt.*;
import java.util.ArrayList;

public class PlayState extends GameState {
    private ArrayList<GameObject> gameObjects;
    
    public PlayState(GameManager gamemanager) {
        super(gamemanager);
        gameObjects = new ArrayList<GameObject>();
        renderer = new PlayView();
        init();
    }
    
    public void init() {
        String playerPath = "res/spritesheets/Player.png";
        AssetPool.addSpriteSheet(playerPath, new SpriteSheet(AssetPool.getTexture(playerPath),16,32));
        gameObjects.add(new Player(new Vector2f(Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f), playerPath));
    }
    
    @Override
    public void update(float dt) {
        Camera.movePosition(0.05f * (int) (dt / 1000000), 0.0f);
        for (GameObject gameObject : gameObjects) {
            gameObject.update(dt);
        }
    }
    
    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        if (key.escape.clicked) {
            gamemanager.addAndPop(Settings.MENU);
        }
        for (GameObject gameObject : gameObjects) {
            gameObject.input(mouse, key);
        }
    }
    
    @Override
    public void render(Graphics2D g) {
        ArrayList<RenderedImage> renderedImages = new ArrayList<RenderedImage>();
        for (GameObject gameObject : gameObjects) {
            renderedImages.add(gameObject.getRenderedImage());
        }
        renderer.render(g, renderedImages);
    }
}
