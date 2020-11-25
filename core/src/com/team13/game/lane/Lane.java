package com.team13.game.lane;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.team13.game.mainGame;

/**
 * Used to define and draw lanes for each boat to stay in.
 */
public class Lane {

    // Fields
    /**
     * Left lane border.
     */
    protected int lBorder;

    /**
     * Right lane border.
     */
    protected int rBorder;

    /**
     * Border width.
     */
    protected static final byte BORDERWIDTH = 5;

    /**
     * Thing that renders shapes.
     */
    protected ShapeRenderer shapeRenderer;


    // Constructors
    /**
     * Default constructor is private to prevent lanes with borders 0, 0.
     * This behaviour may be default if the class is left without a constructor, but this way it is explicit.
     */
    private Lane(){}


    /**
     * Constructor takes 2 values for border positions and assigns them to border positions of the instance.
     * Also initialises the shape renderer used to render the borders
     *
     * @param lBorder left border x coordinate
     * @param rBorder right border x coordinate
     */
    public Lane(int lBorder, int rBorder) {
        this.lBorder = lBorder;
        this.rBorder = rBorder;
        shapeRenderer = new ShapeRenderer();
    }


    // Methods
    /**
     * Draw each lane.
     * Uses the shape renderer to render rectangles, one for each border.
     * Each border goes from 0 to screen height on the y axis.
     *
     * @param camera the camera used ro the game. Supplies the projection matrix, viewport size and camera position
     */
    public void draw(final Camera camera){
        // Setting projection matrix
        shapeRenderer.setProjectionMatrix(camera.combined);
        // Between .begin and .eng shapes can be drawn.
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        // Color is set to black
        shapeRenderer.setColor(0, 0, 0, 1);
        // Left border drawn
        shapeRenderer.rect( lBorder + 100, camera.position.y - camera.viewportHeight/2, BORDERWIDTH, camera.viewportHeight);
        // Right border drawn
        shapeRenderer.rect( rBorder - BORDERWIDTH + 100, camera.position.y - camera.viewportHeight/2, BORDERWIDTH, camera.viewportHeight);

        shapeRenderer.end();

    }

    /**
     * Calls dispose on the shape renderer.
     */
    public void dispose(){
        shapeRenderer.dispose();
    }


    // Getters
    public int getlBorder() {
        return lBorder;
    }

    public int getrBorder() {
        return rBorder;
    }

    // Returns the midpoint of the lane
    public int getMiddle(){
        // Implicit cast to int
        return (lBorder + rBorder)/2;
    }
}
