/*
    This class contains only settings (Static Variables)
 */

package com.game.util;

import java.awt.*;

public class Settings {
    private static final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    // screen config
    private static final float PART_OF_SCREEN = 0.9f;
    public static final int PX_WIDTH = (int) ((float) dim.width * PART_OF_SCREEN); // screen width should be a 16:9 ratio
    public static final int PX_HEIGHT = (int) (((float) dim.width / 16) * 9 * PART_OF_SCREEN); // screen height
    public static final boolean FULLSCREEN = false;
    public static final int WIDTH = 256;
    public static final int HEIGHT = 144;
    // Game speed config
    public static final float GAME_FPS = 70.0f;
    // Tile config
    public static final float TILE_SIZE = 16.0f;  // size of the individual Tiles
    public static final float SCALE = (((float) PX_WIDTH / (float) WIDTH) + ((float) PX_HEIGHT / (float) HEIGHT)) / 2; // scale off the individual tiles
    public static final float PX_TILE_SIZE = SCALE * TILE_SIZE; // Size of the displayed tiles in pixel
    public static final float X_TILECOUNT = PX_WIDTH / PX_TILE_SIZE;
    public static final float Y_TILECOUNT = PX_HEIGHT / PX_TILE_SIZE;
    // Map config
    public static final float FLOOR_HEIGHT = HEIGHT - 10.0f;
    // GameScene config
    public static final int START = 0;
    public static final int MENU = 1;
    public static final int PLAY = 2;
    public static final int PAUSE = 3;
    public static final int GAMEOVER = 4;
    // Debug
    public static final boolean DEBUG = true;
}
