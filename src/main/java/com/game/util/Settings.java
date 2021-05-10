/*
    This class contains only settings (Static Variables)
 */

package com.game.util;

public class Settings {
    // screen config
    public static final int WIDTH = 1920; // screen width
    public static final int HEIGHT = 1080; // screen height
    
    // Game speed config
    public static final double GAME_HERTZ = 60.0;
    public static final double GAME_FPS = 120.0;
    
    // Tile config
    public static final float TILE_SIZE = 16.0f;  // size of the individual Tiles
    public static final float SCALE = 7.5f; // scale off the individual tiles
    public static final float PX_TILE_SIZE = SCALE * TILE_SIZE; // Size of the displayed tiles in pixel
    public static final float X_TILECOUNT = WIDTH / PX_TILE_SIZE;
    public static final float Y_TILECOUNT = HEIGHT / PX_TILE_SIZE;
    
    // GameScene config
    public static final int START = 0;
    public static final int MENU = 1;
    public static final int PLAY = 2;
    public static final int PAUSE = 3;
    public static final int GAMEOVER = 4;
}
