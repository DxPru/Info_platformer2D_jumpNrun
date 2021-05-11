/*
    this class handles the position of the Camera for optimizing the drawing
 */

package com.game.util;


import com.game.util.math.Vector2f;

public class Camera {
    private static Vector2f position = new Vector2f(); // position of the Camera (Middle of the screen)
    private static final Vector2f PROJECTION_SIZE = new Vector2f(Settings.WIDTH, Settings.HEIGHT);
    private static Vector2f[] projection = new Vector2f[2];
    
    public Camera() {}
    
    public Camera(float x, float y) {
        position.x = x;
        position.y = y;
        
        updateProjection();
    }
    
    public static void updateProjection() {
        // moves the projection Vectors to the upper right corner and the lower left corner
        projection[0] = new Vector2f(position);
        projection[1] = new Vector2f(position).add(PROJECTION_SIZE);
    }
    
    public static void movePosition(float x, float y) {
        // moves the Camera according to the given params.
        position.x += x;
        position.y += y;
        
        updateProjection();
    }
    
    public static void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
    }
    
    public static Vector2f getPosition() {
        return position;
    }
    
    public static Vector2f[] getProjection() {
        return projection;
    }
    
    public static Vector2f getAbsPos(Vector2f pos) {
        // translates World Coordinates to screen coordinates
        return new Vector2f(pos).sub(projection[0]).mul(Settings.SCALE);
    }
}
