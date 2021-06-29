package com.game.states;

import com.game.graphics.Animation;
import com.game.graphics.SpriteSheet;
import com.game.util.AssetPool;
import com.game.util.KeyHandler;
import com.game.util.MouseHandler;
import com.game.util.RenderedImage;
import com.game.util.math.Vector2f;
import com.game.views.StartView;

import java.awt.*;
import java.util.ArrayList;

public class StartState extends GameState {
    private Animation animation;
    private boolean loaded = false;
    
    public StartState(GameManager gamemanager) {
        super(gamemanager);
        renderer = new StartView();
        init();
    }
    
    @Override
    protected void init() {
        String filepath = "res/spritesheets/LoadingScreen.png";
        AssetPool.addSpriteSheet(filepath, new SpriteSheet(AssetPool.getTexture(filepath), 256, 144));
        animation = new Animation(filepath, 1);
        animation.setDelay(600);
    }
    
    @Override
    public void update(float dt) {
        animation.update(dt);
        if (animation.hasPlayed()) {
            gamemanager.pop();
        }
        if (!loaded && dt != 0.0f) {
            gamemanager.getData().load();
            loaded = true;
        }
    }
    
    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
    
    }
    
    @Override
    public void render(Graphics2D g) {
        ArrayList<RenderedImage> ri = new ArrayList<RenderedImage>();
        ri.add(0, new RenderedImage(animation.getSprite().getImg(), new Vector2f(), animation.getSprite().getSize()));
        renderer.render(g, ri);
    }
}
