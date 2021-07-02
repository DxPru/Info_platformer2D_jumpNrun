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

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel {
    
    public static int width, height;
    public static int oldFrameCount;
    public static int frameCount;
    
    // Variables for the Game loop timing
    private final float GAME_FPS = Settings.GAME_FPS;
    private final float TTBR = 1000 / GAME_FPS; // TotalTimeBeforeRender
    
    private float dt = 0.0f;
    private float lastUpdate = System.nanoTime();
    private float now = System.nanoTime();
    
    private Timer timer;
    private LoopTask timerTask;
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
        init();
    }
    
    
    // starting new GameThread on first call of Class
    @Override
    public void addNotify() {
        super.addNotify();
        
        if (timer == null) {
            timer = new Timer();
            timer.scheduleAtFixedRate(timerTask, 0, (long) TTBR);
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
        
        timerTask = new LoopTask();
    }
    
    public void dispose() {
        timerTask.cancel();
        timer.cancel();
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
        Graphics g2 = this.getGraphics();
        g2.drawImage(img, 0, 0, width, height, null);
        g2.dispose();
    }
    
    class LoopTask extends TimerTask {
    
        @Override
        public void run() {
            input(mouse, key);
            update(dt);
            render();
            draw();
    
    
            now = System.nanoTime();
            dt = now - lastUpdate;
            lastUpdate = now;
            frameCount = (int) (1000000000 / dt);
    
            if ((oldFrameCount < frameCount - 2 || oldFrameCount > frameCount + 2) && (int) dt != 0) {
                System.out.println("New DeltaTime: " + (int) dt + " | " + frameCount);
                oldFrameCount = frameCount;
            }
        }
    }
}
