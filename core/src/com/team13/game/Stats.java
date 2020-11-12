package com.team13.game;

public class Stats {
    // Speed and positional values will be in a separate classes.
    // Speed limits: h = horizontal; v = vertical.
    private float maxHSpeed;
    private float maxVSpeed;
    private float acceleration;
    private float maneuverability;
    private float robustness;
    private float fatigue;

    Stats() {

    }

    // Getters

    public float getMaxHSpeed()
    {
        return maxHSpeed;
    }

    public float getMaxVSpeed()
    {
        return maxVSpeed;
    }

    public float getAcceleration()
    {
        return acceleration;
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

    public void setMaxHSpeed(float h)
    {
        maxHSpeed = h;
    }

    public void setMaxVSpeed(float v)
    {
        maxVSpeed = v;
    }

    public void setAcceleration(float a)
    {
        acceleration = a;
    }

    public void setManeuverability(float m)
    {
        maneuverability = m;
    }

    public void setRobustness(float r)
    {
        robustness = r;
    }

    public void  setFatigue(float f)
    {
        fatigue = f;
    }
}
