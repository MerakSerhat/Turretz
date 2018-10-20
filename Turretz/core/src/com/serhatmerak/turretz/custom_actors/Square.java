package com.serhatmerak.turretz.custom_actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

/**
 * Created by Serhat Merak on 6.10.2018.
 */

public class Square extends Group{
    public SquareStyle squareStyle;
    private Image pixelBg;
    private Image leftBorder;
    private Image rightBorder;
    private Image topBorder;
    public Image bottomBorder;



    public Square(SquareStyle squareStyle){
        this.squareStyle = squareStyle;
        TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("pix.png")));
        pixelBg = new Image(textureRegionDrawable);
        leftBorder = new Image(textureRegionDrawable);
        rightBorder = new Image(textureRegionDrawable);
        topBorder = new Image(textureRegionDrawable);
        bottomBorder= new Image(textureRegionDrawable);

        pixelBg.setColor(squareStyle.bgColor);
        leftBorder.setColor(squareStyle.borderColor);
        topBorder.setColor(squareStyle.borderColor);
        rightBorder.setColor(squareStyle.borderColor);
        bottomBorder.setColor(squareStyle.borderColor);

        addActor(pixelBg);
        addActor(bottomBorder);
        addActor(leftBorder);
        addActor(rightBorder);
        addActor(topBorder);

}

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        pixelBg.setSize(width,height);
        leftBorder.setSize(squareStyle.borderSize,height);
        rightBorder.setSize(squareStyle.borderSize,height);
        topBorder.setSize(width,squareStyle.borderSize);
        bottomBorder.setSize(width,squareStyle.borderSize);

        leftBorder.setPosition(0,0);
        rightBorder.setPosition(width - squareStyle.borderSize,0);
        bottomBorder.setPosition(0,0);
        topBorder.setPosition(0,height - squareStyle.borderSize);

    }

    public void addLabel(Label label){
        addActor(label);
        label.setPosition(getWidth() / 2,getHeight() / 2 + 10 , Align.center);
    }

    public void setBorderColor(Color borderColor) {
        leftBorder.setColor(borderColor);
        rightBorder.setColor(borderColor);
        bottomBorder.setColor(borderColor);
        topBorder.setColor(borderColor);
    }

    public static class SquareStyle{
        public float borderSize = 5;
        public Color borderColor = Color.valueOf("909495");
        public Color bgColor = Color.BLACK;
    }
}
