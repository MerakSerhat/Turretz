package com.serhatmerak.turretz.huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.serhatmerak.turretz.helpers.GameInfo;


/**
 * Created by Serhat Merak on 4.10.2018.
 */

public class MainHuds {

    public Stage stage;
    private BitmapFont font;
    private Label playArrow,originalArrow,infiniteArrow,backToMainMenuArrow;
    private Label lblHighscores;


    private TextButton btnPlay,btnHighscores,btnMoreGame;
    public TextButton btnOriginal;
    private TextButton btnInfinite;
    private TextButton btnBack;
    private TextButton btnLeadboard,btnCredits,btnBackToMainMenu;

    private Array<Actor> menuActors;
    private Array<Actor> highscoreActors;
    public Array<Actor> playActors;

    private final float changingScreenDelay = 0.7f;
    private final float minAlphaOfAnimatedActors = 0.2f;
    private final float timeOfAlphaAction = 0.6f;
    public final float fadingInAndOutTime = 0.5f;


    public MainHuds(Batch batch, Viewport viewport){
        stage = new Stage(viewport,batch);
        createFont();
//        Label.LabelStyle labelStyle = new Label.LabelStyle();
//        labelStyle.font = font;
//
//        hello = new Label("Hello World",labelStyle);
//        hello.setPosition(GameInfo.WIDTH / 2,GameInfo.HEIGHT / 2, Align.center);
//        stage.addActor(hello);
//
        createButtons();
        createLabels();
        screenChanging();
    }

