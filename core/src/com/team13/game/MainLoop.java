package com.team13.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.team13.game.scenes.Canvas;
import com.team13.game.scenes.IScene;
import com.team13.game.stats.Position;
import com.team13.game.scenes.Card;
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
    private final IScene[] scenes;

    /**
     * Timer used to time each leg
     */
    private final Timer timer;

    /**
     * Formatted strings wth times for each leg
     */
    private final String[] times;

    /**
     * Since the loop is happening outside of the class, this is needed to know the current leg of teh race.
     */
    private byte loopCounter;

    /**
     * Number of legs there will be + number of cards there will be;
     */
    private final byte numLoops = 4;

    /**
     * Keeps track of whether the timer has been started
     */
    private boolean timerStared;

    /**
     * How long the time should be on screen after each leg (in seconds)
     */
    long showTextFor = 3;

    /**
     * Is text being shown at the moment
     */
    boolean showingText = false;

    /**
     * Time when text started being rendered.
     */
    long textShowStartTime;

    /**
     * Position of text on the screen
     */
    Position textPosition;

    //Constructors
    /**
     * Private constructor, only accessible from inside of the class.
     * Creates all canvases and Cards in the scenes array.
     * Initializes the Timer, sets the loopCounter to 0, calculates text position.
     */
    private MainLoop() {
        scenes = new IScene[numLoops];
        scenes[0] = new Card(Gdx.files.internal("cards/title.png"));
        for (int canvas = 1; canvas < numLoops; canvas++) {
            scenes[canvas] = new Canvas();
        }


        timer = new Timer();
        times = new String[numLoops];
        loopCounter = 0;
        timerStared = false;
        textPosition = new Position(scenes[loopCounter].getCamera().viewportWidth/2,
                4 * scenes[loopCounter].getCamera().viewportHeight/5);
    }


    //Methods
    /**
     * Main function to activate the loop.
     * If a timer is not started, it will be started.
     * If the end has not been reached, the scene will get updated.
     * If the end of a leg has been reached, dispose() will be called.
     * If the canvas has legFinishedCorrectly flag set to false, the game over screen will be shown.
     * If The flag had ot been set, and teh last leg was finished, the times for each leg are shown.
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
            if (!scenes[loopCounter].isEnd()) {
                scenes[loopCounter].update();
            } else {
                if (scenes[loopCounter] instanceof Canvas){
                    if (scenes[loopCounter].getLegFinishedCorrectly()){
                        legFinished();
                    } else {
                        gameFinishedWithCrash();
                    }
                } else {
                    cardFinished();
                }
            }
        } else{
            gameFinishedProperly();
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
        for (IScene c : scenes) {
            c.resize(width, height);
        }
    }

    /**
     * Called when a card no longer needs to be displayed.
     * Disposes of teh card and increases teh loop counter.
     */
    private void cardFinished() {
        scenes[loopCounter].dispose();
        loopCounter ++;
    }

    /**
     * Called when one leg is over.
     * Stops the timer, adds any extra penalties, shows the time for teh current leg on the screen.
     * Disposes of the Canvas and increments loop counter.
     */
    private void legFinished(){
        if (!showingText) {
            timer.stop();
            timer.addTime(scenes[loopCounter].getUserBoatPenalties());
            textShowStartTime = System.currentTimeMillis();
            times[loopCounter] = timer.getTimeFormatted();
            showingText = true;
        } else if ((System.currentTimeMillis() - textShowStartTime) / 1000 < showTextFor){
            TextRenderer.print(timer.getTimeFormatted(), textPosition.getPosX(), textPosition.getPosY(), scenes[loopCounter].getCamera(), 10);
        } else {
            scenes[loopCounter].dispose();
            showingText = false;
            timerStared = false;
            loopCounter ++;
        }
    }

    /**
     * Called after all legs have been completed.
     * Clears the screen with a black fill and displays times of all legs.
     */
    private void gameFinishedProperly(){
        Gdx.gl.glClearColor(0F, 0F, 0F, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        TextRenderer.print("Your times", textPosition.getPosX(), textPosition.getPosY() , scenes[numLoops - 1].getCamera(), 5);
        for (int i = 0; i < times.length; i++) {
            if (times[i] != null) {
                TextRenderer.print(times[i], textPosition.getPosX(), textPosition.getPosY() - (mainGame.Resolution.HEIGHT/10F)*i, scenes[numLoops - 1].getCamera(), 5);
            }
        }
    }

    /**
     * Called after the boat has crashed.
     * Clears the screen with a black fill and displays the "You lost" message.
     */
    private void gameFinishedWithCrash(){
        Gdx.gl.glClearColor(0F, 0F, 0F, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        TextRenderer.print("You lost", textPosition.getPosX(), textPosition.getPosY() , scenes[numLoops - 1].getCamera(), 5);
            TextRenderer.print(":(", textPosition.getPosX(), textPosition.getPosY() - (mainGame.Resolution.HEIGHT/10F), scenes[numLoops - 1].getCamera(), 5);
    }

    // Getters
    public static MainLoop getInstance() {
        return instance;
    }

}