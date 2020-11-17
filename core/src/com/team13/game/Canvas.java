package com.team13.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.team13.game.boat.Boat;
import com.team13.game.boat.UserBoat;
import com.team13.game.lane.Lane;
import com.team13.game.lane.UserLane;
import com.team13.game.stats.Position;
import com.team13.game.stats.Stats;

import java.util.Random;

/**
 * Singleton class to manage the camera, background and (possibly) sprite drawing.
 */
public class Canvas {
    // the camera used for the game (there's only one)
    private OrthographicCamera camera;
    // This is required as there can only be one instance of this class
    private static Canvas instance = new Canvas();
    private static final byte numLanes = 8;
    // Array to store the lanes
    private Lane[] lanes;
    private Boat[] boats;

    BackgroundRender background;
    /**
     * Private constructor to prevent it from being called from outside of the class
     */
    private Canvas(){}


    /**
     * Called once to initialise everything to do with the canvas (also at the moment the lanes).
     * Similar to mainGame.create().
     */
    public void create(){
        camera = new OrthographicCamera(mainGame.Resolution.WIDTH, mainGame.Resolution.HEIGHT );
        // sets position of the camera such that it covers the whole screen
        camera.position.set(mainGame.Resolution.WIDTH/2f, mainGame.Resolution.HEIGHT/2f, 0f);
        // NOTE: very important, camera will not do anything until his is called.
        camera.update();
        background = new BackgroundRender(getProjection());
        makeLanes();
        makeBoats();
    }
    // Gets called every frame to update the canvas
    /**
     * Called every frame to update everything to do with the canvas. Similar to mainGame.render().
     */
    public void update(){
        // Clear the screen with a certain color
        Gdx.gl.glClearColor(0.2F, 0.3F, 0.9F, 1);

        // Some OpenGL thing, not really sure.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        background.update((int)camera.position.y);
        drawLanes();
        drawBoats();
        updateCamera();
    }


    /**
     * Initialises the lanes.
     * Allocates memory in the lanes array. Initializes all the Lane instances into the array with correct dimensions
     */
    private void makeLanes(){
        lanes = new Lane[numLanes];
        boolean userLaneSet = false;
        Random userLaneChooser = new Random();
        for (int lane = 0; lane < numLanes; lane++){
            // check if a UserLane was already created
            if (!userLaneSet){
                // choose a number between 0 and 1
                if (userLaneChooser.nextInt(2) == 1){
                    lanes[lane] = new UserLane(lane* (mainGame.Resolution.WIDTH/numLanes), (lane+1)* (mainGame.Resolution.WIDTH/numLanes));
                    userLaneSet = true;
                    continue;
                }
            }
            lanes[lane] = new Lane(lane* (mainGame.Resolution.WIDTH/numLanes), (lane+1)* (mainGame.Resolution.WIDTH/numLanes));
        }
    }


    private void makeBoats(){
        // Number of boats should roughly equal the number of lanes
        boats = new Boat[numLanes];

        for (int boat =0; boat < numLanes; boat++){
            if (lanes[boat] instanceof UserLane){
                // TODO: add some sort of a StatsGenerator class to generate stats for everything
                // This Stats variable is for testing, to be removed
                Stats userStats = new Stats(0.4F, 0.2F, 10, 3, 10, 0 );
                boats[boat] = new UserBoat(new Position(lanes[boat].getMiddle(), 10), userStats);
                boats[boat].setBoatPosition(new Position(boats[boat].getBoatPosition().getPosX() + boats[boat].getSprite().getRegionWidth()/(4 / boats[boat].getScale()) , 0));
                System.out.println(boats[boat].getSprite().getRegionWidth());
                System.out.println(boats[boat].getBoatPosition().getPosX());

                continue;
            }

            // TODO: generate AIBoat when that gets made
        }

    }

    private void drawBoats(){
        for (int i = 0; i < numLanes; i++){
            if (boats[i] != null) {
                boats[i].draw(getProjection());
                boats[i].control();
            }
        }
    }

    /**
     * Draws every Lane in the lanes array.
     */
    private void drawLanes(){
        for (int i = 0; i < numLanes; i++){
            lanes[i].draw();
        }
    }

    private void updateCamera(){
        for (Boat b :
                boats) {
            if (b instanceof UserBoat) {
                camera.position.y = b.getBoatPosition().getPosY() + mainGame.Resolution.HEIGHT/2;
                camera.update();
                break;
            }

        }

    }


    // Getters
    public static Canvas getInstance() {
        return instance;
    }

    /**
     * Gets the camera projection. Used to transform screen space coordinates to world space coordinates.
     * @return Matrix with the projection for the camera
     */
    public Matrix4 getProjection() {
        return camera.combined;
    }

    public Lane[] getLanes() {
        return lanes;
    }
}
