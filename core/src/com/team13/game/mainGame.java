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
	}

	@Override
	public void render () {
		canvas.update();
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();

	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

}
