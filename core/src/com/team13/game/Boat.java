package com.team13.game;

public class Boat {
    private float posX;
    private float posY;
    private float h_speed;
    private float v_speed;
    private float acceleration;
    private final int speed_limit;

    Boat(){
        // These values can be grouped into an array
        posX = 0;
        posY = 0;


        acceleration = 1.1F;
        speed_limit = 100;

        h_speed = 5;
        v_speed = 0;

    }


    public void accelerate(int direction){
        if (v_speed < speed_limit) {
            this.v_speed += (this.acceleration * direction);
        }
    }
//  Getters

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }


    public float getH_speed() {
       return h_speed;
    }

    public float getV_speed() {
        return v_speed;
    }

    public float getAcceleration() {
        return acceleration;
    }

    // Setters
    public void setPosX(float posX) {
        this.posX = posX;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }


    public void setH_speed(float h_speed) {
        this.h_speed = h_speed;
    }

    public void setV_speed(float v_speed) {
        this.v_speed = v_speed;
    }


    // Direction used with acceleration
    static class Direction{
        public static final int FORWARDS = 1;
        public static final int BACKWARDS = -1;

    }
}

