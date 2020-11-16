package com.team13.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;

public class Canvas {
    private OrthographicCamera camera;
    private static Canvas instance = new Canvas();
    private Canvas(){}


    private static final byte numLanes = 8;
    private Lane[] lanes;


    // Gets called once to init everything to do woth the canvas
    public void create(){
        camera = new OrthographicCamera(mainGame.Resolution.WIDTH, mainGame.Resolution.HEIGHT );
        camera.position.set(mainGame.Resolution.WIDTH/2f, mainGame.Resolution.HEIGHT/2f, 0f);
        camera.update();
        makeLanes();
    }
    // Gets called every frame to update the canvas
    public void update(){
        Gdx.gl.glClearColor(0.2F, 0.3F, 0.9F, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        drawLanes();
    }



    private void makeLanes(){
        lanes = new Lane[numLanes];
        for (int i = 0; i < numLanes; i++){
            //TODO: make one of the lanes randomly UserLane
            lanes[i] = new Lane(i* (mainGame.Resolution.WIDTH/numLanes), (i+1)* (mainGame.Resolution.WIDTH/numLanes));
        }
    }

    private void drawLanes(){
        for (int i = 0; i < numLanes; i++){
            lanes[i].draw(camera);
        }
    }


    // Getters
    public static Canvas getInstance() {
        return instance;
    }

    // Returns the projection matrix for the current camera
    public Matrix4 getProjection() {
        return camera.combined;
    }

    public Lane[] getLanes() {
        return lanes;
    }
}
