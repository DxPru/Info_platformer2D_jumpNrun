package com.game.states;

import com.game.graphics.Animation;
import com.game.graphics.SpriteSheet;
import com.game.util.*;
import com.game.util.math.Vector2f;
import com.game.views.StartView;

import java.awt.*;
import java.util.ArrayList;

public class StartState extends GameState {
    private Animation animation;
    
    public StartState(GameManager gamemanager) {
        super(gamemanager);
        renderer = new StartView();
        init();
    }
    
    public void init() {
        String filepath = "res/spritesheets/LoadingScreen.png";
        AssetPool.addSpriteSheet(filepath, new SpriteSheet(AssetPool.getTexture(filepath),256, 144));
        animation = new Animation(filepath,1);
        animation.setDelay(600);
    }
    
    @Override
    public void update(float dt) {
        animation.update(dt);
        if (animation.hasPlayed()) {
            gamemanager.addAndPop(Settings.MENU);
        }
    }
    
    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
    
    }
    
    @Override
    public void render(Graphics2D g) {
        ArrayList<RenderedImage> ri = new ArrayList<RenderedImage>();
        ri.add(0,new RenderedImage(animation.getSprite().getImg(), new Vector2f(), animation.getSprite().getSize()));
        renderer.render(g, ri);
    }
}
