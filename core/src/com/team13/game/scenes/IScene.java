package com.team13.game.scenes;

import com.badlogic.gdx.graphics.Camera;

/**
 * Interface which allows Card and Canvas objects to be stored in teh same array.
 */
public interface IScene {
    void update();
    boolean isEnd();
    void dispose();
    Camera getCamera();
    long getUserBoatPenalties();

    void resize(int width, int height);

    boolean getLegFinishedCorrectly();
}
