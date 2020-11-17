package com.team13.game.boat;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.team13.game.stats.Position;
import com.team13.game.stats.Stats;

public class Boat {
    //boat position
    Position boatPosition;
    //horizontal and vertical speed
    Stats boatStats;
    Texture boatTexture;
    SpriteBatch batch;
    Sprite boatSprite;

    Boat(){
        /*
        Initial values
        */ 
        boatPosition = new Position(0, 0);
        boatStats = new Stats(0, 1.1F,100, 5, 10, 0 );
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


//  Getters

    public Position getBoatPosition() {
        return boatPosition;
    }


    public Stats getBoatStats() {
        return boatStats;
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

