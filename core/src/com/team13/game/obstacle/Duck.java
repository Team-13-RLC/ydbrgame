package com.team13.game.obstacle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.team13.game.stats.Position;

public class Duck extends complexDynamicObstacle
{
    /**
     * Inherits dynamicObstacle class.
     * Creates a duck and initialises all that needs to be initialised.
     * Assigns the correct texture for the duck(s). Ensures for correct filtering.
     * Gives the texture to the duck sprite and sets initial position of the duck using the superclass' constructor.
     * Sets the scale for the sprite.
     * @param position
     * @See Obstacle
     */
    public Duck(Position position)
    {
        super(position);
        spriteScale = 0.2F;
        obstacleTexture = new Texture(Gdx.files.internal("textures/rockTexture.png"));
        obstacleTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        obstacleSprite = new Sprite(obstacleSprite);
        obstacleSprite.setPosition(obstaclePosition.getPosX(), obstaclePosition.getPosY());
        obstacleSprite.setScale(spriteScale);
    }
}
