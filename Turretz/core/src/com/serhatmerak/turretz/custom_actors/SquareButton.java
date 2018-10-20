package com.serhatmerak.turretz.custom_actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Serhat Merak on 7.10.2018.
 */

public class SquareButton extends Group {
    public SquareButtonStyle squareButtonStyle;
    private Image bg;
    private Image right_border,leftBorder,botBorder,topBorder;
    private Image img;
    private Image hoverImg;

    public SquareButton(SquareButtonStyle squareButtonStyle){
        this.squareButtonStyle = squareButtonStyle;
        TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("pix.png")));
        bg = new Image(textureRegionDrawable);
        bg.setColor(squareButtonStyle.bgColor);

        right_border = new Image(textureRegionDrawable);
        leftBorder = new Image(textureRegionDrawable);
        topBorder = new Image(textureRegionDrawable);
        botBorder = new Image(textureRegionDrawable);
        hoverImg = new Image(textureRegionDrawable);
        hoverImg.setColor(Color.BLACK);
        hoverImg.getColor().a = 0;

        right_border.setColor(squareButtonStyle.borderColor);
        leftBorder.setColor(squareButtonStyle.borderColor);
        topBorder.setColor(squareButtonStyle.borderColor);
        botBorder.setColor(squareButtonStyle.borderColor);

        if (squareButtonStyle.img != null)
            img = new Image(squareButtonStyle.img);

        addActor(bg);
        addActor(topBorder);
        addActor(botBorder);
        addActor(leftBorder);
        addActor(right_border);
        if (img != null)
            addActor(img);

        addActor(hoverImg);

        addListener(new ClickListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                hoverImg.getColor().a = 0.5f;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                hoverImg.getColor().a = 0f;
            }
        });

    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        bg.setSize(width,height);
        hoverImg.setSize(width,height);
        topBorder.setSize(width - 20,5);
        botBorder.setSize(width - 20,5);
        leftBorder.setSize(5,height - 30);
        right_border.setSize(5,height - 30);

        topBorder.setPosition(10,getHeight() - 15);
        botBorder.setPosition(10,10);
        leftBorder.setPosition(10,15);
        right_border.setPosition(getWidth() - 15,15);
    }

    public static class SquareButtonStyle{
        public Drawable img;
        public Color bgColor = Color.WHITE;
        public Color borderColor = Color.BLACK;
    }
}
