package com.game.views;

import com.game.util.RenderedImage;
import com.game.util.Settings;

import java.awt.*;
import java.util.ArrayList;

public class MenuView extends View {
    
    @Override
    public void render(Graphics2D g, ArrayList<RenderedImage> renderedImages) {
        g.drawImage(renderedImages.get(0).getImg(), 0, 0, Settings.PX_WIDTH, Settings.PX_HEIGHT, null);
        renderer.renderUi(g, renderedImages);
    }
}
