package com.serhatmerak.turretz.helpers;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Serhat Merak on 4.10.2018.
 */

public class GameInfo {
    public static final float WIDTH=1080f;
    public static final float HEIGHT=1920f;
    public static Vector2 rotateAround(Vector2 vector, Vector2 origin, float rotation){
        return vector.sub(origin).rotate(rotation).add(origin);
    }
}
