package com.team13.game.stats;

/**
 * Position class stores the X and Y coordinates.
 */
public class Position
{

    // Fields
    /**
     * Horizontal position
     */
    private float posX;

    /**
     * Vertical position
     */
    private float posY;


    // Constructors

    /**
     * Private constructor to prevent positionfrom initializing to 0,0
     */
    private Position(){}

    /**
     * Constructor takes arguments and assigns their values to the private fields.
     * @param posX Horizontal position of an object
     * @param posY Vertical position of an object
     */
    public Position(float posX, float posY)
    {
        this.posX = posX;
        this.posY = posY;
    }


    // getters

    public float getPosX()
    {
        return posX;
    }

    public float getPosY()
    {
        return posY;
    }


    // setters

    public void setPosX(float posX)
    {
        this.posX = posX;
    }

    public void setPosY(float posY)
    {
        this.posY = posY;
    }
}
