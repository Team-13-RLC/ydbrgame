package com.team13.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

public class BackgroundRender {
    Texture bgTexture;
    SpriteBatch batch;
    Matrix4 projection;
    int sourceX;

    BackgroundRender(Matrix4 projection) {
        this.projection = projection;
        batch = new SpriteBatch();
        bgTexture = new Texture(Gdx.files.internal("textures/backGround.png"));
        // Makes it possible to scroll the texture
        bgTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        sourceX = 0;
    }

    public void update(int cameraYPosition){
        batch.begin();
        batch.draw(bgTexture, 0, 0,0, -(cameraYPosition)/6, mainGame.Resolution.WIDTH, mainGame.Resolution.HEIGHT);
        batch.end();


    }
}
