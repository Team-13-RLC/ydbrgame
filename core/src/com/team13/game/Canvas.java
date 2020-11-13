package com.team13.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Canvas {
    private static Canvas instance = new Canvas();

    private Canvas(){}


    public void create(){


    }

    public void update(){
        Gdx.gl.glClearColor(0.4F, 0.4F, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    }


    // Getters
    public static Canvas getInstance() {
        return instance;
    }
}
