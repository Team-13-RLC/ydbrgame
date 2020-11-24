package com.team13.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.team13.game.mainGame;

public class Card implements IScene {
    private final OrthographicCamera camera;
    private final FillViewport viewport;
    private final Texture cardTexture;
    private final SpriteBatch batch;



    public Card(FileHandle textureFile){
        camera = new OrthographicCamera();
        viewport = new FillViewport(mainGame.Resolution.WIDTH, mainGame.Resolution.HEIGHT, camera);
        cardTexture = new Texture(textureFile);
        batch = new SpriteBatch();

        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
        viewport.apply();
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }


    public void update(){
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(cardTexture,0, 0);
        batch.end();
    }

    public boolean isEnd(){
        return Gdx.input.isKeyPressed(Input.Keys.ANY_KEY);
    }

    public void dispose(){
        cardTexture.dispose();
        batch.dispose();
    }


    public final Camera getCamera() {
        return camera;
    }

    public long getUserBoatPenalties(){return 0;}

    public void resize(int width, int height){
        viewport.update(width, height);
        camera.position.set(camera.viewportWidth/2 , camera.viewportHeight/2, 0f);
    }


    public boolean getLegFinishedCorrectly(){ return true;}
}
