package com.team13.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class mainGame extends ApplicationAdapter {
	Canvas canvas;

	@Override
	public void create () {
		canvas = Canvas.getInstance();
		canvas.create();
	}

	@Override
	public void render () {
		canvas.update();
		// Uncomment to see fps
  		// System.out.println(com.badlogic.gdx.Gdx.graphics.getFramesPerSecond());
	}

	@Override
	public void dispose () {
		canvas.dispose();
	}


	// Didn't know where else to put it, so it's here for now
	public static class Resolution{
		static public final int WIDTH = 1280;
		static public final int HEIGHT = 720;
	}
}
