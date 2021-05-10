package com.game.util;

import com.game.graphics.SpriteSheet;
import com.game.graphics.Texture;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AssetPool {
    private static Map<String, Texture> textures = new HashMap<>();
    private static Map<String, SpriteSheet> spritesheets = new HashMap<>();
    
    public static Texture getTexture(String filepath) {
        File file = new File(filepath);
        if (AssetPool.textures.containsKey(file.getAbsolutePath())) {
            return AssetPool.textures.get(file.getAbsolutePath());
        } else {
            Texture texture = new Texture();
            texture.init(filepath);
            AssetPool.textures.put(file.getAbsolutePath(), texture);
            return texture;
        }
    }
    
    public static SpriteSheet getSpriteSheet(String filepath) {
        File file = new File(filepath);
        if (!AssetPool.spritesheets.containsKey(file.getAbsolutePath())) {
            assert false : "ERROR: Tried to access spritesheet '" + filepath + "' and it's not been added!";
        }
        return spritesheets.get(file.getAbsolutePath());
    }
    
    public static void addSpriteSheet(String filepath, SpriteSheet spritesheet) {
        File file = new File(filepath);
        if (!AssetPool.spritesheets.containsKey(file.getAbsolutePath())) {
            AssetPool.spritesheets.put(file.getAbsolutePath(), spritesheet);
        }
    }
}
