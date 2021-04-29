package com.game;

import javax.swing.JFrame;

public class Gui extends JFrame {
    
    public Gui() {
        setTitle("2D Platformer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(1280, 720));
        
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
