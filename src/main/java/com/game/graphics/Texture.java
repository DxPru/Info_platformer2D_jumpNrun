package com.game.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Texture {
    private String filepath;
    private BufferedImage img;
    private int width, height;
    
    public void init(String filepath) {
        this.filepath = filepath;
        
        try{
            File file;
            img = ImageIO.read(getClass().getClassLoader().getResourceAsStream(filepath));
        } catch (IOException e) {
            System.out.println("ERROR: could not load file: " + filepath);
            e.printStackTrace();
        }
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
}
