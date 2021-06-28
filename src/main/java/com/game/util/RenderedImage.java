package com.game.util;

import com.game.util.math.Vector2f;

import java.awt.image.BufferedImage;

public class RenderedImage {
    private final BufferedImage img;
    private final Vector2f pos;
    private final Vector2f size;
    private boolean uiElement = false;
    
    public RenderedImage(BufferedImage img, Vector2f pos, Vector2f size) {
        this.img = img;
        this.pos = pos;
        this.size = size;
    }
    
    public RenderedImage(BufferedImage img, Vector2f pos, Vector2f size, boolean uiElement) {
        this.img = img;
        this.pos = pos;
        this.size = size;
        this.uiElement = uiElement;
    }
    
    public BufferedImage getImg() {
        return img;
    }
    
    public Vector2f getPos() {
        return pos;
    }
    
    public Vector2f getSize() {
        return size;
    }
    
    public boolean isUiElement() {
        return uiElement;
    }
}
