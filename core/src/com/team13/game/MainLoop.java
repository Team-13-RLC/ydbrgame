package com.team13.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.team13.game.stats.Position;
import com.team13.game.utils.Card;
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

    private final Timer timer;

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

    private void cardFinished() {
        scenes[loopCounter].dispose();
        loopCounter ++;
    }

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