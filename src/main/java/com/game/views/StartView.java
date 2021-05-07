package com.game.views;

import com.game.util.Settings;

import java.awt.*;

public class StartView extends View {
    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(200, 200, Settings.WIDTH - 400, Settings.HEIGHT - 400);
    }
}
