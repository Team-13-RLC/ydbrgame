package com.team13.game.obstacle;

import com.team13.game.stats.Position;

public class dynamicObstacle extends Obstacle
{

    /**
     * Directional speeds of an obstacle, in the X and Y directions.
     */
    protected float velocityX;
    protected float velocityY;


    /**
     * Inherits Obstacle class.
     * @See Obstacle
     */
    public dynamicObstacle(Position position)
    {
        super(position);
    }
}
