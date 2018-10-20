package com.serhatmerak.turretz;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.serhatmerak.turretz.helpers.GameInfo;
import com.serhatmerak.turretz.helpers.GifBatch;
import com.serhatmerak.turretz.helpers.Weapon;
import com.serhatmerak.turretz.helpers.WeaponStyle;

import java.util.HashMap;

/**
 * Created by Serhat Merak on 15.10.2018.
 */

public class Planet extends Sprite{

    public PlanetStyle planetStyle;
    private Array<Weapon> weapons;

    public Planet(PlanetStyle planetStyle){
        super(new Texture(planetStyle.imgPath));
        this.planetStyle = planetStyle;
        setSize(planetStyle.size,planetStyle.size);
        setOrigin(getWidth() / 2,getHeight() / 2);
        weapons = new Array<Weapon>();
        for (int i = 0; i < planetStyle.slot; i++) {
            weapons.add(null);
        }
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
        for (Weapon weapon:weapons) {
            if (weapon != null){
                Vector2 worldCenter = new Vector2(getX() + getWidth() / 2,getY() + getHeight() / 2);
                Vector2 circlePos = new Vector2(worldCenter.x,worldCenter.y + 90 + planetStyle.size / 2);
                Vector2 pos = GameInfo.rotateAround(circlePos,worldCenter,weapon.getRotation());
                weapon.setPosition(pos.x,pos.y);
                weapon.draw(((GifBatch) batch));
            }
        }
    }

    public void addWeapon(Weapon weapon,int index){
        int degree = (360 / planetStyle.slot )* index;
        weapon.setRotation(degree);
        Vector2 worldCenter = new Vector2(getX() + getWidth() / 2,getY() + getHeight() / 2);
        Vector2 circlePos = new Vector2(worldCenter.x,worldCenter.y + 90 + planetStyle.size / 2);
        Vector2 pos = GameInfo.rotateAround(circlePos,worldCenter,degree);
        System.out.println("pos: " + pos + " rotation: " + degree);
        weapon.setPosition(pos.x,pos.y);
        weapons.set(index,weapon);
    }

    public void removeWeapon(int index){
        weapons.set(index,null);
    }

    public Weapon getWeapon(int index){
        return weapons.get(index);
    }

    public void update() {
        //her planet için farklı farklı döndür
        switch (planetStyle) {
            case PLANET1: {
                //todo
            }
            break;
            case PLANET2: {
                //todo
            }
            break;
            case PLANET3: {
                //todo
            }
            break;
            case PLANET4: {
                //todo
            }
        }
    }

    @Override
    public void rotate(float degrees) {
        super.rotate(degrees);
        for (Weapon weapon:weapons) {
            if (weapon != null)
                weapon.rotate(degrees);
        }
    }

    public enum PlanetStyle{
        PLANET1("planet1.png",6,320),
        PLANET2("planet1.png",8,320),
        PLANET3("planet1.png",10,400),
        PLANET4("planet1.png",12,400);

        public String imgPath;
        public int slot;
        public float size;


        PlanetStyle(String imgPath, int slot, float size){
            this.imgPath = imgPath;
            this.slot = slot;
            this.size = size;
        }

    }
}
