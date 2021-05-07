package com.game;

import com.game.util.Settings;

import javax.swing.JFrame;

public class Gui extends JFrame {
    
    public Gui() {
        setTitle("2D Platformer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setContentPane(new GamePanel(Settings.WIDTH, Settings.HEIGHT, this));
    
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void close() {
        setVisible(false);
        dispose();
        System.exit(0);
    }
}
