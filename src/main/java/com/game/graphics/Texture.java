package com.game.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Texture {
    private String filepath;
    private BufferedImage img;
    private int width, height;
    
    public void init(String filepath) {
        this.filepath = filepath;
        
        try{
            img = ImageIO.read(Objects.requireNonNull(new File(filepath)));
        } catch (IOException e) {
            System.out.println("ERROR: could not load file: " + filepath);
            e.printStackTrace();
        }
        this.width = img.getWidth();
        this.height = img.getHeight();
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public BufferedImage getImg() {
        return img;
    }
}
