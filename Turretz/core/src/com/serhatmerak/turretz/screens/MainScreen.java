package com.serhatmerak.turretz.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
        giveListenersToButtons();



        Gdx.input.setInputProcessor(mainHuds.stage);
    }

    private void giveListenersToButtons() {
        mainHuds.btnOriginal.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                for (Actor a:mainHuds.playActors) {
                    for (Action action:a.getActions()) {
                        a.removeAction(action);
                    }
                    a.addAction(Actions.sequence(Actions.fadeOut(mainHuds.fadingInAndOutTime),Actions.delay(0.5f),Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            game.setScreen(new ShopScreen(game));
                        }
                    })));
                }
            }
        });
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
