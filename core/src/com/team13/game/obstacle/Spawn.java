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

import java.util.ArrayList;
import java.util.Random;

public class Spawn {

    private ArrayList<Obstacles> spawned_obstacles;
    private Random r = new Random();

    public Spawn(){}

    public void Update(){

    }

    public void spawn_new(Obstacle obstacle){
        int canvas_height;

        canvas_height = mainGame.Resolution.HEIGHT;

        int spawn_x = r.nextInt(mainGame.Resolution.WIDTH + 1);
    }

}
