package com.game.graphics;

import com.game.util.math.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class SpriteSheet {
    
    private Texture texture;
    private Sprite[][] sprites;
    private int width, height;
    
    public SpriteSheet(Texture texture, int spriteWidth, int spriteHeight) {
        this.width = texture.getWidth() / spriteWidth;
        this.height = texture.getHeight() / spriteHeight;
        this.sprites = new Sprite[this.height][this.width];
        
        this.texture = texture;
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                Vector2f texCoords = new Vector2f(x * spriteWidth, y * spriteHeight);
                Sprite sprite = new Sprite();
                sprite.setTexture(texture);
                sprite.setTexCoords(texCoords);
                sprite.setWidth(spriteWidth);
                sprite.setHeight(spriteHeight);
                sprites[y][x] = sprite;
            }
        }
    }
    
    public Sprite[] getSprites(int index) {
        return this.sprites[index];
    }
    
    public Sprite getSprite(int y, int x) {
        if (this.width > x && this.height > y) {
            return this.sprites[y][x];
        }
        return this.sprites[0][0];
    }
    
    public Sprite getSprite(Vector2f pos) {
        return this.sprites[(int)pos.y][(int)pos.x];
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getWidth() {
        return this.width;
    }
}
