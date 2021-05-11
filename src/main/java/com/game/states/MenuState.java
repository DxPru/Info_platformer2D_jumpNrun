package com.game.states;

import com.game.gameObjects.Button;
import com.game.gameObjects.DebugButton;
import com.game.graphics.SpriteSheet;
import com.game.util.*;
import com.game.util.math.Vector2f;
import com.game.views.MenuView;
import com.game.views.View;

import java.awt.*;
import java.util.ArrayList;

public class MenuState extends GameState {
    private GameManager gamemanager;
    private View renderer;
    private Button[] buttons;
    private int focusedButton = 0;
    
    public MenuState(GameManager gamemanager) {
        super(gamemanager);
        this.gamemanager = gamemanager;
        renderer = new MenuView();
        buttons = new Button[3];
        init();
    }
    
    private void init() {
        Camera.setPosition(0, 0);
        int buttonWidth = 64;
        int buttonHeight = 32;
        String ButtonPath = "res/images/Button.png";
        AssetPool.addSpriteSheet(ButtonPath,new SpriteSheet(AssetPool.getTexture(ButtonPath),buttonWidth, buttonHeight));
        buttons[0] = new DebugButton(new Vector2f((float) ((Settings.WIDTH / 2) - buttonWidth / 2), 20),ButtonPath, 0); //Play button
        buttons[0].setFocused(true);
        buttons[1] = new DebugButton(new Vector2f((float) ((Settings.WIDTH / 2) - buttonWidth / 2), 60),ButtonPath, 2); //Username button
        buttons[2] = new DebugButton(new Vector2f((float) ((Settings.WIDTH) - buttonWidth) - 12, 100),ButtonPath, 1); //Exit button
    }
    
    @Override
    public void update(float dt) {
        if (!buttons[focusedButton].isFocused()) {
            for (int i = 0; i < buttons.length; i++) {
                if (i == focusedButton) {
                    buttons[i].setFocused(true);
                } else {
                    buttons[i].setFocused(false);
                }
            }
        }
    }
    
    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        // TODO Temporary exit because of fullscreen should be done over the buttons
        if (key.escape.down) {
            System.out.println("Closing");
            gamemanager.gui.close();
        }
        if (key.up.clicked) {
            focusedButton--;
            if (focusedButton < 0) {
                focusedButton = 0;
            }
        }
        if (key.down.clicked) {
            focusedButton++;
            if (focusedButton >= buttons.length) {
                focusedButton = buttons.length - 1;
            }
        }
        
        for (Button button : buttons) {
            button.input(mouse, key);
        }
        
    }
    
    @Override
    public void render(Graphics2D g) {
        ArrayList<RenderedImage> renderdImages = new ArrayList<RenderedImage>();
        for (Button button : buttons) {
            renderdImages.add(button.getRenderedImage());
        }
        renderer.render(g, 0, renderdImages);
    }
}
