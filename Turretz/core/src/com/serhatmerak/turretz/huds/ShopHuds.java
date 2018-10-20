package com.serhatmerak.turretz.huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.serhatmerak.turretz.custom_actors.ShopItemPanel;
import com.serhatmerak.turretz.custom_actors.Square;
import com.serhatmerak.turretz.custom_actors.SquareButton;
import com.serhatmerak.turretz.helpers.GameInfo;
import com.serhatmerak.turretz.helpers.WeaponStyle;
import com.serhatmerak.turretz.screens.ShopScreen;

import java.util.Arrays;

/**
 * Created by Serhat Merak on 6.10.2018.
 */

public class ShopHuds {
    public Stage stage;
    private ShopScreen shopScreen;
    private BitmapFont font;
    public Sprite botImg;
    public SquareButton btnHome,btnPlay,btnPaymentShop;
    public ScrollPane upgradeScrollPane;

    public ShopHuds(Batch batch, Viewport viewport, ShopScreen shopScreen){
        this.shopScreen = shopScreen;
        stage = new Stage(viewport,batch);
        createFont();
        createMenu();
        Image img = new Image(new TextureRegionDrawable(new TextureRegion(new Texture("shop_bottom_img.png"))));
        img.setPosition(0,0);
        img.setTouchable(Touchable.disabled);
//        img.setSize(GameInfo.WIDTH,170);
        stage.addActor(img);
        createButtons();

//        ShopItemPanel shopItemPanel = new ShopItemPanel(font);
//        shopItemPanel.setPosition(80,GameInfo.HEIGHT / 2);
//        stage.addActor(shopItemPanel);





        botImg = new Sprite(new Texture("shop_bottom_img.png"));
        botImg.setSize(botImg.getWidth(),400);



    }

    private void createButtons() {
        SquareButton.SquareButtonStyle homeStyle = new SquareButton.SquareButtonStyle();
        homeStyle.bgColor = Color.valueOf("919aa3");

        SquareButton.SquareButtonStyle playStyle = new SquareButton.SquareButtonStyle();
        playStyle.bgColor = Color.valueOf("852821");

        SquareButton.SquareButtonStyle paymentStyle = new SquareButton.SquareButtonStyle();
        paymentStyle.bgColor = Color.valueOf("cda81c");

        btnHome = new SquareButton(homeStyle);
        btnPlay = new SquareButton(playStyle);
        btnPaymentShop = new SquareButton(paymentStyle);

        btnHome.setSize(290,150);
        btnPlay.setSize(400,150);
        btnPaymentShop.setSize(290,150);

        btnHome.setPosition(35,20);
        btnPlay.setPosition(btnHome.getX() + btnHome.getWidth() + 20,20);
        btnPaymentShop.setPosition(btnPlay.getWidth() + btnPlay.getX() + 20,20);

        stage.addActor(btnHome);
        stage.addActor(btnPaymentShop);
        stage.addActor(btnPlay);

    }

