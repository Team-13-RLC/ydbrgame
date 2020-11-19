package com.team13.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

/**
 * Class to render the backgorund.
 */
public class BackgroundRender {

    // Fields
    /**
     * Water texture used for the background
     */
    Texture bgTexture;

    /**
     * Sprite batch used to draw everything
     */
    SpriteBatch batch;

    // Constructors
    /**
     * Initializes everything.
     * Enables texture wrapping on the background texture so that it can be scrolled.
     */
    BackgroundRender() {
        batch = new SpriteBatch();
        bgTexture = new Texture(Gdx.files.internal("textures/backGround.png"));
        bgTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }


    // Methods

    /**
     * Updates the background texture scroll position.
     * @param cameraYPosition Current y position of teh camera.
     */
    public void update(int cameraYPosition){
        batch.begin();
        batch.draw(bgTexture, 0, 0,0, -(cameraYPosition), mainGame.Resolution.WIDTH, mainGame.Resolution.HEIGHT);
        batch.end();


    }
}
