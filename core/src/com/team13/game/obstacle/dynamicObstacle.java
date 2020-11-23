package com.team13.game.obstacle;

import com.team13.game.stats.Position;
import com.team13.game.stats.Speed;

public class dynamicObstacle extends Obstacle
{

    /**
     * Directional speeds of an obstacle, in the X and Y directions.
     */
    protected Speed velocity = new Speed(10,0);



    /**
     * Inherits Obstacle class.
     *
     */
    public dynamicObstacle(Position position)
    {
        super(position);

    }

    /**
     * Allows the obstacle to move with a set velocity.
     */
    public void ObstacleMove(){
        Position updated = this.getObstaclePosition();
        updated.setPosX(updated.getPosX() + velocity.getVelocityx());
        this.setObstaclePosition(updated);
    }

}
