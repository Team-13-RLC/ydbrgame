package com.team13.game.obstacle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.team13.game.stats.Position;

/**
 * Class of tree branch Obstacles
 */
public class treeBranch extends simpleDynamicObstacle
{

    /**
     * Creates a tree branch and initialises all that needs to be initialised.
     * Assigns the correct texture for the tree branch(es). Ensures for correct filtering.
     * Gives the texture to the tree branch sprite and sets initial position of the tree branch using the superclass' constructor.
     * Sets the scale for the sprite.
     * @param position x and y position of the obstacle.
     **/
    public treeBranch(Position position)
    {
        super(position);
        spriteScale = 0.2F;
        obstacleTexture = new Texture(Gdx.files.internal("textures/Branch_Texture.png"));
        obstacleTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        obstacleSprite = new Sprite(obstacleTexture);
        obstacleSprite.setPosition(obstaclePosition.getPosX(), obstaclePosition.getPosY());
        obstacleSprite.setScale(spriteScale);
    }
}
