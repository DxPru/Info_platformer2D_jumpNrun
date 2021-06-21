package com.game.tile;

import com.game.graphics.Texture;
import com.game.util.AssetPool;
import com.game.util.RenderedImage;
import com.game.util.math.Vector2f;

import java.util.ArrayList;

public class Background {
    private static Texture texture;
    private static ArrayList<Vector2f> positions = new ArrayList<Vector2f>();
    
    public static void update(float dt) {
    
    }
    
    public static ArrayList<RenderedImage> getRenderedImage() {
        ArrayList<RenderedImage> tmp = new ArrayList<RenderedImage>();
        
        for (Vector2f pos : positions) {
            tmp.add(new RenderedImage(texture.getImg(), pos, texture.getSize()));
        }
        
        return tmp;
    }
    
    public static void setTexture(String filepath) {
        texture = AssetPool.getTexture(filepath);
    }
    
    public static void addBg(Vector2f pos) {
        positions.add(new Vector2f(pos));
    }
}
