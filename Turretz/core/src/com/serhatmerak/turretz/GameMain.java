package com.serhatmerak.turretz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.serhatmerak.turretz.screens.MainScreen;

public class GameMain extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MainScreen(this));
	}

	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
