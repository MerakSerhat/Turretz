package com.serhatmerak.turretz.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.serhatmerak.turretz.helpers.GameInfo;
import com.serhatmerak.turretz.GameMain;
import com.serhatmerak.turretz.huds.MainHuds;

/**
 * Created by Serhat Merak on 4.10.2018.
 */

public class MainScreen implements Screen {

    private GameMain game;
    private OrthographicCamera camera;
    private StretchViewport viewport;
    private SpriteBatch batch;

    private MainHuds mainHuds;



    public MainScreen(GameMain game){
        this.game = game;
        batch = game.batch;
        camera = new OrthographicCamera(GameInfo.WIDTH,GameInfo.HEIGHT);
        camera.setToOrtho(false,GameInfo.WIDTH,GameInfo.HEIGHT);
        viewport = new StretchViewport(GameInfo.WIDTH,GameInfo.HEIGHT,camera);

        mainHuds = new MainHuds(batch,viewport);


        Gdx.input.setInputProcessor(mainHuds.stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);

        mainHuds.stage.act();
        mainHuds.stage.draw();



    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    @Override
    public void dispose() {

    }
}