    private void createMenu() {
        final Square menuSquare = new Square(new Square.SquareStyle());
        menuSquare.setSize(GameInfo.WIDTH - 80,GameInfo.HEIGHT / 2 - 40);
        menuSquare.setPosition(40,40);
        stage.addActor(menuSquare);

        final Square upgradeSquare = new Square(new Square.SquareStyle());
        upgradeSquare.setSize(300,80);
        upgradeSquare.setPosition(40,menuSquare.getHeight() + menuSquare.getY() - 5);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        Label upgradeLabel = new Label("[GRAY]UPGRADE[]",labelStyle);
        upgradeSquare.addLabel(upgradeLabel);
        upgradeSquare.bottomBorder.setColor(Color.BLACK);

        stage.addActor(upgradeSquare);

        final Square skinSquare = new Square(new Square.SquareStyle());
        skinSquare.setSize(300,80);
        skinSquare.setPosition(40 + upgradeSquare.getWidth() - 5,menuSquare.getHeight() + menuSquare.getY() - 25);

        Label skinLabel = new Label("[GRAY]SKIN[]",labelStyle);
        skinSquare.addLabel(skinLabel);
        skinSquare.bottomBorder.setColor(Color.BLACK);

        stage.addActor(skinSquare);

        int [] zIndexes = {upgradeSquare.getZIndex(),menuSquare.getZIndex(),skinSquare.getZIndex()};
        Arrays.sort(zIndexes);

        upgradeSquare.setZIndex(zIndexes[2]);
        menuSquare.setZIndex(zIndexes[1]);
        skinSquare.setZIndex(zIndexes[0]);

        final Table upgradeTable = new Table();
        upgradeTable.setWidth(920);
        upgradeTable.setHeight(25 * 220);
        upgradeTable.align(Align.topLeft);
        for (int i = 0; i < 25; i++) {
            int padBottom = 20;
            if (i == 24)
                padBottom = 100;
            upgradeTable.add(new ShopItemPanel(font, WeaponStyle.values()[MathUtils.random(0,WeaponStyle.values().length - 1)]).setListener(shopScreen)).padBottom(padBottom).row();
        }

        final Table skinTable = new Table();
        skinTable.setWidth(920);
        skinTable.setHeight(25 * 220);
        skinTable.align(Align.topLeft);

        for (int i = 0; i < 250; i++) {
            int padBottom = 20;
            if (i == 24)
                padBottom = 100;
            skinTable.add(new ShopItemPanel(font, WeaponStyle.values()[MathUtils.random(0,WeaponStyle.values().length - 1)]).setListener(shopScreen)).padBottom(padBottom).row();
        }

        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle();
        upgradeScrollPane = new ScrollPane(upgradeTable, scrollPaneStyle);
        upgradeScrollPane.setVariableSizeKnobs(false);
        upgradeScrollPane.setOverscroll(false, false);
        upgradeScrollPane.setSize(920,menuSquare.getHeight() - 80);
        upgradeScrollPane.setPosition(menuSquare.getX() + 40,menuSquare.getY() + 40);
        stage.addActor(upgradeScrollPane);

        upgradeSquare.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                int [] zIndexes = {upgradeSquare.getZIndex(),menuSquare.getZIndex(),skinSquare.getZIndex()};
                Arrays.sort(zIndexes);

                upgradeSquare.setZIndex(zIndexes[2]);
                menuSquare.setZIndex(zIndexes[1]);
                skinSquare.setZIndex(zIndexes[0]);

                upgradeSquare.setPosition(40,menuSquare.getHeight() + menuSquare.getY() - 5);
                skinSquare.setPosition(40 + upgradeSquare.getWidth() - 5,
                        menuSquare.getHeight() + menuSquare.getY() - 25);
                upgradeScrollPane.setActor(upgradeTable);
                upgradeScrollPane.setVelocityY(0);
                upgradeScrollPane.setScrollY(0);

            }
        });

        skinSquare.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                int [] zIndexes = {upgradeSquare.getZIndex(),menuSquare.getZIndex(),skinSquare.getZIndex()};
                Arrays.sort(zIndexes);

                upgradeSquare.setZIndex(zIndexes[0]);
                menuSquare.setZIndex(zIndexes[1]);
                skinSquare.setZIndex(zIndexes[2]);

                upgradeSquare.setPosition(40,menuSquare.getHeight() + menuSquare.getY() - 25);
                skinSquare.setPosition(40 + upgradeSquare.getWidth() - 5,
                        menuSquare.getHeight() + menuSquare.getY() - 5);

                upgradeScrollPane.setActor(skinTable);
                upgradeScrollPane.setVelocityY(0);
                upgradeScrollPane.setScrollY(0);


            }
        });







    }

    private void createFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("upheavtt.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 42;
        font = generator.generateFont(parameter);
        generator.dispose();

        font.getData().markupEnabled = true;
    }
}


