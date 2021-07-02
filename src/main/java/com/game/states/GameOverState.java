package com.game.states;

import com.game.graphics.Sprite;
import com.game.graphics.SpriteSheet;
import com.game.util.*;
import com.game.util.math.Vector2f;
import com.game.views.GameOverView;

import java.awt.*;
import java.util.ArrayList;

public class GameOverState extends GameState {
    private int score;
    private int highscore;
    private String screenPath;
    private String fontPath;
    private String color;
    
    public GameOverState(GameManager gamemanager) {
        super(gamemanager);
        renderer = new GameOverView();
        init();
    }
    // TODO Import state
    @Override
    protected void init() {
        gamemanager.save();
        highscore = gamemanager.getData().getHighScore(gamemanager.getLoginname());
        score = gamemanager.getHighScore();
        screenPath = "res/spritesheets/LoadingScreen.png";
        fontPath = "res/fonts/minecraft.ttf";
        color = "lightbrown";
        AssetPool.addSpriteSheet(screenPath, new SpriteSheet(AssetPool.getTexture(screenPath), 256, 144));
        AssetPool.addFont(fontPath, Font.TRUETYPE_FONT, 36f);
        AssetPool.addColor(color, new Color(214, 157, 100));
    }
    
    @Override
    public void update(float dt) {
        highscore = gamemanager.getHighScore();
        score = gamemanager.getScore();
    }
    
    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        if (key.escape.clicked || key.enter.clicked || key.right.clicked || key.left.clicked || key.up.clicked || key.down.clicked || key.space.clicked) {
            gamemanager.addAndPop(Settings.MENU);
            gamemanager.reset();
        }
    }
    
    @Override
    public void render(Graphics2D g) {
        ArrayList<RenderedImage> tmp = new ArrayList<RenderedImage>();
        Sprite sprite = AssetPool.getSpriteSheet(screenPath).getSprite(new Vector2f(2, 0));
        tmp.add(new RenderedImage(sprite.getImg(), new Vector2f(), sprite.getSize(), true));
        gamemanager.getGameState(0).render(g);
        renderer.render(g, tmp);
        
        g.setFont(AssetPool.getFont(fontPath).deriveFont(64f));
        g.setColor(AssetPool.getColor(color));
        g.drawString("" + highscore, ((Settings.WIDTH / 2) + 24) * Settings.SCALE, ((Settings.HEIGHT / 2) + 30) * Settings.SCALE);
        g.setFont(AssetPool.getFont(fontPath).deriveFont(128f));
        g.drawString("" + score, (Settings.WIDTH / 2) * Settings.SCALE, ((Settings.HEIGHT / 2) + 16) * Settings.SCALE);
    }
}
