package com.game.states;

import com.game.gameObjects.GameObject;
import com.game.gameObjects.Player;
import com.game.graphics.SpriteSheet;
import com.game.tile.Background;
import com.game.tile.TileManager;
import com.game.util.*;
import com.game.util.math.Vector2f;
import com.game.views.PlayView;

import java.awt.*;
import java.util.ArrayList;

public class PlayState extends GameState {
    private static TileManager tilemanager;
    private static float score = 0;
    private ArrayList<GameObject> gameObjects;
    private Background background;
    
    public PlayState(GameManager gamemanager) {
        super(gamemanager);
        renderer = new PlayView();
        init();
    }
    
    public static TileManager getTilemanager() {
        return tilemanager;
    }
    
    public static int getScore() {
        return (int) score;
    }
    
    @Override
    protected void init() {
        score = 0;
        gameObjects = new ArrayList<GameObject>();
        String bgPath = "res/spritesheets/Background.png";
        AssetPool.addSpriteSheet(bgPath, new SpriteSheet(AssetPool.getTexture(bgPath), 256, 146));
        background = new Background(bgPath);
        background.addBg();
        background.addBg();
        tilemanager = new TileManager("res/tilemaps/Block.xml", "res/spritesheets/Block.png");
        String playerPath = "res/spritesheets/Player.png";
        AssetPool.addSpriteSheet(playerPath, new SpriteSheet(AssetPool.getTexture(playerPath), 16, 16));
        gameObjects.add(new Player(new Vector2f(Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f), playerPath));
    }
    
    @Override
    public void update(float dt) {
        score += (dt / 1000000) * 0.001;
        Camera.movePosition((0.045f + (score / 1500)) * (dt / 1000000), 0.0f);
        background.update(dt);
        tilemanager.update(dt);
        for (GameObject gameObject : gameObjects) {
            gameObject.update(dt);
        }
    }
    
    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        if (key.escape.clicked) {
            gamemanager.add(Settings.PAUSE);
        }
        for (GameObject gameObject : gameObjects) {
            gameObject.input(mouse, key);
        }
    }
    
    @Override
    public void render(Graphics2D g) {
        ArrayList<RenderedImage> renderedImages = new ArrayList<RenderedImage>();
        renderedImages.addAll(background.getRenderedImage());
        renderedImages.addAll(tilemanager.getRenderedImage());
        for (GameObject gameObject : gameObjects) {
            renderedImages.add(gameObject.getRenderedImage());
        }
        renderer.render(g, renderedImages);
        g.drawString(Integer.toString((int) score), 40, 20);
    }
}
