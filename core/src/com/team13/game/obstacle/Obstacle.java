package com.team13.game.obstacle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.team13.game.stats.Position;

/**
 * Parent class of all obstacles.
 */
public class Obstacle
{
    // Attributes

    /**
     * x and y position of teh Obstacle
     */
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
     * Sets the first position for the obstacle.
     * @param position a Position variable, holds the position of the obstacle.
     * @see Position
     */
    public Obstacle(Position position)
    {
        obstaclePosition = position;
        batch = new SpriteBatch();
    }

    /**
     * Function to draw all Obstacles
     * It sets the projection matrix for the sprite batch. Translates the sprite then draws it within the sprite batch.
     * @param projectionMatrix projection matrix for the camera.
     */
    public void draw(Matrix4 projectionMatrix)
    {
        batch.setProjectionMatrix(projectionMatrix);
        batch.begin();
        obstacleSprite.setPosition(obstaclePosition.getPosX(), obstaclePosition.getPosY());
        obstacleSprite.draw(batch);
        batch.end();
    }

    /**
     * Empty method to act as a placeholder for move methods down the line.
     * Important as move is called on all obstacle types even the static ones.
     */
    public void move(){}

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
