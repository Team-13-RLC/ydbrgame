package com.team13.game;


/**
 * Singleton class which creates the game loop.
 */
public class MainLoop {

    // Fields
    /**
     * The single instance of MainLoop
     */
    private static final MainLoop instance = new MainLoop();

    /**
     * Array holding all the canvases.
     * One per leg of teh race.
     */
    private final Canvas[] canvases;

    private Timer timer;

    private String[] times;

    /**
     * Since the loop is happening outside of the class, this is needed to know the current leg of teh race.
     */
    private byte loopCounter;

    /**
     * Number of legs there will be.
     */
    private final byte numLoops = 3;

    /**
     * Keeps track of whether the canvas has been created for the current leg.
     */
    private boolean timerStared;


    //Constructors
    /**
     * Private constructor, only accessible from inside of teh class.
     * Creates all canvases in the canvases array.
     */
    private MainLoop() {
        canvases = new Canvas[numLoops];
        for (int canvas = 0; canvas < numLoops; canvas++) {
            canvases[canvas] = new Canvas();
        }
        timer = new Timer();
        times = new String[numLoops];
        loopCounter = 0;
        timerStared = false;
    }


    //Methods
    /**
     * Main function to activate the loop.
     * If a canvas is not created, it will get created.
     * If the end of a leg has not been reached, the canvas will get updated.
     * If the end of the game has been reached, dispose() will be called.
     */
    public void run(){
        if (loopCounter < numLoops){
            // Crate the canvas
            if (!timerStared) {
                timer.reset();
                timer.start();
                timerStared = true;
            }

            // update the canvas
            if (!canvases[loopCounter].checkForEnd()) {
                canvases[loopCounter].update();
            } else {
                // Dispose of canvas
                canvases[loopCounter].dispose();
                timer.stop();
                timer.addTime(canvases[loopCounter].getUserBoatPenalties());
                times[loopCounter] = timer.getTimeFormatted();
                timerStared = false;
                loopCounter ++;
            }
        }
    }

    /**
     * Function given to the ApplicationAdapter#resize() function in mainGame
     *
     * @param width window width
     * @param height window height
     * @see com.badlogic.gdx.ApplicationAdapter#resize(int width, int height)
     * @see mainGame#resize(int width, int height)
     */
    public void resize(int width, int height) {
        for (Canvas c : canvases) {
            c.resize(width, height);
        }
    }


    // Getters
    public static MainLoop getInstance() {
        return instance;
    }

}