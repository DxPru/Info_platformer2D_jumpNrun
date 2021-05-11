package com.game.views;

import com.game.graphics.SpriteSheet;
import com.game.util.AssetPool;
import com.game.util.RenderedImage;
import com.game.util.Settings;

import java.awt.*;
import java.util.ArrayList;

public class StartView extends View {
    
    public StartView() {
        AssetPool.addSpriteSheet("res/images/LoadingScreen.png", new SpriteSheet(AssetPool.getTexture("res/images/LoadingScreen.png"), 256, 144));
    }
    
    @Override
    public void render(Graphics2D g, int frame, ArrayList<RenderedImage> renderedImages) {
        g.drawImage(AssetPool.getSpriteSheet("res/images/LoadingScreen.png").getSprite(1, frame).getImg(), 0, 0, Settings.PX_WIDTH, Settings.PX_HEIGHT, null);
    }
}
