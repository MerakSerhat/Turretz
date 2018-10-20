package com.serhatmerak.turretz.helpers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Serhat Merak on 15.10.2018.
 */

public class Weapon {

    private WeaponStyle weaponStyle;
    //todo veritabanondan çek
    public int level = 0;
    private Vector2 position;
    private int rotation;
    private Gif gif;
    private TextureRegion textureRegion;


    public Weapon(WeaponStyle weaponStyle){
        this.weaponStyle = weaponStyle;
        position = new Vector2();
        if (weaponStyle.hasAnim)
            if(!weaponStyle.hasAtlas)
                gif = new Gif(new Texture("gif-" + weaponStyle.imgPath),weaponStyle.frameCount,12);
            else
                gif = new Gif(new TextureAtlas("gif-" + weaponStyle.imgPath.replace("png","pack")),12);
        else
            textureRegion = new TextureRegion(new Texture(weaponStyle.imgPath));
    }



    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public int getRotation() {
        return rotation;
    }

    public void setPosition(float x,float y) {
        position.set(x,y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void rotate(float rotation){
        this.rotation += rotation;
    }

    public void draw(GifBatch batch){
        if (weaponStyle.hasAnim){
            (batch).draw(gif,position.x - 100, + position.y - 100,
                    100, 100,
                    200, 200, 1, 1, rotation);
        }else {
            batch.draw(textureRegion, position.x - 100, position.y - 100,
                    100, 100,
                    200, 200, 1, 1, rotation);
        }
    }

//    public float getX(){
//        return position.x;
//    }
//
//    public float getY(){
//        return position.y;
//    }
}
