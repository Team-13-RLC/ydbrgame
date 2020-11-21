package com.team13.game;

import com.badlogic.gdx.ApplicationAdapter;


public class mainGame extends ApplicationAdapter {
    MainLoop loop;

	@Override
	public void create () {
		// Only one instance of MainLoopcan exist. It can be retrieved with MainLoop.getInstance()
		loop = MainLoop.getInstance();
	}

	@Override
	public void render () {
	    loop.run();

		// Uncomment to see fps
  		// System.out.println(com.badlogic.gdx.Gdx.graphics.getFramesPerSecond());
	}

	@Override
	public void dispose () {
	}


	@Override
	public void resize(int width, int height){
		loop.resize(width, height);

	}


	// Didn't know where else to put it, so it's here for now
	public static class Resolution{
		static public final int WIDTH = 1280;
		static public final int HEIGHT = 720;
	}
}
