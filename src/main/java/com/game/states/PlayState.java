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
    private String bgPath, playerPath, overlayPath, fontPath, colorname;
    private float dx = 0.0f;
    private float speed = 0.04f;
    
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
    public void save() {
        gamemanager.setScore((int) score);
        System.out.println("Saved score");
    }
    
    @Override
    protected void init() {
        playerPath = "res/spritesheets/Player.png";
        bgPath = "res/spritesheets/Background.png";
        overlayPath = "res/spritesheets/overlay.png";
        fontPath = "res/fonts/Minecraft.ttf";
        colorname = "lightbrown";
        AssetPool.addSpriteSheet(bgPath, new SpriteSheet(AssetPool.getTexture(bgPath), 256, 146));
        AssetPool.addSpriteSheet(playerPath, new SpriteSheet(AssetPool.getTexture(playerPath), 14, 14));
        AssetPool.getTexture(overlayPath);
        AssetPool.addFont(fontPath, Font.TRUETYPE_FONT, 6f * Settings.SCALE);
        AssetPool.addColor(colorname, new Color(214, 157, 100));
        Camera.setPosition(0, 0);
        score = 0;
        speed = 0.04f;
        gameObjects = new ArrayList<GameObject>();
        background = new Background(bgPath);
        background.addBg();
        background.addBg();
        tilemanager = new TileManager("res/tilemaps/Block.xml", "res/spritesheets/Block.png");
        gameObjects.add(new Player(new Vector2f(48f, 14f), playerPath));
    }
    
    @Override
    public void reset() {
        save();
        Camera.setPosition(0, 0);
        score = 0;
        speed = 0.04f;
        Player player = new Player(new Vector2f(48f, 14f), playerPath);
        if (gamemanager.getLoginname().equals("DxPru")) {
            player.setCheat(true);
        } else if (gamemanager.getLoginname().equals("Gape Horn")) {
            score = 50f;
            player.setCheat(true);
        } else if (gamemanager.getLoginname().equals("gggga")) {
            score = 100f;
            player.setCheat(true);
        } else if (gamemanager.getLoginname().equals("soren")) {
            speed += 50f / 1500f;
        } else if (gamemanager.getLoginname().equals("8=====D")) {
            speed += 100f / 1500f;
        }
        dx = 0.f;
        gameObjects = new ArrayList<GameObject>();
        background = new Background(bgPath);
        background.addBg();
        background.addBg();
        tilemanager.reset();
        gameObjects.add(player);
        System.out.println("Reset");
    }
    
    @Override
    public void update(float dt) {
        score += (dt / 1000000) * 0.00025;
        if (dx < speed + (score / 1500)) {
            dx += 0.00001f * (dt / 1000000);
        }
        Camera.movePosition(dx * (dt / 1000000), 0.0f);
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
        renderedImages.addAll(background.getRenderedImages());
        renderedImages.addAll(tilemanager.getRenderedImages());
        for (GameObject gameObject : gameObjects) {
            renderedImages.add(gameObject.getRenderedImage());
        }
        renderedImages.add(new RenderedImage(AssetPool.getTexture(overlayPath).getImg(), new Vector2f(), AssetPool.getTexture(overlayPath).getSize(), true));
    
        renderer.render(g, renderedImages);
        g.setColor(AssetPool.getColor(colorname));
        g.setFont(AssetPool.getFont(fontPath));
        g.drawString("Score: " + (int) score, (int) (36 * Settings.SCALE), (int) (9 * Settings.SCALE));
    }
}
