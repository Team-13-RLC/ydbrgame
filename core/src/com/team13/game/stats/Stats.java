package com.team13.game.stats;

import java.util.UUID;

public class Stats {

    // Methods
    /**
     * Speed of the boat. Always initialized to 0.
     */
    private float speed = 0;

    /**
     * How much the speed changes when the user presses UP or DOWN
     *
     * @see com.team13.game.boat.UserBoat.Keymap
     */
    private float acceleration;

    /**
     * How much the speed changes when the user does not press anything.
     * Could have been called drag for clarity. Is constant.
     * Note: yes, this could be simplified by just returning the value from getDeceleration(),
     * but this way makes it more obvious where this value is coming from.
     */
    private final float deceleration = 0.02F;

    /**
     * Upper bound beyond which the boat will not accelerate.
     */
    private float maxSpeed;

    /**
     * Boat's horizontal speed.
     */
    private float maneuverability;

    /**
     * How many times the boat needs to hit an obstacle before brwaking.
     */
    private float robustness;

    /**
     * How much the stats change between legs
     */
    private float fatigue;


    // Constructors
    /**
     * Private constructor to prevent all stats from being 0.
     */
    private Stats(){}

    /**
     * Initialises all variables from parameters.
     *
     * @param acceleration Initial Acceleration
     * @param maxSpeed Initial maximum speed
     * @param maneuverability Initial horizontal velocity
     * @param robustness Initial robustness.
     * @param fatigue Initial fatigue
     */
    public Stats( float acceleration, float maxSpeed, float maneuverability, float robustness, float fatigue) {
       this.acceleration = acceleration;
       this.maxSpeed = maxSpeed;
       this.maneuverability = maneuverability;
       this.robustness = robustness;
       this.fatigue = fatigue;
    }


    // Getters
    public float getSpeed() {
        return speed;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public float getDeceleration() {
        return deceleration;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public float getManeuverability() {
        return maneuverability;
    }

    public float getRobustness() {
        return robustness;
    }

    public float getFatigue() {
        return fatigue;
    }


    // Setters
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setAcceleration(float a) {
        this.acceleration = a;
    }

    public void setMaxSpeed(float v) {
        this.maxSpeed = v;
    }

    public void setManeuverability(float m) {
        this.maneuverability = m;
    }

    public void setRobustness(float r) {
        this.robustness = r;
    }

    public void  setFatigue(float f) {
        this.fatigue = f;
    }
}