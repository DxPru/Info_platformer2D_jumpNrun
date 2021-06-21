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
    private ArrayList<GameObject> gameObjects;
    private TileManager tilemanager;
    private Background background;
    
    public PlayState(GameManager gamemanager) {
        super(gamemanager);
        renderer = new PlayView();
        init();
    }
    
    @Override
    protected void init() {
        gameObjects = new ArrayList<GameObject>();
        String bgPath = "res/spritesheets/Background.png";
        AssetPool.addSpriteSheet(bgPath, new SpriteSheet(AssetPool.getTexture(bgPath), 256, 146));
        background = new Background(bgPath);
        background.addBg();
        background.addBg();
        String tilePath = "res/spritesheets/Background.png";
        AssetPool.addSpriteSheet(tilePath, new SpriteSheet(AssetPool.getTexture(tilePath), 8, 8));
        tilemanager = new TileManager(tilePath);
        tilemanager.genTileMap();
        String playerPath = "res/spritesheets/Player.png";
        AssetPool.addSpriteSheet(playerPath, new SpriteSheet(AssetPool.getTexture(playerPath), 16, 32));
        gameObjects.add(new Player(new Vector2f(Settings.WIDTH / 2.0f, Settings.HEIGHT / 2.0f), playerPath));
    }
    
    @Override
    public void update(float dt) {
        Camera.movePosition(0.05f * (int) (dt / 1000000), 0.0f);
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
    }
}
