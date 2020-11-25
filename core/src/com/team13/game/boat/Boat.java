package com.team13.game.boat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.team13.game.lane.Lane;
import com.team13.game.obstacle.Obstacle;
import com.team13.game.obstacle.Spawn;
import com.team13.game.scenes.Canvas;
import com.team13.game.stats.Position;
import com.team13.game.stats.Stats;

/**
 * Parent class of all boats.
 * Responsible for rendering and managing boat speed and acceleration.
 */
public class Boat {

    // Fields
    /**
     * Holds values for boat x and y positions.
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

    /**
     * How tall the sprite is
     */
    protected float spriteHeight;

    /**
     * Number of penalties the boat had received for crossing Lane boundaries.
     */
    private long penalties =0;

    /**
     * When the boat received its last penalty.
     */
    private long timeOfLastPenalty = 0;

    /**
     * How many seconds each penalty adds.
     */
    private final long penalty = 5;

    /**
     * How many times a second a penalty can be issued.
     */
    private final long penaltiesPerSecond = 2;

    /**
     * How much boat's robustness decreases each time it hits and Obstacle.
     */
    private final int breakability = 1;

    /**
     * When was the last time the boat took damage from collision.
     */
    private long timeSinceLastCollision = 0;


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
    protected void accelerate(int direction){
        if (boatStats.getSpeed() < boatStats.getMaxSpeed()) {
            boatStats.setSpeed(boatStats.getSpeed() + boatStats.getAcceleration() * direction);
        }
    }

    /**
     * Function to draw the boat.
     * It sets the projection matrix for the sprite batch. Translates the sprite then draws it within the sprite batch.
     *
     * @param projectionMatrix projection matrix for the camera.
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
     * To be overridden when inheriting.
     */
    public void control(Spawn spawn) {}

    /**
     * Updates boat position when no keys are pressed or when control() is no longer being called (e.g. after the race is over).
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

    /**
     * Checks for both collisions with Lane borders and any Obstacles.
     * If boat's left boundary crosses Lane's right boundary (and vice versa)
     * and starts adding penalties in accordance with the penalty and penaltiesPerSecond variables.
     * Also loops over all currently spawned Obstacles and checks if the boats bounding box intersects the Obstacle's bounding box.
     * @param lane The lane this boat is in.
     * @param spawn Spawn object holding all currently spawned obstacles.
     * @see Spawn
     * @see Lane
     * @see Obstacle
     */
    public void checkCollisions(Lane lane, Spawn spawn){
        // Useless rectangle.
        if (lane.getlBorder() > boatPosition.getPosX() || lane.getrBorder() < boatPosition.getPosX() + spriteWidth){
            if ((System.currentTimeMillis() - timeOfLastPenalty)/1000 > (1/penaltiesPerSecond)){
            penalties += penalty;
                timeOfLastPenalty = System.currentTimeMillis();
            }
        }
        for (Obstacle o : spawn.getObstacleList()) {
            if(Intersector.overlaps(boatSprite.getBoundingRectangle(), o.getObstacleSprite().getBoundingRectangle())){
                boatStats.setSpeed(boatStats.getSpeed()*0.5F);
                if ((System.currentTimeMillis() - timeSinceLastCollision)/1000 > (1)) {
                    boatStats.setRobustness(boatStats.getRobustness() - breakability);
                }
            }
        }
    }


    /**
     * Calls dispose on the SpriteBatch and the texture
     */
    public void dispose(){
        batch.dispose();
        boatTexture.dispose();
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

    public long getPenalties() {
        return penalties;
    }

    // Setters
    public void setBoatPosition(Position boatPosition) {
        this.boatPosition = boatPosition;
    }

    public void setBoatStats(Stats boatStats) {
        this.boatStats = boatStats;
    }

    public float getSpriteHeight() {
        return spriteHeight;
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
