package com.game;

import com.game.states.GameManager;
import com.game.util.Settings;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Objects;

public class Gui extends JFrame {
    
    private static Gui gui;
    private GamePanel gamepanel;
    
    public Gui() {
        init();
    }
    
    public static void close() {
        getInstance().setVisible(false);
        getInstance().gamepanel.dispose();
        getInstance().dispose();
        System.exit(0);
    }
    
    private void init() {
        gui = this;
        setTitle("Pixel Jump");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // when closed via the red x the data is not saved --> disabled
        WindowListener exitListener = new WindowAdapter() {
        
            @Override
            public void windowClosing(WindowEvent e) {
                GameManager.getInstance().save();
                Gui.close();
            }
        };
        addWindowListener(exitListener);
        setUndecorated(Settings.FULLSCREEN);
        gamepanel = new GamePanel(Settings.PX_WIDTH, Settings.PX_HEIGHT);
        setContentPane(gamepanel);
        setIconImage(new ImageIcon("res/spritesheets/icon.png").getImage());
        
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static Gui getInstance() {
        return Objects.requireNonNullElseGet(gui, Gui::new);
    }
}
