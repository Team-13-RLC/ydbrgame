package com.team13.game.boat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.team13.game.stats.Position;
import com.team13.game.stats.Stats;

public class Boat {
    //boat position
    protected Position boatPosition;
    //horizontal and vertical speed
    protected Stats boatStats;
    protected Texture boatTexture;
    protected SpriteBatch batch;
    protected Sprite boatSprite;
    protected float spriteScale;

    public Boat(){
        /*
        Initial values
        */ 
        batch = new SpriteBatch();
    }

    /* the boat cannot accelerate more than speed limit,
    using direction to determine acceleration and deceleration
    */

    public void accelerate(int direction){
        if (boatStats.getSpeed() < boatStats.getMaxSpeed()) {
            boatStats.setSpeed(boatStats.getSpeed() + boatStats.getAcceleration() * direction);
        }

    }

    public void draw(Matrix4 projectionMatrix){
        batch.setProjectionMatrix(projectionMatrix);
        batch.begin();
        boatSprite.draw(batch);
        boatSprite.setPosition(boatPosition.getPosX(), boatPosition.getPosY());
        batch.end();
    }

    public void control(){}


    public void update(){
        /* when boat is moving but no keys are pressed,
        the boat is slowly decelerating until it almost becomes stationary
        */
        if (boatStats.getSpeed() > 0){
            boatStats.setSpeed(boatStats.getSpeed() - boatStats.getDeceleration());
            boatPosition.setPosY(boatPosition.getPosY() + boatStats.getSpeed());
        } else {
            boatStats.setSpeed(0);
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


    // using 1 or -1 to determine the boat is accelerating or decelerating
    static class Direction{
        public static final int FORWARDS = 1;
        public static final int BACKWARDS = -1;

    }
}
