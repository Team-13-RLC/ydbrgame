package com.team13.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.team13.game.utils.*;
import com.team13.game.boat.AIBoat;
import com.team13.game.boat.Boat;
import com.team13.game.boat.UserBoat;
import com.team13.game.lane.Lane;
import com.team13.game.lane.UserLane;
import com.team13.game.mainGame;
import com.team13.game.obstacle.Spawn;
import com.team13.game.stats.Position;
import com.team13.game.stats.Stats;

import java.util.Random;

/**
 * Class to manage the camera, background and sprite drawing.
 */
public class Canvas implements IScene {

    // Fields
    /**
     * The camera used for the canvas.
     */
    private final OrthographicCamera camera;

    /**
     * ViewPort used for the game
     */
    private final FillViewport viewport;


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
    private final BackgroundRender background;

    /**
     * The line which the boats need to cross to win
     */
    private final FinishLine finishLine;

    /**
     * How long the race is
     */
    private final float raceLength = 8000;

    /**
     * Used to spawn and keep track of all obstacles.
     */
    private final Spawn obstacleSpawner;

    /**
     * Flag to make sure the UserBoat finished the race and did not crash.
     */
    private boolean legFinishedCorrectly;


    // Constructors
    /**
     * Initializes the camera, the viewport, the background, the finish line and the obstacle spawner.
     *
     * Also creates all boats and all lanes.
     * @see BackgroundRender
     * @see FinishLine
     * @see Spawn
     */
    public Canvas() {
        camera = new OrthographicCamera();
        viewport = new FillViewport(mainGame.Resolution.WIDTH, mainGame.Resolution.HEIGHT, camera);
        camera.position.set(camera.viewportWidth / 2 + 100, camera.viewportHeight / 2, 0f);
        viewport.apply();
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        background = new BackgroundRender();
        finishLine = new FinishLine(raceLength);
        obstacleSpawner = new Spawn();
        legFinishedCorrectly = true;
        makeLanes();
        makeBoats();
    }


    // Methods

    /**
     * Called every frame to update everything to do with the canvas.
     * Clears the screen with a color. Updates (scrolls) the background. Draws boats and lanes.
     * Checks if the leg is getting close to the end so the FinishLine can be displayed.
     * Updates camera position.
     *
     * @see mainGame#render().
     */
    public void update() {
        Gdx.gl.glClearColor(0.2F, 0.3F, 0.9F, 1);

        // Some OpenGL thing, not really sure.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        background.update(camera);
        obstacleSpawner.update(camera);
        drawLanes();
        checkForEnd();
        updateBoats();
        updateCamera();
    }


    /**
     * Cals dispose on the background, all boats and all lanes.
     */
    public void dispose() {
        background.dispose();
        for (Lane l : lanes) {
            l.dispose();
        }
        for (Boat b : boats) {
            // Temporary check because no AI boats
            if (b != null) {
                b.dispose();
            }
        }
    }

    /**
     * Initialises the lanes.
     * Allocates memory in the lanes array. Initializes all the Lane instances into the array with correct dimensions.
     * Randomly chooses one lane to be the UserLane.
     * @see Lane
     */
    private void makeLanes() {
        lanes = new Lane[numLanes];
        boolean userLaneSet = false;
        Random userLaneChooser = new Random();
        for (int lane = 0; lane < numLanes; lane++) {
            // check if a UserLane was already created
            if (!userLaneSet) {
                // choose a number between 0 and 1
                if (userLaneChooser.nextInt(2) == 1) {
                    // Create user lane
                    lanes[lane] = new UserLane(lane * ((int) camera.viewportWidth / numLanes), (lane + 1) * ((int) camera.viewportWidth / numLanes));
                    userLaneSet = true;
                    continue;
                }
            }
            // Otherwise just make a normal lane
            lanes[lane] = new Lane(lane * ((int) camera.viewportWidth / numLanes), (lane + 1) * ((int) camera.viewportWidth / numLanes));
        }

    }