    private void screenChanging() {
        menuActors = new Array<Actor>();
        highscoreActors = new Array<Actor>();
        playActors = new Array<Actor>();

        menuActors.add(btnPlay);
        menuActors.add(btnMoreGame);
        menuActors.add(btnHighscores);
        menuActors.add(playArrow);

        highscoreActors.add(btnCredits);
        highscoreActors.add(btnLeadboard);
        highscoreActors.add(btnBackToMainMenu);
        highscoreActors.add(backToMainMenuArrow);
        highscoreActors.add(lblHighscores);

        playActors.add(btnOriginal);
        playActors.add(btnInfinite);
        playActors.add(btnBack);
        playActors.add(infiniteArrow);
        playActors.add(originalArrow);

        btnPlay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                for (Actor a:menuActors) {
                    a.addAction(Actions.sequence(Actions.fadeOut(fadingInAndOutTime),Actions.removeActor(a)));
                }
                for (Actor a:playActors) {
                    stage.addActor(a);
                    for (Action action:a.getActions()) {
                        a.removeAction(action);
                    }

                    a.addAction(Actions.fadeOut(0));
                    SequenceAction sequenceAction = new SequenceAction();
                    sequenceAction.addAction(Actions.delay(changingScreenDelay));
                    sequenceAction.addAction(Actions.fadeIn(fadingInAndOutTime));
                    if (a == btnOriginal)
                        sequenceAction.addAction(Actions.forever(Actions.sequence(Actions.alpha(minAlphaOfAnimatedActors,timeOfAlphaAction),
                                Actions.alpha(1f,timeOfAlphaAction))));
                    a.addAction(sequenceAction);

                }
            }
        });


        btnBackToMainMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                for (Actor a:highscoreActors) {
                    a.addAction(Actions.sequence(Actions.fadeOut(fadingInAndOutTime),Actions.removeActor(a)));
                }
                for (Actor a:menuActors) {
                    stage.addActor(a);
                    for (Action action:a.getActions()) {
                        a.removeAction(action);
                    }

                    a.addAction(Actions.fadeOut(0));
                    SequenceAction sequenceAction = new SequenceAction();
                    sequenceAction.addAction(Actions.delay(changingScreenDelay));
                    sequenceAction.addAction(Actions.fadeIn(fadingInAndOutTime));
                    if (a == btnPlay)
                        sequenceAction.addAction(Actions.forever(Actions.sequence(Actions.alpha(minAlphaOfAnimatedActors,timeOfAlphaAction),
                                Actions.alpha(1f,timeOfAlphaAction))));
                    a.addAction(sequenceAction);

                }
            }
        });


        btnBack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                for (Actor a:playActors) {
                    a.addAction(Actions.sequence(Actions.fadeOut(fadingInAndOutTime),Actions.removeActor(a)));
                }
                for (Actor a:menuActors) {
                    stage.addActor(a);
                    for (Action action:a.getActions()) {
                        a.removeAction(action);
                    }

                    a.addAction(Actions.fadeOut(0));
                    SequenceAction sequenceAction = new SequenceAction();
                    sequenceAction.addAction(Actions.delay(changingScreenDelay));
                    sequenceAction.addAction(Actions.fadeIn(fadingInAndOutTime));
                    if (a == btnPlay)
                        sequenceAction.addAction(Actions.forever(Actions.sequence(Actions.alpha(minAlphaOfAnimatedActors,timeOfAlphaAction),
                                Actions.alpha(1f,timeOfAlphaAction))));
                    a.addAction(sequenceAction);

                }
            }
        });

        btnHighscores.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                for (Actor a:menuActors) {
                    a.addAction(Actions.sequence(Actions.fadeOut(fadingInAndOutTime),Actions.removeActor(a)));
                }
                for (Actor a:highscoreActors) {
                    stage.addActor(a);
                    for (Action action:a.getActions()) {
                        a.removeAction(action);
                    }

                    a.addAction(Actions.fadeOut(0));
                    SequenceAction sequenceAction = new SequenceAction();
                    sequenceAction.addAction(Actions.delay(changingScreenDelay));
                    sequenceAction.addAction(Actions.fadeIn(fadingInAndOutTime));
                    if (a == btnBackToMainMenu)
                        sequenceAction.addAction(Actions.forever(Actions.sequence(Actions.alpha(minAlphaOfAnimatedActors,timeOfAlphaAction),
                                Actions.alpha(1f,timeOfAlphaAction))));
                    a.addAction(sequenceAction);

                }
            }
        });

        //ilk açılış
        for (Actor a:menuActors) {
            stage.addActor(a);
            for (Action action:a.getActions()) {
                a.removeAction(action);
            }

            a.addAction(Actions.fadeOut(0));
            SequenceAction sequenceAction = new SequenceAction();
            sequenceAction.addAction(Actions.delay(changingScreenDelay));
            sequenceAction.addAction(Actions.fadeIn(fadingInAndOutTime));
            if (a == btnPlay)
                sequenceAction.addAction(Actions.forever(Actions.sequence(Actions.alpha(minAlphaOfAnimatedActors,timeOfAlphaAction),
                        Actions.alpha(1f,timeOfAlphaAction))));
            a.addAction(sequenceAction);

        }
    }



    private void createLabels() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        playArrow = new Label("[GREEN]>[]",labelStyle);
        playArrow.setPosition(btnPlay.getX() - 20 - playArrow.getWidth(),btnPlay.getY());

        originalArrow = new Label("[GREEN]>[]",labelStyle);
        originalArrow.setPosition(btnOriginal.getX() - 20 - originalArrow.getWidth(),btnOriginal.getY());

        infiniteArrow = new Label("[#2f2f2f]>[]",labelStyle);
        infiniteArrow.setPosition(btnInfinite.getX() - 20 - infiniteArrow.getWidth(),btnInfinite.getY());

        backToMainMenuArrow = new Label(">",labelStyle);
        backToMainMenuArrow.setPosition(btnBackToMainMenu.getX() - 20 - backToMainMenuArrow.getWidth(),
                btnBackToMainMenu.getY());

        lblHighscores = new Label("[RED]HIGH SCORES[]",labelStyle);
        lblHighscores.setPosition(GameInfo.WIDTH / 2,GameInfo.HEIGHT - 200,Align.center);
    }

    private void createButtons() {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;

        btnPlay = new TextButton("[GREEN]PLAY GAME[]",textButtonStyle);
        btnHighscores = new TextButton("HIGH SCORES",textButtonStyle);
        btnMoreGame = new TextButton("MORE GAME",textButtonStyle);

        btnPlay.addAction(Actions.forever(Actions.sequence(Actions.alpha(minAlphaOfAnimatedActors,timeOfAlphaAction),
                Actions.alpha(1f,timeOfAlphaAction))));

        btnHighscores.setPosition(GameInfo.WIDTH / 2,GameInfo.HEIGHT / 2,Align.center);
        btnPlay.setPosition(GameInfo.WIDTH / 2,GameInfo.HEIGHT / 2 + 150,Align.center);
        btnMoreGame.setPosition(GameInfo.WIDTH / 2,GameInfo.HEIGHT / 2 - 150,Align.center);

        ///////////////////////////

        btnOriginal = new TextButton("[YELLOW]ORIGINAL[]",textButtonStyle);
        btnOriginal.setPosition(GameInfo.WIDTH / 2,GameInfo.HEIGHT / 2 + 100,Align.center);

        btnInfinite = new TextButton("[#2f2f2f]INFINITE[]",textButtonStyle);
        btnInfinite.setPosition(GameInfo.WIDTH / 2,GameInfo.HEIGHT / 2,Align.center);

        btnBack = new TextButton("BACK",textButtonStyle);
        btnBack.setPosition(GameInfo.WIDTH / 2,GameInfo.HEIGHT / 2 - 100,Align.center);

        ///////////////////////////////////////

        btnLeadboard = new TextButton("LEADBOARD",textButtonStyle);
        btnCredits = new TextButton("CREDITS",textButtonStyle);
        btnBackToMainMenu = new TextButton("BACK TO MAIN MENU",textButtonStyle);

        btnCredits.setPosition(GameInfo.WIDTH / 2,GameInfo.HEIGHT / 4,Align.center);
        btnBackToMainMenu.setPosition(GameInfo.WIDTH / 2,GameInfo.HEIGHT / 4 - 100,Align.center);
        btnLeadboard.setPosition(GameInfo.WIDTH / 2,GameInfo.HEIGHT / 4 + 100,Align.center);

    }

    private void createFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("upheavtt.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 64;
        font = generator.generateFont(parameter);
        generator.dispose();

        font.getData().markupEnabled = true;
    }
}
