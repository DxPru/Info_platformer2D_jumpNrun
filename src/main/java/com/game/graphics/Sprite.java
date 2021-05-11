package com.game.graphics;

import com.game.util.math.Vector2f;

import java.awt.image.BufferedImage;

public class Sprite {
    
    private float width, height;
    private Texture texture = null;
    private Vector2f texCoords = new Vector2f();
    private BufferedImage img;
    
    public Texture getTexture() {
        return texture;
    }
    
    public void setTexture(Texture texture) {
        this.texture = texture;
        this.genImage();
    }
    
    public float getWidth() {
        return width;
    }
    
    public void setWidth(float width) {
        this.width = width;
        this.genImage();
    }
    
    public float getHeight() {
        return height;
    }
    
    public void setHeight(float height) {
        this.height = height;
        this.genImage();
    }
    
    public Vector2f getTexCoords() {
        return texCoords;
    }
    
    public void setTexCoords(Vector2f texCoords) {
        this.texCoords = texCoords;
        this.genImage();
    }
    
    private void genImage() {
        if (width != 0 && height != 0 && texture != null) {
            // System.out.println(texCoords.x + " | " + texCoords.y + " | " + width + " | " + height);
            this.img = texture.getImg().getSubimage((int) this.texCoords.x, (int) this.texCoords.y, (int) this.width, (int) this.height);
        }
    }
    
    public BufferedImage getImg() {
        return this.img;
    }
    
    public Vector2f getSize() {
        return new Vector2f(width, height);
    }
}
