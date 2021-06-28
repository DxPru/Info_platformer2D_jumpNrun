package com.game.util;

import com.game.graphics.SpriteSheet;
import com.game.graphics.Texture;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AssetPool {
    private static final Map<String, Texture> textures = new HashMap<>();
    private static final Map<String, SpriteSheet> spritesheets = new HashMap<>();
    private static final Map<String, Font> fonts = new HashMap<>();
    private static final Map<String, Color> colors = new HashMap<>();
    
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
        assert AssetPool.spritesheets.containsKey(file.getAbsolutePath()) : "ERROR: Tried to access spritesheet '" + filepath + "' and it's not been added!";
        return spritesheets.get(file.getAbsolutePath());
    }
    
    public static void addSpriteSheet(String filepath, SpriteSheet spritesheet) {
        File file = new File(filepath);
        if (!AssetPool.spritesheets.containsKey(file.getAbsolutePath())) {
            AssetPool.spritesheets.put(file.getAbsolutePath(), spritesheet);
        }
    }
    
    public static Font getFont(String filepath) {
        File file = new File(filepath);
        assert AssetPool.fonts.containsKey(file.getAbsolutePath()) : "ERROR: Tried to access Font '" + filepath + "' and it's not been added!";
        return AssetPool.fonts.get(file.getAbsolutePath());
    }
    
    public static void addFont(String filepath, int fontType, float size) {
        File file = new File(filepath);
        if (!AssetPool.fonts.containsKey(file.getAbsolutePath())) {
            try {
                AssetPool.fonts.put(file.getAbsolutePath(), Font.createFont(fontType, file).deriveFont(size));
            } catch (FontFormatException | IOException e) {
                assert false : "ERROR: Filed to add Font '" + filepath + "'";
                e.printStackTrace();
            }
        }
    }
    
    public static Color getColor(String name) {
        assert AssetPool.colors.containsKey(name) : "ERROR: Tried to access Color '" + name + "' and it's not been added!";
        return colors.get(name);
    }
    
    public static void addColor(String name, Color color) {
        if (!AssetPool.colors.containsKey(name)) {
            AssetPool.colors.put(name, color);
        }
    }
}
