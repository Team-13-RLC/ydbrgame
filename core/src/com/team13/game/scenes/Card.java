package com.team13.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.team13.game.mainGame;

/**
 * A class to display a card (an image) on the whole screen.
 */
public class Card implements IScene {
    //Methods
    /**
     * The camera used for the Card.
     */
    private final OrthographicCamera camera;

    /**
     * The viewport used for teh Card
     */
    private final FillViewport viewport;

    /**
     * The card to be displayed (as a texture)
     */
    private final Texture cardTexture;

    /**
     * The sprite batch to render teh card.
     */
    private final SpriteBatch batch;


    //Constructors

    /**
     * Constrictor for the Card class.
     * Initializes the camera, viewport, texture and the SpriteBatch.
     * Sets the camera position correctly.
     * @param textureFile FileHandle with the location of the card texture.
     */
    public Card(FileHandle textureFile){
        camera = new OrthographicCamera();
        viewport = new FillViewport(mainGame.Resolution.WIDTH, mainGame.Resolution.HEIGHT, camera);
        cardTexture = new Texture(textureFile);
        batch = new SpriteBatch();

        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
        viewport.apply();
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }


    /**
     * Draws the card to the screen.
     */
    public void update(){
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(cardTexture,0, 0);
        batch.end();
    }

    /**
     * Checks for any keys to be pressed.
     * @return returns true if a key was pressed, false otherwise.
     */
    public boolean isEnd(){
        return Gdx.input.isKeyPressed(Input.Keys.ANY_KEY);
    }

    /**
     * Calls dispose on the texture and the sprite batch.
     */
    public void dispose(){
        cardTexture.dispose();
        batch.dispose();
    }

    /**
     * Function given to the ApplicationAdapter#resize() function in mainGame
     * @param width  window width
     * @param height window height
     * @see mainGame#resize(int width, int height)
     */
    public void resize(int width, int height){
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth/2 , camera.viewportHeight/2, 0f);
    }


    // Getters
    public final Camera getCamera() {
        return camera;
    }

    public long getUserBoatPenalties(){return 0;}



    public boolean getLegFinishedCorrectly(){ return true;}
}
