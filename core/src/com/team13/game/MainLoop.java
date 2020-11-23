package com.team13.game;


import com.team13.game.stats.Position;
import com.team13.game.utils.TextRenderer;
import com.team13.game.utils.Timer;

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

    private final Timer timer;

    private final String[] times;

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

    long showTextFor = 3;

    boolean showingText = false;

    long textShowStartTime;

    Position textPosition;

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
        textPosition = new Position(canvases[loopCounter].getCamera().viewportWidth/2,
                4 * canvases[loopCounter].getCamera().viewportHeight/5);
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
                legFinished();
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


    private void legFinished(){
        if (!showingText) {
            timer.stop();
            timer.addTime(canvases[loopCounter].getUserBoatPenalties());
            textShowStartTime = System.currentTimeMillis();
            times[loopCounter] = timer.getTimeFormatted();
            showingText = true;
        } else if ((System.currentTimeMillis() - textShowStartTime) / 1000 < showTextFor){
            TextRenderer.print(timer.getTimeFormatted(), textPosition.getPosX(), textPosition.getPosY(), canvases[loopCounter].getCamera(), 10);
        } else {
            canvases[loopCounter].dispose();
            showingText = false;
            timerStared = false;
            loopCounter ++;
        }
    }


    // Getters
    public static MainLoop getInstance() {
        return instance;
    }

}