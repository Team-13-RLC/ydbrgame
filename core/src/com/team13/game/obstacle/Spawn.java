package com.team13.game.obstacle;
import com.badlogic.gdx.graphics.Camera;
import com.team13.game.mainGame;
import com.team13.game.stats.Position;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class designed to manage the spawning of obstacles.
 */
public class Spawn {
    /**
     * Holds all obstacles currently on the canvas.
     */
    protected ArrayList<Obstacle> spawned_obstacles;
    /**
     * Random object for generating random numbers.
     */
    protected Random random = new Random();
    /**
     * Holds the x co-ordinate of the last spawned obstacle.
     */
    protected float last_spawned_x;
    /**
     * Holds the time that the last obstacle was spawned.
     * On startup this is counted as the time the program was run.
     */
    protected long last_spawn_time;

    /**
     * An Array of strings that holds all current types of obstacles available.
     */
    protected String[] obstacle_types = {"Duck", "Goose", "Rock", "treeBranch"};

    /**
     * Public constructor so that it can be called whenever needed.
     */
    public Spawn(){
        spawned_obstacles = new ArrayList<>();
        last_spawn_time = System.currentTimeMillis();
    }


    /**
     * Void class that decides when to spawn and when to de-spawn objects.
     */
    public void update(final Camera camera){
        spawned_obstacles.removeIf(obstacle -> obstacle.getObstaclePosition().getPosY() < 0 || obstacle.getObstaclePosition().getPosX() < -1 ||
                obstacle.getObstaclePosition().getPosX() > mainGame.Resolution.WIDTH + 1);

        for (Obstacle o : spawned_obstacles) {
            o.draw(camera.combined);
        }

        //Currently using a half second increment between spawning of objects.
        if(System.currentTimeMillis() - last_spawn_time >= 500){
            spawned_obstacles.add(spawn_new(getRandomObstacleType(getObstacle_types()), camera));
            setLast_spawn_time(System.currentTimeMillis());
        }

    }

    /**
     * The spawn_new() class is used to spawn a new Obstacle object of the designated
     * subtype.
     * @param obstacle_type A string that tells the function which obstacle type to create.
     * @return Returns the new Obstacle object of the designated type.
     */
    public Obstacle spawn_new(String obstacle_type, final Camera camera){
        float canvas_height;
        Obstacle output;
        canvas_height = mainGame.Resolution.HEIGHT;
        float spawn_x = -1;
        boolean temp_bool = true;

        while (temp_bool) {
            if(spawn_x == -1) {
                spawn_x = random.nextInt(mainGame.Resolution.WIDTH + 1);
            }else if(spawn_x < getLastSpawnedx() + 50 && spawn_x > getLastSpawnedx() - 50){
                spawn_x = random.nextInt(mainGame.Resolution.WIDTH + 1);
            }else{
                temp_bool = false;
            }


        }
        setLastSpawned(spawn_x);
        Position output_position = new Position(0,0);

        output = new Obstacle(output_position);

        //If new obstacle types added, this must be changed.
        //It's currently case sensitive.
        switch (obstacle_type) {
            case "Duck":
                output = new Duck(output_position);
                break;
            case "Goose":
                output = new Goose(output_position);
                break;
            case "Rock":
                output = new Rock(output_position);
                break;
            case "treeBranch":
                output = new treeBranch(output_position);
                break;
        }


        output_position = new Position(spawn_x,(mainGame.Resolution.HEIGHT/2F + camera.position.y) - output.getObstacleHeight());
        output.setObstaclePosition(output_position);

        return output;

    }


    //Setters

    /**
     * Used to set the last_spawned_x variable to a new value.
     * @param x the x co-ordinate that you want to set.
     */
    public void setLastSpawned(float x){
        this.last_spawned_x = x;
    }

    public void setLast_spawn_time(Long time){
        this.last_spawn_time = time;
    }


    //Getters

    /**
     * Returns the spawn_obstacles list.
     * @return spawn_obstacles
     */
    public ArrayList<Obstacle> getObstacleList(){
        return spawned_obstacles;
    }

    /**
     * Returns the x co-ordinate of the last spawned obstacle.
     * @return last_spawned_x
     */
    public float getLastSpawnedx(){
        return last_spawned_x;
    }

    /**
     * Returns the array of obstacle types.
     * @return obstacle_types an Array.
     */
    public String[] getObstacle_types(){
        return obstacle_types;
    }

    /**
     * Takes an Array of strings and returns a random value from the array.
     * @param types An array of string (intended for types of obstacle available).
     * @return returns a random value from inputted array.
     */
    public String getRandomObstacleType(String[] types){
        return types[random.nextInt(types.length)];
    }
}
