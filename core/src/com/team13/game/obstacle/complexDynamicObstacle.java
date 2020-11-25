package com.team13.game.obstacle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.team13.game.boat.Boat;
import com.team13.game.boat.UserBoat;
import com.team13.game.lane.Lane;
import com.team13.game.lane.UserLane;
import com.team13.game.mainGame;
import com.team13.game.stats.Position;
import com.team13.game.stats.Stats;
import com.team13.game.obstacle.Obstacle;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Obstacle with more intricate movement.
 */
public class complexDynamicObstacle extends dynamicObstacle
{

    /**
     * Calls the constructor of dynamicObstacle with the position argument.
     * @param position position of the Obstacle.
     */
    public complexDynamicObstacle(Position position)
    {
        super(position);
    }



    /*
      Allows the obstacle to move and bounce on edges.
     */
    @Override
    public void ObstacleMove(){
        if(this.getObstaclePosition().getPosX() == 0 ||
                this.getObstaclePosition().getPosX() == mainGame.Resolution.WIDTH){
            velocity.setVelocityx(velocity.getVelocityx()*-1);
        }
        Position updated = this.getObstaclePosition();
        updated.setPosX(updated.getPosX() + velocity.getVelocityx());
        this.setObstaclePosition(updated);
    }

    @Override
    public void move(){
        ObstacleMove();
    }
}
