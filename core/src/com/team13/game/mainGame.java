package com.team13.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class mainGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Canvas canvas;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		canvas = Canvas.getInstance();
		canvas.create();
	}

	@Override
	public void render () {
		canvas.update();
		// this can be improved by making a method in canvas and returning camera.combined
		batch.setProjectionMatrix(canvas.camera.combined);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();

	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}


	// Didn't know where else to put it, so it's here for now
	public static class Resolution{
		static public final int WIDTH = 1280;
		static public final int HEIGHT = 720;
	}
}
