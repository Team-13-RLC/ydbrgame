package com.team13.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Canvas {
    private static Canvas instance = new Canvas();

    public OrthographicCamera camera;
    private Canvas(){}


    public void create(){
        camera = new OrthographicCamera(mainGame.Resolution.WIDTH, mainGame.Resolution.HEIGHT );
        camera.position.set(mainGame.Resolution.WIDTH/2f, mainGame.Resolution.HEIGHT/2f, 0f);
        camera.update();




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
