package com.game;

import com.game.states.GameManager;
import com.game.util.KeyHandler;
import com.game.util.MouseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {
    
    public static int width, height;
    public static int oldFrameCount;
    public static int oldTickCount;
    public static int tickCount;
    
    private Thread thread;
    private boolean running = false;
    
    private BufferedImage img;
    private Graphics2D g;
    
    private MouseHandler mouse;
    private KeyHandler key;
    
    private GameManager gameManager;
    
    // Setting up the JPanel (new Window)
    public GamePanel (int width, int height) {
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
        
        gameManager = new GameManager();
    }
    
    // method is run at start of the GameThread
    @Override
    public void run() {
        init();
        
        // Variables for the Game loop timing
        final double GAME_HERTZ = 60.0;
        final double TRU = 1000000000 / GAME_HERTZ; // Time Before Update
    
        final  int MUBR = 5; // Must Update Before Render
    
        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;
    
        final double TARGET_FPS = 120;
        final double TTBR = 1000000000 / TARGET_FPS; // Total Time Before Render
    
        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);
        oldFrameCount = 0;
    
        tickCount = 0;
        oldTickCount = 0;
    
        while (running){
        
            // Querying inputs && updating the Game (tick)
            double now = System.nanoTime();
            int updateCount = 0;
            while (((now - lastUpdateTime) > TRU) && (updateCount < MUBR)){
                update();
                input(mouse, key);
                lastUpdateTime += TRU;
                updateCount ++;
                tickCount ++;
            }
        
            if (now - lastUpdateTime > TRU) {
                lastUpdateTime = now - TRU;
            }
            
            // rendering and drawing (frame)
            input(mouse, key);
            render();
            draw();
            lastRenderTime = now;
            frameCount ++;
        
            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime) {
                if (frameCount != oldFrameCount) {
                    System.out.println("New SECOND: " + thisSecond + " | " + frameCount);
                    oldFrameCount = frameCount;
                }
            
                if (tickCount != oldTickCount) {
                    System.out.println("New SECOND (T): " + thisSecond + " | " + tickCount);
                    oldTickCount = tickCount;
                }
            
                tickCount = 0;
                frameCount = 0;
                lastSecondTime = thisSecond;
            }
        
            // sleeping until next update
            while (now - lastRenderTime < TTBR && now - lastUpdateTime < TRU){
                Thread.yield();
            
                try {
                    Thread.sleep(1);
                } catch (Exception e){
                    System.out.println("ERROR: yielding Thread"); // failed to pause Thread
                }
            
                now = System.nanoTime();
            }
        }
    }
    
    // TODO import GameManager
    
    public void update(){
        // gameManager.update();
    }
    
    public void input(MouseHandler mouse, KeyHandler key){
        // gameManager.input(mouse, key);
    }
    
    public void render(){
        if(g != null){
            g.setColor(new Color(66, 134, 244));
            g.fillRect(0, 0, width, height);
            // gameManager.render(g);
        }
    }
    
    public void draw(){
        Graphics g2 = (Graphics) this.getGraphics();
        g2.drawImage(img, 0, 0 , width, height, null);
        g2.dispose();
    }
}
