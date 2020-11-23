package com.team13.game;

import com.badlogic.gdx.graphics.Camera;

public interface IScene {
    void update();
    boolean isEnd();
    void dispose();
    Camera getCamera();
    long getUserBoatPenalties();

    void resize(int width, int height);
}
