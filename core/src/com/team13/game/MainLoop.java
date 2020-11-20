package com.team13.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

public class MainLoop {
    BitmapFont font;
    SpriteBatch batch;

    MainLoop() {
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        font.getData().setScale(10);
        batch = new SpriteBatch();
    }

    public void drawText(Matrix4 projection) {
        batch.setProjectionMatrix(projection);
        batch.begin();
        font.draw(batch, "Hello World!", mainGame.Resolution.WIDTH / 2f, mainGame.Resolution.HEIGHT / 2f);
        batch.end();
    }
}