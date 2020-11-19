package com.team13.game.stats;

public class Speed {
    /**
     * Holds the speed as a float.
     */

    private float speed;

    /**
     * Initialises the speed variable from the parameter.
     * @param speed
     */

    public Speed(float speed){
        this.speed = speed;
    }

    //getters

    /**
     * Gets the speed variable.
     * @return returns speed as a float.
     */
    public float getSpeed(){
        return speed;
    }

    //setters

    /**
     * Sets the speed to new_speed.
     * @param new_speed
     */
    public void setSpeed(float new_speed){
        this.speed = new_speed;
    }

}
