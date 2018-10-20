package com.serhatmerak.turretz.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by ADY on 10.10.2018. cCc
 */

public class GifBatch extends SpriteBatch {

    public GifBatch()
    {
        super();
    }

    public void draw(Gif gif, float x, float y, float originX, float originY, int width, int height, float scaleX, float scaleY, float rotation) {
        this.draw(gif.getCurrentFrame(), x, y, originX, originY, width, height, scaleX, scaleY, rotation);
    }


    public void draw(Gif gif, float x, float y) {
        this.draw(gif, x, y, gif.getGifWidth() / 2, gif.getGifHeight() / 2, gif.getGifWidth(), gif.getGifHeight(), 1f, 1f, 0);
    }


}
