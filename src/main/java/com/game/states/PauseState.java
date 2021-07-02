package com.game.states;

import com.game.graphics.Sprite;
import com.game.graphics.SpriteSheet;
import com.game.util.*;
import com.game.util.math.Vector2f;
import com.game.views.PauseView;

import java.awt.*;
import java.util.ArrayList;

public class PauseState extends GameState {
    private String overlayPath;
    
    public PauseState(GameManager gamemanager) {
        super(gamemanager);
        renderer = new PauseView();
        init();
    }

    @Override
    protected void init() {
        overlayPath = "res/spritesheets/LoadingScreen.png";
        AssetPool.addSpriteSheet(overlayPath, new SpriteSheet(AssetPool.getTexture(overlayPath), 256, 144));
    }
    
    @Override
    public void update(float dt) {
    
    }
    
    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        if (key.escape.clicked) {
            gamemanager.addAndPop(Settings.MENU);
            gamemanager.reset();
        }
        if (key.space.clicked || key.right.clicked || key.left.clicked || key.up.clicked || key.down.clicked || key.enter.clicked) {
            gamemanager.pop();
        }
    }
    
    @Override
    public void render(Graphics2D g) {
        gamemanager.getGameState(0).render(g);
        
        ArrayList<RenderedImage> tmp = new ArrayList<RenderedImage>();
        
        Sprite sprite = AssetPool.getSpriteSheet(overlayPath).getSprite(0, 1);
        tmp.add(new RenderedImage(sprite.getImg(), new Vector2f(), sprite.getSize(), true));
        
        renderer.render(g, tmp);
    }
}
