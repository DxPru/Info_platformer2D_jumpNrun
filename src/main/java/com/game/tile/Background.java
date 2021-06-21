package com.game.tile;

import com.game.graphics.Sprite;
import com.game.graphics.SpriteSheet;
import com.game.util.AssetPool;
import com.game.util.RenderedImage;
import com.game.util.math.Vector2f;

import java.util.ArrayList;

public class Background {
    private static String spriteSheetPath;
    private static ArrayList<Vector2f> positions = new ArrayList<Vector2f>();
    private static ArrayList<Vector2f> spritePositions = new ArrayList<Vector2f>();
    
    public static void update(float dt) {
    
    }
    
    public static ArrayList<RenderedImage> getRenderedImage() {
        ArrayList<RenderedImage> tmp = new ArrayList<RenderedImage>();
        
        for (int i = 0; i < positions.size(); i++) {
            if (spritePositions.get(i) == null) {
                spritePositions.add(i, new Vector2f());
            }
            
            Sprite sprite = AssetPool.getSpriteSheet(spriteSheetPath).getSprite(spritePositions.get(i));
            tmp.add(new RenderedImage(sprite.getImg(), positions.get(i), sprite.getSize()));
        }
        
        return tmp;
    }
    
    public static void setSpriteSheet(String filepath) {
        spriteSheetPath = filepath;
    }
    
    public static void addBg(Vector2f pos, Vector2f spritePos) {
        positions.add(new Vector2f(pos));
        spritePositions.add(new Vector2f(spritePos));
    }
}
