package com.game;

import com.game.util.Settings;

import javax.swing.JFrame;

public class Gui extends JFrame {
    
    public Gui() {
        setTitle("2D Platformer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(Settings.WIDTH, Settings.HEIGHT));
        
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
