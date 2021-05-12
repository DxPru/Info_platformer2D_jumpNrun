package com.game.views;

import com.game.graphics.Renderer;
import com.game.util.RenderedImage;

import java.awt.*;
import java.util.ArrayList;

public abstract class View {
    protected Renderer renderer = new Renderer();
    
    public View() {
    }
    
    public abstract void render(Graphics2D g, ArrayList<RenderedImage> renderedImages);
}
