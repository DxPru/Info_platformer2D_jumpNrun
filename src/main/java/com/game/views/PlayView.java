package com.game.views;

import com.game.util.RenderedImage;

import java.awt.*;
import java.util.ArrayList;

public class PlayView extends View {
    @Override
    public void render(Graphics2D g, ArrayList<RenderedImage> renderedImages) {
        renderer.render(g, renderedImages);
    }
}
