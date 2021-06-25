package com.game.tile;

import com.game.graphics.Sprite;
import com.game.graphics.SpriteSheet;
import com.game.util.AssetPool;
import com.game.util.Camera;
import com.game.util.RenderedImage;
import com.game.util.math.Vector2f;
import com.game.util.physics.Collision;
import com.game.util.physics.Rect;

import java.util.ArrayList;
import java.util.Stack;

public class Background {
    private String filepath;
    private final Stack<BG> backgrounds = new Stack<BG>();
    private Vector2f nextpos = new Vector2f();
    
    public Background(String filePath) {
        this.filepath = filePath;
    }
    
    public void update(float dt) {
        for (BG bg : backgrounds) {
            bg.update(dt);
        }
    }
    
    public ArrayList<RenderedImage> getRenderedImage() {
        ArrayList<RenderedImage> tmp = new ArrayList<RenderedImage>();
        
        for (BG bg : backgrounds) {
            tmp.add(bg.getRenderedImage());
        }
        
        return tmp;
    }
    
    public void setSpriteSheet(String filepath) {
        this.filepath = filepath;
    }
    
    public void addBg() {
        backgrounds.push(new BG());
    }
    
    public void genBackground(int count) {
    
    }
    
    public class BG {
        protected Vector2f pos;
        protected Vector2f spritePos;
        protected Collision collision;
        
        public BG() {
            this.genSpritePos();
            this.genPos();
            collision = new Collision(new Rect(pos, AssetPool.getSpriteSheet(filepath).getSprite(spritePos).getSize()));
        }
        
        public BG(Vector2f pos, Vector2f spritePos) {
            this.pos = pos;
            this.spritePos = spritePos;
            collision = new Collision(new Rect(pos, AssetPool.getSpriteSheet(filepath).getSprite(spritePos).getSize()));
        }
        
        public void update(float dt) {
            if (collision.rect.getPos().x + collision.rect.getSize().x < Camera.getRect().getPos().x) {
                this.genPos();
                this.collision.rect.setPos(this.pos);
                this.genSpritePos();
            }
        }
        
        public RenderedImage getRenderedImage() {
            Sprite sprite = AssetPool.getSpriteSheet(filepath).getSprite(spritePos);
            
            return new RenderedImage(sprite.getImg(), pos, sprite.getSize());
        }
        
        public void genSpritePos() {
            SpriteSheet spriteSheet = AssetPool.getSpriteSheet(filepath);
            
            int x = (int) (Math.random() * spriteSheet.getWidth());
            int y = (int) (Math.random() * spriteSheet.getHeight());
            
            this.spritePos = new Vector2f(x, y);
        }
        
        public void genPos() {
            this.pos = nextpos;
            nextpos = new Vector2f(this.pos).add(new Vector2f(AssetPool.getSpriteSheet(filepath).getSprite(spritePos).getWidth(), 0));
        }
    }
}
