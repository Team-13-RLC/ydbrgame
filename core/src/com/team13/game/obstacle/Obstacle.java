package com.team13.game.obstacle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.team13.game.stats.Position;
import com.team13.game.stats.Stats;

/**
 * Parent class of all obstacles.
 */

public class Obstacle
{
    // Attributes

    protected Position obstaclePosition;

    /**
     * Stores the texture for each obstacle.
     */
    protected Texture obstacleTexture;

    /**
     * Sprite batch used to draw each obstacle.
     */
    protected SpriteBatch batch;

    /**
     * Sprite used to draw obstacles. Stores the texture and will be used to be drawn in the batch.
     */
    protected Sprite obstacleSprite;

    /**
     * How much the sprite needs to be scaled
     */
    protected float spriteScale;

    /**
     * How wide the obstacle will be.
     */
    protected float obstacleWidth;

    /**
     * How tall the obstacle will be.
     */
    protected float obstacleHeight;

    /**
     * Creates an object and all the initialises all that needs to be initialised.
     * Assigns the required texture to an obstacle. Ensures for correct filtering.
     * Gives the correct texture to the sprite. Also sets the first position for the sprite.
     * Sets the scaling for the sprites.
     * @param position
     * @see Position
     */
    public Obstacle(Position position)
    {
        spriteScale = 0.2F;
        obstaclePosition = position;
        obstacleTexture = new Texture(Gdx.files.internal("textures/obstacleTexture.png"));
        obstacleTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        obstacleSprite = new Sprite(obstacleTexture);
        obstacleSprite.setPosition(obstaclePosition.getPosX(), obstaclePosition.getPosY());
        obstacleSprite.setScale(spriteScale);
    }

    public void draw(Matrix4 projectionMatrix)
    {
        batch.setProjectionMatrix(projectionMatrix);
        batch.begin();
        obstacleSprite.setPosition(obstaclePosition.getPosX(), obstaclePosition.getPosY());
        obstacleSprite.draw(batch);
        batch.end();
    }

    // Getters

    public Position getObstaclePosition()
    {
        return obstaclePosition;
    }

    public float getObstacleHeight()
    {
        return obstacleHeight;
    }

    public float getObstacleWidth()
    {
        return obstacleWidth;
    }

    public SpriteBatch getBatch()
    {
        return batch;
    }

    public Sprite getObstacleSprite()
    {
        return obstacleSprite;
    }

    public Texture getObstacleTexture()
    {
        return obstacleTexture;
    }

    public float getSpriteScale()
    {
        return spriteScale;
    }

    // Setters

    public void setObstacleHeight(float obstacleHeight)
    {
        this.obstacleHeight = obstacleHeight;
    }

    public void setObstacleWidth(float obstacleWidth)
    {
        this.obstacleWidth = obstacleWidth;
    }

    public void setSpriteScale(float spriteScale)
    {
        this.spriteScale = spriteScale;
    }

    public void setObstacleSprite(Sprite obstacleSprite)
    {
        this.obstacleSprite = obstacleSprite;
    }

    public void setBatch(SpriteBatch batch)
    {
        this.batch = batch;
    }

    public void setObstacleTexture(Texture obstacleTexture)
    {
        this.obstacleTexture = obstacleTexture;
    }

    public void setObstaclePosition(Position obstaclePosition)
    {
        this.obstaclePosition = obstaclePosition;
    }
}
