package com.game.views;

import com.game.graphics.SpriteSheet;
import com.game.util.AssetPool;
import com.game.util.Settings;

import java.awt.*;

public class StartView extends View {
    
    public StartView() {
        AssetPool.addSpriteSheet("res/images/LoadingScreen.png", new SpriteSheet(AssetPool.getTexture("res/images/LoadingScreen.png"), 256, 144, 4, 0));
    }
    
    @Override
    public void render(Graphics2D g, int frame) {
        g.drawImage(AssetPool.getSpriteSheet("res/images/LoadingScreen.png").getSprite(frame).getImg(), 0,0,Settings.WIDTH, Settings.HEIGHT, null);
    }
}
