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


public class Spawn {

    protected ArrayList<Obstacle> spawned_obstacles;
    protected Random r = new Random();
    protected float last_spawned_x;
    protected Instant last_spawn_time = Instant.now();

    public Spawn(){}

    public void Update(){
        Iterator<Obstacle> iter = spawned_obstacles.iterator();
        while (iter.hasNext()){
            if(iter.next().getObstaclePosition().getPosY() < 0){
                iter.remove();
            }
        }

        if(last_spawn_time.compareTo(Instant.now()) >= 0.5){
            spawned_obstacles.add(spawn_new());
        }

    }

    public Obstacle spawn_new(){
        float canvas_height;
        Obstacle output;
        canvas_height = mainGame.Resolution.HEIGHT;
        float spawn_x = -1;

        while (spawn_x > this.getLastSpawnedx() - 50 &&
                spawn_x < this.getLastSpawnedx() + 50 &&
                spawn_x != -1) {
            spawn_x = r.nextInt(mainGame.Resolution.WIDTH + 1);

        }
        this.setLastSpawned(spawn_x);
        Position output_position = new Position(0,0);
        output = new Obstacle(output_position);
        output_position = new Position(spawn_x,canvas_height - output.getObstacleHeight());
        output.setObstaclePosition(output_position);

        return output;

    }


    //Setters

    public void setLastSpawned(float x){
        this.last_spawned_x = x;
    }



    //Getters

    public ArrayList<Obstacle> getObstacleList(){
        return spawned_obstacles;
    }
    public float getLastSpawnedx(){
        return last_spawned_x;
    }
}
