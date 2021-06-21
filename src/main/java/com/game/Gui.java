package com.game;

import com.game.util.Settings;

import javax.swing.*;

public class Gui extends JFrame {
    
    private static Gui gui;
    
    public Gui() {
        init();
    }
    
    private void init() {
        gui = this;
        setTitle("2D Platformer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(Settings.FULLSCREEN);
        setContentPane(new GamePanel(Settings.PX_WIDTH, Settings.PX_HEIGHT));
        
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setIgnoreRepaint(true);
        setVisible(true);
    }
    
    public static void close() {
        gui.setVisible(false);
        gui.dispose();
        System.exit(0);
    }
}
