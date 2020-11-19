package com.team13.game.boat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.team13.game.lane.Lane;
import com.team13.game.stats.Position;
import com.team13.game.stats.Stats;

/**
 * Parent class of all boats.
 * Note: This should've probably been an abstract class
 */
public class Boat {

    // Fields
    /**
     * Holds 2 values for boat x and y positions
     */
    protected Position boatPosition;

    /**
     * Holds boat stats.
     * Such as speed, acceleration, deceleration, maximum speed, maneuverability (horizontal speed), robustness and fatigue.
     */
    protected Stats boatStats;

    /**
     * Stores the texture for each boat.
     */
    protected Texture boatTexture;

    /**
     * Sprite batch used to draw each boat.
     */
    protected SpriteBatch batch;

    /**
     * Sprite used to draw boats. Holds the texture and is drawn in the batch.
     */
    protected Sprite boatSprite;

    /**
     * How much the sprite needs to be scaled
     */
    protected float spriteScale;

    /**
     * How wide the sprite is
     */
    protected float spriteWidth;


    // Constructors
    /**
     * Constructor for the boat.
     * Allocates the sprite batch.
     */
    public Boat() {
        batch = new SpriteBatch();
    }


    // Methods
    /**
     * Function to change boat speed.
     * The boat cannot accelerate more than speed limit. direction is passed as either Direction.FORWARDS or Direction.BACKWARDS.
     *
     * @see Direction
     * @param direction Direction of acceleration. 1 for forwards, -1 for backwards.
     */
    public void accelerate(int direction){
        if (boatStats.getSpeed() < boatStats.getMaxSpeed()) {
            boatStats.setSpeed(boatStats.getSpeed() + boatStats.getAcceleration() * direction);
        }
    }

    /**
     * Function to draw the boat.
     * It sets the projection matrix for the sprite batch. Translates the sprite then draws it within the sprite batch.
     *
     * @param projectionMatrix projection matrix for the camera.
     * @see com.team13.game.Canvas#getProjection()
     */
    public void draw(Matrix4 projectionMatrix){
        batch.setProjectionMatrix(projectionMatrix);
        batch.begin();
        boatSprite.setPosition(boatPosition.getPosX(), boatPosition.getPosY());
        boatSprite.draw(batch);
        batch.end();
    }

    /**
     * Function to control the boats.
     * To be overridden when inheriting
     */
    public void control() {}

    /**
     * Updates boat position when no keys are pressed.
     * Boat speed is decreasing (due to drag), position keeps increasing until the speed is below 0.
     * Speed is clamped at 0 when it goes below 0 (or reaches 0)
     */
    public void update(){
        if (boatStats.getSpeed() > 0){
            boatStats.setSpeed(boatStats.getSpeed() - boatStats.getDeceleration());
            boatPosition.setPosY(boatPosition.getPosY() + boatStats.getSpeed());
        } else {
            boatStats.setSpeed(0);
        }
    }

    public void checkCollisions(Lane lane){
        if (lane.getlBorder() > boatPosition.getPosX() || lane.getrBorder() < boatPosition.getPosX() + spriteWidth){
            // TODO: Make penalty
        }

    }


//  Getters
    public Position getBoatPosition() {
        return boatPosition;
    }

    public Stats getBoatStats() {
        return boatStats;
    }

    public Sprite getSprite(){
        return boatSprite;
    }

    public float getScale() {
        return spriteScale;

    }


    // Setters
    public void setBoatPosition(Position boatPosition) {
        this.boatPosition = boatPosition;
    }

    public void setBoatStats(Stats boatStats) {
        this.boatStats = boatStats;
    }


    // Static classes
    /**
     * Gives names to directions.
     * Using 1 for forwards and -1 for backwards
     * 
     * @see Boat#accelerate(int direction)
     */
    static class Direction{
        public static final int FORWARDS = 1;
        public static final int BACKWARDS = -1;

    }
}
