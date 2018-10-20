package com.serhatmerak.turretz.helpers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Serhat Merak on 14.10.2018.
 */

public enum  WeaponStyle {
    WEAPON1("weapon1.png","WEAPON 1"),
    WEAPON2("weapon2.png","WEAPON 2"),
    WEAPON3("weapon3.png","WEAPON 3"),
    WEAPON4("weapon4.png","WEAPON 4"),
    WEAPON5("weapon5.png","WEAPON 5"),
    WEAPON6("weapon6.png","WEAPON 6",8),
    WEAPON7("weapon7.png","WEAPON 7",5),
    WEAPON8("weapon8.png","WEAPON 8",8),
    WEAPON9("weapon9.png","WEAPON 9",5);



    public String imgPath;
    public Color borderColor;
    public String name;
    public boolean hasAnim;
    public int frameCount;
    public boolean hasAtlas;

    WeaponStyle(String imgPath,String name){
        this.hasAnim = false;
        this.imgPath = imgPath;
        this.name = name;
    }

    WeaponStyle(String imgPath,String name,int frameCount){
        this.hasAnim = true;
        this.frameCount = frameCount;
        this.imgPath = imgPath;
        this.name = name;
    }

    WeaponStyle(String imgPath,String name,boolean hasAtlas){
        this.hasAtlas = hasAtlas;
        this.hasAnim = true;
        this.imgPath = imgPath;
        this.name = name;
    }
}
