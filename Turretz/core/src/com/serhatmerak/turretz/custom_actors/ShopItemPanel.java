package com.serhatmerak.turretz.custom_actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.serhatmerak.turretz.helpers.WeaponStyle;

/**
 * Created by Serhat Merak on 6.10.2018.
 */

public class ShopItemPanel extends Group {


    private final int width = 920,height = 200;
    public ShopItemPanelListener listener;
    public TextureRegion textureRegion;
    public WeaponStyle weaponStyle;


    public ShopItemPanel(BitmapFont font, final WeaponStyle weaponStyle){
        this.weaponStyle = weaponStyle;
        setSize(width,height);

        Square bg;
        final Square priceSquare;
        Square imgSquare;
        Label lblLevel,lblName,lblPrice;
        Image image;

        Square.SquareStyle bgSquareStyle = new Square.SquareStyle();
        if(weaponStyle.borderColor == null)
            bgSquareStyle.borderColor = Color.valueOf("4b9739");
        else
            bgSquareStyle.borderColor = weaponStyle.borderColor;
        bg = new Square(bgSquareStyle);
        bgSquareStyle.borderSize = 3;
        bg.setSize(width,height);

        Square.SquareStyle imgSquareStyle = new Square.SquareStyle();
        imgSquareStyle.borderColor = Color.WHITE;
        imgSquareStyle.borderSize = 4;

        imgSquare = new Square(imgSquareStyle);
        imgSquare.setSize(140,140);

        Square.SquareStyle priceSquareStyle = new Square.SquareStyle();
        priceSquareStyle.borderSize = 8;
        priceSquareStyle.borderColor = Color.valueOf("cbaf4e");

        priceSquare = new Square(priceSquareStyle);
        priceSquare.setSize(300,80);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        lblLevel = new Label("Lv. 1",labelStyle);
        lblName = new Label(weaponStyle.name,labelStyle);
        lblPrice = new Label("5.000",labelStyle);

        imgSquare.setPosition((getHeight() - imgSquare.getHeight()) / 2,(getHeight() - imgSquare.getHeight()) / 2);
        priceSquare.setPosition(getWidth() - imgSquare.getX() * 2.5f - priceSquare.getWidth(),(getHeight() - priceSquare.getHeight()) / 2);
        lblPrice.setPosition(priceSquare.getX() + priceSquare.getWidth() / 2,
                priceSquare.getY() + priceSquare.getHeight() / 2, Align.center);
        lblLevel.setPosition(imgSquare.getX() * 2.5f + imgSquare.getWidth(),getHeight() / 4 * 3);
        lblName.setPosition(lblLevel.getX(),getHeight() / 4 * 2);

        textureRegion = new TextureRegion(new Texture(weaponStyle.imgPath));

        image = new Image(new TextureRegionDrawable(textureRegion));
        image.setSize(120,120);
        image.setTouchable(Touchable.disabled);
        image.setPosition(imgSquare.getX() + (imgSquare.getWidth() - image.getWidth()) / 2,imgSquare.getY() + (imgSquare.getHeight() - image.getHeight()) / 2);

        addActor(bg);
        addActor(imgSquare);
        addActor(priceSquare);
        addActor(lblPrice);
        addActor(lblLevel);
        addActor(lblName);
        addActor(image);

        lblPrice.setTouchable(Touchable.disabled);

        imgSquare.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (listener != null){
                    listener.touchDown(ShopItemPanel.this);
                }
                return super.touchDown(event, x, y, pointer, button);
            }

        });

        priceSquare.addListener(new ClickListener(){

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                System.out.println("sa");
                priceSquare.setBorderColor(Color.valueOf("9b7f1e"));
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                priceSquare.setBorderColor(Color.valueOf("cbaf4e"));
            }
        });
    }

    public ShopItemPanel setListener(ShopItemPanelListener listener) {
        this.listener = listener;
        return this;
    }
}
