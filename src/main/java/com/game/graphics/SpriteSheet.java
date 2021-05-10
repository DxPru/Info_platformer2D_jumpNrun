package com.game.graphics;

import com.game.util.math.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class SpriteSheet {
    
    private Texture texture;
    private List<Sprite> sprites;
    
    public SpriteSheet(Texture texture, int spriteWidth, int spriteHeight, int numSprites, int spacing) {
        this.sprites = new ArrayList<Sprite>();
        
        this.texture = texture;
        int currentX = 0;
        int currentY = 0;
        for (int i = 0; i < numSprites; i++) {
            Vector2f texCoords = new Vector2f(currentX, currentY);
            Sprite sprite = new Sprite();
            sprite.setTexture(texture);
            sprite.setTexCoords(texCoords);
            sprite.setWidth(spriteWidth);
            sprite.setHeight(spriteHeight);
            sprites.add(sprite);
            
            currentX += spriteWidth + spacing;
            if (currentX >= texture.getWidth()) {
                currentX = 0;
                currentY += spriteHeight + spacing;
            }
        }
    }
    
    public Sprite getSprite(int index) {
        return this.sprites.get(index);
    }
    
    public int getSize() {
        return this.sprites.size();
    }
}
