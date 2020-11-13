package com.team13.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.team13.game.mainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = mainGame.Resolution.WIDTH;
		config.height = mainGame.Resolution.HEIGHT;

		new LwjglApplication(new mainGame(), config);
	}
}
