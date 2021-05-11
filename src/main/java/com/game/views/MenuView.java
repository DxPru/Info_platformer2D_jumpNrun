package com.game.views;

import com.game.util.AssetPool;
import com.game.util.RenderedImage;
import com.game.util.Settings;

import java.awt.*;
import java.util.ArrayList;

public class MenuView extends View{
    
    @Override
    public void render(Graphics2D g, int frame, ArrayList<RenderedImage> renderedImages) {
        g.drawImage(AssetPool.getTexture("res/images/Info_Jump'n'Run.png").getImg(), 0, 0, Settings.PX_WIDTH, Settings.PX_HEIGHT, null);
        renderer.render(g, renderedImages);
        g.setColor(Color.lightGray);
        g.setFont(new Font("Hack", Font.PLAIN, 24));
        g.drawString("Exit with ESC (Voruebergehend)", Settings.PX_WIDTH - 550 , Settings.PX_HEIGHT - 375);
    }
}
