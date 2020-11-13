package com.team13.game;

import com.team13.game.stats.Position;
import com.team13.game.stats.Stats;

public class Boat {
    Position boatPosition;
    Stats boatStats;

    /**
     * Constructor with no parameters will initialize all values to "sensible defaults".
     */
    Boat(){
        boatPosition = new Position(0, 0);
        boatStats = new Stats(0, 1.1F,100, 5, 10, 0 );
    }


    /**
     * Causes the speed of the boat to increase. If the boat's current speed if less then its maximum speed,
     * the speed will be changed. If direction passed is Direction.FORWARDS, acceleration is added to the speed,
     * subtracted otherwise.
     *
     * @param direction Is the boat moving backwards or forwards
     * @see Direction
     */
    public void accelerate(int direction){
    /* the boat cannot accelerate more than speed limit,
    using direction to determine acceleration and deceleration
    */
        if (boatStats.getSpeed() < boatStats.getMaxSpeed()) {
            boatStats.setSpeed(boatStats.getSpeed() + boatStats.getAcceleration() * direction);
        }
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

    /**
     * Used to give names to the values 1 and -1. Avoids confusion when accelerate() is called
     *
     * @see Boat#accelerate(int)
     */
    static class Direction{
        public static final int FORWARDS = 1;
        public static final int BACKWARDS = -1;

    }
}

