package com.game.tile;

import com.game.tile.block.Block;
import com.game.util.AssetPool;
import com.game.util.RenderedImage;
import com.game.util.math.Vector2f;
import com.game.util.physics.Rect;

import java.util.ArrayList;
import java.util.Stack;

public class TileMap {
    private final ArrayList<Block> tiles = new ArrayList<Block>();
    private final String filePath;
    
    public TileMap(String filePath, float startX, Integer[][] pattern, int tilewidth, int tileheight, int width, int height) {
        this.filePath = filePath;
        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (pattern[x][y] != 0) {
                    addBlock(new Vector2f(startX + (x * tileheight), (y * tileheight) - 10), new Vector2f(pattern[x][y] - 1, 0));
                }
            }
        }
    }
    
    public void addBlock(Vector2f pos, Vector2f spritePos) {
        this.tiles.add(new Block(pos, AssetPool.getSpriteSheet(this.filePath).getSprite(spritePos)));
    }
    
    public void popBlock(Block block) {
        this.tiles.remove(block);
    }
    
    public void update(float dt) {
        Stack<Block> del = new Stack<Block>();
        for (Block block : this.tiles) {
            block.update(dt);
            if (block.getDel()) {
                del.push(block);
            }
        }
    
        for (Block block : del) {
            tiles.remove(block);
        }
    }
    
    public ArrayList<RenderedImage> getRenderedImages() {
        ArrayList<RenderedImage> tmp = new ArrayList<RenderedImage>();
        RenderedImage temp;
    
        for (Block block : this.tiles) {
            temp = block.getRenderedImage();
            if (temp != null) {
                tmp.add(temp);
            }
        }
        
        return tmp;
    }
    
    public boolean getDel() {
        return tiles.size() <= 0;
    }
    
    public Stack<Rect> getRects() {
        Stack<Rect> rects = new Stack<Rect>();
        Rect rect;
        
        for (Block block : tiles) {
            rect = block.getRect();
            if (rect != null) {
                rects.add(rect);
            }
        }
        
        return rects;
    }
}
