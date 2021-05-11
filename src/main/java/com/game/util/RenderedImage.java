package com.game.util;

import com.game.util.math.Vector2f;

import java.awt.image.BufferedImage;

public class RenderedImage {
    private BufferedImage img;
    private Vector2f pos;
    private Vector2f size;
    
    public RenderedImage(BufferedImage img, Vector2f pos, Vector2f size) {
        this.img = img;
        this.pos = pos;
        this.size = size;
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
}
