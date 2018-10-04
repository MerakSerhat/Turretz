package com.serhatmerak.turretz.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.serhatmerak.turretz.helpers.GameInfo;
import com.serhatmerak.turretz.GameMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int) GameInfo.WIDTH / 2;
		config.height = (int) GameInfo.HEIGHT / 2;
		new LwjglApplication(new GameMain(), config);
	}
}
