package com.game.views;

import com.game.util.AssetPool;
import com.game.util.RenderedImage;
import com.game.util.Settings;

import java.awt.*;
import java.util.ArrayList;

public class MenuView extends View{
    
    @Override
    public void render(Graphics2D g, int frame, ArrayList<RenderedImage> renderedImages) {
        g.drawImage(AssetPool.getSpriteSheet("res/images/LoadingScreen.png").getSprite(0,0).getImg(), 0, 0, Settings.PX_WIDTH, Settings.PX_HEIGHT, null);
        renderer.render(g, renderedImages);
    }
}
