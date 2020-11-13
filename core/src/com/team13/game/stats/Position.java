package com.team13.game.stats;

public class Position
{
    private float posX;
    private float posY;

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
