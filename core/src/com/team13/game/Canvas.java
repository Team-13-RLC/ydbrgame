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

    // Fields
    /**
     * The single camera used for the game
     */
    private OrthographicCamera camera;

    /**
     * The single instance of this class.
     * Created when the program starts.
     */
    private static final Canvas instance = new Canvas();

    /**
     * Number of lanes in the game.
     */
    private static final byte numLanes = 8;

    /**
     * An array to store all the lanes
     */
    private Lane[] lanes;

    /**
     * An array to store all the boats
     */
    private Boat[] boats;

    /**
     * Class that will render the background.
     */
    BackgroundRender background;


    // Constructors
    /**
     * Private constructor to prevent it from being called from outside of the class
     */
    private Canvas(){}


    // Methods
    /**
     * Called once to initialise everything to do with the canvas, the lanes and boats.
     * Initializes the camera and sets its position. Initializes the background. Makes the boats. Makes the lanes.
     *
     * @see mainGame#create()
     */
    public void create(){
        camera = new OrthographicCamera(mainGame.Resolution.WIDTH, mainGame.Resolution.HEIGHT );
        // sets position of the camera such that it covers the whole screen
        // Ok, I haven't managed to figure out why it does this. It's off by 100. This is not the way to fix this, but we're running out of time.
        camera.position.set(mainGame.Resolution.WIDTH/2f +100, mainGame.Resolution.HEIGHT/2f, 0f);
        // NOTE: very important, camera will not do anything until his is called.
        camera.update();
        background = new BackgroundRender();
        makeLanes();
        makeBoats();
    }

    /**
     * Called every frame to update everything to do with the canvas.
     * Clears the screen with a color (for some reason). Updates (scrolls) the background. Draws boats and lanes.
     * Updates camera position.
     *
     * @see mainGame#render().
     */
    public void update(){
        Gdx.gl.glClearColor(0.2F, 0.3F, 0.9F, 1);

        // Some OpenGL thing, not really sure.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        background.update((int)camera.position.y);
        drawLanes();
        updateBoats();
        updateCamera();
    }


    /**
     * Initialises the lanes.
     * Allocates memory in the lanes array. Initializes all the Lane instances into the array with correct dimensions.
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
                    // Create user lane
                    lanes[lane] = new UserLane(lane* (mainGame.Resolution.WIDTH/numLanes), (lane+1)* (mainGame.Resolution.WIDTH/numLanes));
                    userLaneSet = true;
                    continue;
                }
            }
            // Otherwise just make a normal lane
            lanes[lane] = new Lane(lane* (mainGame.Resolution.WIDTH/numLanes), (lane+1)* (mainGame.Resolution.WIDTH/numLanes));
        }
    }

    /**
     * Initialises the boats.
     * Allocates memory in the boats array. Initializes all the Bane instances into the array.
     */
    private void makeBoats(){
        // Number of boats should roughly equal the number of lanes
        boats = new Boat[numLanes];

        for (int boat =0; boat < numLanes; boat++){
            if (lanes[boat] instanceof UserLane){
                // TODO: add some sort of a StatsGenerator class to generate stats for everything
                // This Stats variable is for testing, to be removed
                Stats userStats = new Stats(0.04F, 5, 3, 10, 0 );
                boats[boat] = new UserBoat(new Position(lanes[boat].getMiddle(), 0), userStats);
                boats[boat].setBoatPosition(new Position(lanes[boat].getMiddle() - boats[boat].getSprite().getBoundingRectangle().width/2f, 0));

                continue;
            }

            // TODO: generate AIBoat when that gets made
        }

    }

    /**
     * Gets boats to be drawn, also allows them to be controlled/control themselves.
     */
    private void updateBoats(){
        for (int i = 0; i < numLanes; i++){
            if (boats[i] != null) { // Will nor be required in the future once there's more then one boat
                boats[i].draw(getProjection());
                boats[i].control();
                boats[i].checkCollisions(lanes[i]);
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

    /**
     * Updates camera position so that it follows the userboat.
     */
    private void updateCamera(){
        for (Boat b : boats) {
            if (b instanceof UserBoat) {
                camera.position.y = b.getBoatPosition().getPosY() + mainGame.Resolution.HEIGHT/2;
                camera.update();
                // Important so that we don't keep going through this array after the boat in question was found.
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
