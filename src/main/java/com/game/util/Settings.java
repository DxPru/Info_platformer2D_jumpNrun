/*
    This class contains only settings (Static Variables)
 */

package com.game.util;

public class Settings {
    public static final float TILE_SIZE = 32.0f;  // size of the individual Tiles
    public static final float SCALE = 2.0f; // scale off the individual tiles
    public static final float PX_TILE_SIZE = SCALE * TILE_SIZE; // Size of the displayed tiles in pixel
    public static final int WIDTH = 1920; // screen width
    public static final int HEIGHT = 1080; // screen height
    public static final float X_TILECOUNT = WIDTH / PX_TILE_SIZE;
    public static final float Y_TILECOUNT = HEIGHT / PX_TILE_SIZE;
}
