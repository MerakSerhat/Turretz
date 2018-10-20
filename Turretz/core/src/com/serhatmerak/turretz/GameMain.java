package com.serhatmerak.turretz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.serhatmerak.turretz.helpers.GifBatch;
import com.serhatmerak.turretz.screens.MainScreen;

public class GameMain extends Game {
	public GifBatch batch;
	
	@Override
	public void create () {
		batch = new GifBatch();
		setScreen(new MainScreen(this));
	}

	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
