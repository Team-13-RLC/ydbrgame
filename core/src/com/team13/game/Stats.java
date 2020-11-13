package com.team13.game;

public class Stats {

    // maneuverability = horizontal speed
    private float speed;
    private float acceleration;
    private float maxSpeed;
    private float maneuverability;
    private float robustness;
    private float fatigue;

    // Default constructor defined implicitly

    // Constructor which takes arguments assigns the values of those arguments to the private variables
    Stats(float speed, float acceleration, float maxSpeed, float maneuverability, float robustness, float fatigue) {
       this.speed = speed;
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

    public float getAcceleration()
    {
        return acceleration;
    }

    public float getMaxSpeed()
    {
        return maxSpeed;
    }

    public float getManeuverability()
    {
        return maneuverability;
    }

    public float getRobustness()
    {
        return robustness;
    }

    public float getFatigue()
    {
        return fatigue;
    }


    // Setters

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setAcceleration(float a)
    {
        this.acceleration = a;
    }

    public void setMaxSpeed(float v)
    {
        this.maxSpeed = v;
    }

    public void setManeuverability(float m)
    {
        this.maneuverability = m;
    }

    public void setRobustness(float r)
    {
        this.robustness = r;
    }

    public void  setFatigue(float f)
    {
        this.fatigue = f;
    }
}