    /**
     * Initialises the boats.
     *
     * Allocates memory in the boats array. Initializes all the Lane instances into the array.
     * If the Lane is a UserLane, a UserBoat is placed into that Lane.
     * For AI boats, the Stats are generated with teh StatsFactory. The texture is chosen by the TexturePicker.
     * @see UserBoat
     * @see AIBoat
     * @see StatsFactory
     * @see TexturePicker
     */
    private void makeBoats() {
        // Number of boats should roughly equal the number of lanes
        boats = new Boat[numLanes];

        TexturePicker picker = new TexturePicker("textures/aitextures/", "aiboattexturelist.txt");
        for (int boat = 0; boat < numLanes; boat++) {
            if (lanes[boat] instanceof UserLane) {
                Stats userStats = new Stats(0.04F, 5, 3, 100, 0);
                boats[boat] = new UserBoat(new Position(lanes[boat].getMiddle(), 0), userStats);
                boats[boat].setBoatPosition(new Position(lanes[boat].getMiddle() - boats[boat].getSprite().getBoundingRectangle().width / 2f, 0));
                continue;
            }
            boats[boat] = new AIBoat(new Position(lanes[boat].getMiddle(), 0), StatsFactory.make_stats(), 50, lanes[boat], picker.pick());
            boats[boat].setBoatPosition(new Position(lanes[boat].getMiddle() - boats[boat].getSprite().getBoundingRectangle().width / 2f, 0));
        }

    }

    /**
     * Gets boats to be drawn, allows them to be controlled/control themselves and checks for collisions.
     */
    private void updateBoats() {
        for (int i = 0; i < numLanes; i++) {
            boats[i].draw(camera.combined);
            boats[i].checkCollisions(lanes[i], obstacleSpawner);
            if (boats[i].getBoatPosition().getPosY() < (raceLength - boats[i].getSpriteHeight())) {
                boats[i].control(obstacleSpawner);
            } else {
                boats[i].update();
            }
        }
    }

    /**
     * Draws every Lane in the lanes array.
     */
    private void drawLanes() {
        for (int i = 0; i < numLanes; i++) {
            lanes[i].draw(camera);
        }
    }

    /**
     * Checks if the FinishLine is close, then renders it.
     * @see FinishLine
     */
    public void checkForEnd() {
        if (camera.position.y > raceLength - camera.viewportHeight) {
            finishLine.draw(camera.combined);
        }
    }

    /**
     * Checks if a leg is finished, or the boat has crashed.
     *
     * If the UserBoat crashed the legFinishedCorrectly flag is set to false.
     * @return true if this is the end of the leg or the UserBoat has crashed, false otherwise.
     */
    public boolean isEnd() {
        for (Boat b : boats) {
            if (camera.position.y > raceLength - camera.viewportHeight) {
                if (b instanceof UserBoat) {
                    if (b.getBoatPosition().getPosY() > raceLength && b.getBoatStats().getSpeed() == 0) {
                        return true;
                    }
                }
            }else if ( b instanceof UserBoat && b.getBoatStats().getRobustness() < 0) {
                legFinishedCorrectly = false;
                return true;
            }
        }
        return false;
    }

    /**
     * Updates camera position so that it follows the UserBoat.
     * Prints boat's current health.
     */
    private void updateCamera() {
        for (Boat b : boats) {
            if (b instanceof UserBoat) {
                camera.position.y = b.getBoatPosition().getPosY() + camera.viewportHeight / 2;
                camera.position.x = camera.viewportWidth / 2 + 100;
                viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                String printRobustness = "HP: " + b.getBoatStats().getRobustness();
                TextRenderer.print(printRobustness, mainGame.Resolution.WIDTH / 16F, mainGame.Resolution.HEIGHT / 1.5F, camera, 2);
                break;
            }
        }
    }


    /**
     * Function given to the ApplicationAdapter#resize() function in mainGame
     * @param width  window width
     * @param height window height
     * @see mainGame#resize(int width, int height)
     */
    public void resize(int width, int height) {
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth / 2 + 100, camera.viewportHeight / 2, 0f);
    }

    // Getters


    /**
     * Returns all penalties received by the UserBoat.
     * @return  all UserBoat penalties
     */
    public long getUserBoatPenalties() {
        for (Boat b : boats) {
            if (b instanceof UserBoat) {
                return b.getPenalties();
            }
        }
        return 0;
    }

    public final Camera getCamera() {
        return camera;
    }

    public boolean getLegFinishedCorrectly() {
        return legFinishedCorrectly;
    }
}