package com.game;

import com.game.util.Settings;

import javax.swing.*;

public class Gui extends JFrame {
    
    private static Gui gui;
    
    public Gui() {
        gui = this;
        setTitle("2D Platformer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setContentPane(new GamePanel(Settings.PX_WIDTH, Settings.PX_HEIGHT));
        
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void close() {
        gui.setVisible(false);
        gui.dispose();
        System.exit(0);
    }
}
