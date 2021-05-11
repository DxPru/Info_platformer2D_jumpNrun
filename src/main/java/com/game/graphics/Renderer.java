/*
    This class is for drawing GameObjects
 */
package com.game.graphics;

import com.game.util.Camera;
import com.game.util.RenderedImage;
import com.game.util.Settings;
import com.game.util.math.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Renderer {

    public Renderer(){

    }
    
    public void render(Graphics2D g, ArrayList<RenderedImage> renderedImages) {
        for (RenderedImage renderedImage : renderedImages) {
            Vector2f absPos = Camera.getAbsPos(renderedImage.getPos());
            BufferedImage img = renderedImage.getImg();
            Vector2f absSize = new Vector2f(renderedImage.getSize()).mul(Settings.SCALE);
            g.drawImage(img,(int) absPos.x,(int) absPos.y, (int) absSize.x, (int) absSize.y, null);
        }
    }
}
