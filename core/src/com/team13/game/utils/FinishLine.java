package com.team13.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.team13.game.mainGame;

/**
 * The finish line, the line a boat crosses to win the race.
 */
public class FinishLine {
    // Fields
    /**
     * Y coordinate of the bottom edge of the finish line
     */
    private final float position;

    /**
     * Stores the texture of teh finish line.
     */
    protected Texture finishLineTexture;

    /**
     * Sprite batch used to draw the finish line.
     */
    protected SpriteBatch batch;

    /**
     * Sprite used to draw The finish line.
     * Holds the texture and is drawn in the batch.
     */
    protected Sprite finishLineSprite;


    // Constructors

    /**
     * Takes position as a parameter and initialises y coordinate of the finish line to it.
     *
     * @param position y coordinate of the bottom edge of teh finish line.
     */
    public FinishLine(float position) {
        this.position = position;
        batch = new SpriteBatch();
        finishLineTexture = new Texture(Gdx.files.internal("textures/finishLineTexture.png"));
        finishLineTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        finishLineSprite = new Sprite(finishLineTexture);
        finishLineSprite.setPosition(100, this.position);
        finishLineSprite.setSize(mainGame.Resolution.WIDTH, mainGame.Resolution.HEIGHT/20f);
    }

    // Methods
    public void draw(Matrix4 projectionMatrix){
        batch.setProjectionMatrix(projectionMatrix);
        batch.begin();
        finishLineSprite.draw(batch);
        batch.end();
    }



    // Getters
    public float getPosition() {
        return position;
    }
}
