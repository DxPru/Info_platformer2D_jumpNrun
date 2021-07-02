package com.game.util;

import com.game.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyHandler implements KeyListener {
    public static List<Key> keys = new ArrayList<Key>();
    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();
    public Key space = new Key();
    public Key enter = new Key();
    public Key escape = new Key();
    private boolean typing = false;
    private String string = "";
    
    public KeyHandler(GamePanel game) {
        game.addKeyListener(this);
    }
    
    public void resetAll() {
        for (Key key : keys) {
            key.reset();
        }
        string = "";
    }
    
    public void tick() {
        for (Key key : keys) {
            key.tick();
        }
    }
    
    public void toggle(KeyEvent e, boolean pressed) {
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) up.toggle(pressed);
        if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) down.toggle(pressed);
        if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) right.toggle(pressed);
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) left.toggle(pressed);
        if (e.getKeyCode() == KeyEvent.VK_SPACE) space.toggle(pressed);
        if (e.getKeyCode() == KeyEvent.VK_ENTER) enter.toggle(pressed);
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) escape.toggle(pressed);
    }
    
    public void setTyping(boolean typing) {
        this.typing = typing;
        if (!typing) {
            string = "";
        }
    }
    
    public String getString() {
        return string;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // Do nothing
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        toggle(e, true);
        if (typing) {
            int code = e.getKeyCode();
            if ((code >= 65 && code <= 90) || (code >= 97 && code <= 122) || (code >= 48 && code <= 57)) {
                string += e.getKeyChar();
            } else if (code == 8) {
                if (string.length() <= 1) {
                    string = "";
                } else {
                    string = string.substring(0, string.length() - 1);
                }
            } else if(code == 32) {
                string += ' ';
            } else if (code == KeyEvent.VK_ESCAPE) {
                toggle(e, true);
                setTyping(false);
            }
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        toggle(e, false);
    }
    
    public class Key {
        public int presses, absorbs;
        public boolean down, clicked;
        
        public Key() {
            keys.add(this);
        }
        
        public void toggle(boolean pressed) {
            if (pressed != down) {
                down = pressed;
            }
            if (pressed) {
                presses++;
            }
        }
        
        public void tick() {
            if (absorbs < presses) {
                absorbs++;
                clicked = true;
            } else {
                clicked = false;
            }
        }
        
        public void reset() {
            down = false;
            clicked = false;
            absorbs = 0;
            presses = 0;
        }
    }
}
