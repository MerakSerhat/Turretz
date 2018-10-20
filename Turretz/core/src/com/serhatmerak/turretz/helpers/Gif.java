package com.serhatmerak.turretz.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by ADY on 10.10.2018. cCc
 */

public class Gif {
    private float timeelapsed = 0f;
    Animation<TextureRegion> gifAnimation;
    Texture gifstrip;
    private int width;
    private int height;

    public Gif(Texture texture, int cols, float fps) {
        gifstrip = texture;
//        gifstrip.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        width = gifstrip.getWidth() / cols;
        System.out.println("gifstrip"+width);
        height = gifstrip.getHeight();
        TextureRegion[][] animframes = TextureRegion.split(gifstrip,
                width,
                gifstrip.getHeight());

        gifAnimation = new Animation<TextureRegion>(1/fps, animframes[0]);

    }

    public Gif(TextureAtlas atlas, float fps) {
        gifAnimation = new Animation<TextureRegion>(1/fps, atlas.getRegions());

    }

    /*
        public void draw(Batch batch, float x, float y, float originX, float originY, int width, int height, float scaleX, float scaleY, float rotation) {
            timeelapsed += Gdx.graphics.getDeltaTime();
            TextureRegion currentFrame = gifAnimation.getKeyFrame(timeelapsed, true);

            batch.draw(currentFrame, x, y, originX, originY, width, height, scaleX, scaleY, rotation);

        }*/
    public TextureRegion getCurrentFrame() {
        timeelapsed += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = gifAnimation.getKeyFrame(timeelapsed, true);
        return currentFrame;
    }

    public int getGifWidth() {
        return width;
    }

    public int getGifHeight() {
        return height;
    }/*
    public void draw(Batch batch, float x, float y) {
        this.draw(batch, x, y, width / 2, height / 2, width, height, 1f, 1f, 0);
    }*/

}
