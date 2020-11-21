package com.team13.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
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
    private Texture bgTexture;

    /**
     * Sprite batch used to draw everything
     */
    private SpriteBatch batch;

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
     */
    public void update(final Camera camera){
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(bgTexture, camera.position.x - camera.viewportWidth/2, camera.position.y - camera.viewportHeight/2,0, (int)-(camera.position.y), (int)camera.viewportWidth , (int)camera.viewportHeight);
        batch.end();
    }


    public void dispose(){
        bgTexture.dispose();
        batch.dispose();
    }
}
