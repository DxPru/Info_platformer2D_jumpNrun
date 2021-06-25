package com.game.states;

import com.game.gameObjects.Button;
import com.game.gameObjects.DebugButton;
import com.game.gameObjects.ExitButton;
import com.game.gameObjects.PlayButton;
import com.game.graphics.Sprite;
import com.game.graphics.SpriteSheet;
import com.game.util.*;
import com.game.util.math.Vector2f;
import com.game.views.MenuView;

import java.awt.*;
import java.util.ArrayList;

public class MenuState extends GameState {
    private final Button[] buttons;
    private int focusedButton = 0;
    
    public MenuState(GameManager gamemanager) {
        super(gamemanager);
        renderer = new MenuView();
        buttons = new Button[3];
        init();
    }
    
    @Override
    protected void init() {
        Camera.setPosition(0, 0);
        int buttonWidth = 64;
        int buttonHeight = 32;
        String ButtonPath = "res/spritesheets/Button.png";
        AssetPool.addSpriteSheet(ButtonPath, new SpriteSheet(AssetPool.getTexture(ButtonPath), buttonWidth, buttonHeight));
        buttons[0] = new PlayButton(new Vector2f((float) ((Settings.WIDTH / 2) - buttonWidth / 2), 20), ButtonPath, 0, this.gamemanager); //Play button
        buttons[0].setFocused(true);
        buttons[1] = new DebugButton(new Vector2f((float) ((Settings.WIDTH / 2) - buttonWidth / 2), 60), ButtonPath, 2, this.gamemanager); //Username button
        buttons[2] = new ExitButton(new Vector2f((float) ((Settings.WIDTH) - buttonWidth) - 12, 100), ButtonPath, 1, this.gamemanager); //Exit button
    }
    
    @Override
    public void update(float dt) {
        if (!buttons[focusedButton].isFocused()) {
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].setFocused(i == focusedButton);
            }
        }
    }
    
    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
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
        Sprite background = AssetPool.getSpriteSheet("res/spritesheets/LoadingScreen.png").getSprite(0, 0);
        renderdImages.add(0, new RenderedImage(background.getImg(), new Vector2f(), background.getSize()));
        renderer.render(g, renderdImages);
    }
}
