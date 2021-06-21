package com.game.tile;

import com.game.graphics.SpriteSheet;
import com.game.tile.block.Block;
import com.game.util.AssetPool;
import com.game.util.RenderedImage;
import com.game.util.math.Vector2f;

import java.util.ArrayList;

public class TileManager {
    private ArrayList<Block> tiles = new ArrayList<Block>();
    private String filePath;
    
    public TileManager(String filePath) {
        this.filePath = filePath;
    }
    
    public void addBlock(Block block) {
        this.tiles.add(block);
    }
    
    public void addBlock(Vector2f pos, Vector2f spritePos) {
        this.addBlock(new Block(pos, AssetPool.getSpriteSheet(this.filePath).getSprite(spritePos)));
    }
    
    public void popBlock(Block block) {
        this.tiles.remove(block);
    }
    
    public void update(float dt) {
        for(Block block : this.tiles) {
            block.update(dt);
        }
    }
    
    public void genTileMap() {
        // TODO Auto-Generated map
    }
    
    public ArrayList<RenderedImage> getRenderedImage() {
        ArrayList<RenderedImage> tmp = new ArrayList<RenderedImage>();
        
        for(Block block : this.tiles) {
            tmp.add(block.getRenderedImage());
        }
        
        return tmp;
    }
}
