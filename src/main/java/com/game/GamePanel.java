/*
    This class acts as a main controller managing timing between input, update (logical update) and Rendering (Graphical update)
    The actions are then past to the GameManager CLASS
    
    the initializing of a new Panel is although handled here because only one panel is used
*/

package com.game;

import com.game.states.GameManager;
import com.game.util.KeyHandler;
import com.game.util.MouseHandler;
import com.game.util.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    
    public static int width, height;
    public static int oldFrameCount;
    public static int frameCount;
    
    private Thread thread;
    private boolean running = false;
    
    private BufferedImage img;
    private Graphics2D g;
    
    private MouseHandler mouse;
    private KeyHandler key;
    
    private GameManager gamemanager;
    
    // Setting up the JPanel (new Window)
    public GamePanel(int width, int height) {
        GamePanel.width = width;
        GamePanel.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
    }
    
    
    // starting new GameThread on first call of Class
    @Override
    public void addNotify() {
        super.addNotify();
        
        if (thread == null) {
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }
    
    // initializing the Game
    public void init() {
        running = true;
        
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();
        
        mouse = new MouseHandler(this);
        key = new KeyHandler(this);
        
        gamemanager = new GameManager();
    }
    
    // method is run at start of the GameThread
    @Override
    public void run() {
        init();
        
        // Variables for the Game loop timing
        final float GAME_FPS = Settings.GAME_FPS;
        final float TTBR = 1000000000 / GAME_FPS; // TotalTimeBeforeRender
        
        float dt = 0.0F;
        float lastUpdate = System.nanoTime();
        
        while (running) {
    
            this.input(this.mouse, this.key);
            this.update(dt);
            this.render();
            this.draw();
    
            double now = System.nanoTime();
            while (now - lastUpdate < TTBR) {
                Thread.yield();
    
                try {
                    Thread.sleep(1);
                } catch (Exception e){
                    System.out.println("ERROR: yielding Thread");
                }
    
                now = System.nanoTime();
            }
    
            dt = System.nanoTime() - lastUpdate;
            lastUpdate = System.nanoTime();
            frameCount = (int) (1000000000 / dt);
            
            if (oldFrameCount < frameCount - 5|| oldFrameCount > frameCount + 5 ) {
                System.out.println("New DeltaTime: " + (int) dt + " | " + frameCount);
                oldFrameCount = frameCount;
            }
        }
    }
    
    public void update(float dt) {
        this.gamemanager.update(dt);
    }
    
    public void input(MouseHandler mouse, KeyHandler key) {
        key.tick();
        this.gamemanager.input(mouse, key);
    }
    
    public void render() {
        if (g != null) {
            g.setColor(new Color(66, 134, 244));
            g.fillRect(0, 0, width, height);
            this.gamemanager.render(g);
        }
    }
    
    public void draw() {
        Graphics g2 = (Graphics) this.getGraphics();
        g2.drawImage(img, 0, 0, width, height, null);
        g2.dispose();
    }
}
