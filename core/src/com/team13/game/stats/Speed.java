package com.team13.game.stats;

public class Speed {
    /**
     * Holds the speed as a float.
     */

    private float velocityx;
    private float velocityy;

    /**
     *
     * @param velocityx float x velocity of the object.
     * @param velocityy float y velocity of the object.
     */
    public Speed(float velocityx, float velocityy){

        this.velocityx = velocityx;
        this.velocityy = velocityy;
    }

    //getters

    /**
     * Gets the speed variable.
     * @return returns speed as a float.
     */
    public float getVelocityx(){
        return velocityx;
    }

    public float getVelocityy(){return velocityy;}

    //setters

    /**
     * Sets the speed to new_speed.
     * @param new_speed float the new velocity you want.
     */
    public void setVelocityx(float new_speed){

        this.velocityx = new_speed;
    }
    /**
     * Sets the speed to new_speed.
     * @param new_speed float the new velocity you want.
     */
    public void setVelocityy(float new_speed){

        this.velocityy = new_speed;
    }


}
