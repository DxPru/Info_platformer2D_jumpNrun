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
        
            System.out.println("Removed Block");
        }
    }
    
    public ArrayList<RenderedImage> getRenderedImage() {
        ArrayList<RenderedImage> tmp = new ArrayList<RenderedImage>();
        
        for (Block block : this.tiles) {
            tmp.add(block.getRenderedImage());
        }
        
        return tmp;
    }
    
    public boolean getDel() {
        return tiles.size() <= 0;
    }
    
    public Stack<Rect> getRects() {
        Stack<Rect> rects = new Stack<Rect>();
        
        for (Block block : tiles) {
            rects.add(block.rect);
        }
        
        return rects;
    }
}
