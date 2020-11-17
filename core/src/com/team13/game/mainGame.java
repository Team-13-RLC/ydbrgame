package com.team13.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.team13.game.boat.Boat;
import com.team13.game.boat.UserBoat;
import com.team13.game.stats.Position;


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
//		batch.setProjectionMatrix(canvas.getProjection());
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();

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
