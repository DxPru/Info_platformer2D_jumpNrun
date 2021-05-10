package com.game.views;

import com.game.util.AssetPool;
import com.game.util.Settings;

import java.awt.*;

public class MenuView extends View{
    
    @Override
    public void render(Graphics2D g, int frame) {
        g.drawImage(AssetPool.getTexture("res/images/Info_Jump'n'Run.png").getImg(), 0, 0, Settings.WIDTH, Settings.HEIGHT, null);
        g.setColor(Color.lightGray);
        g.setFont(new Font("Hack", Font.PLAIN, 24));
        g.drawString("Exit with ESC (Voruebergehend)", Settings.WIDTH - 500 , Settings.HEIGHT - 300);
    }
}
