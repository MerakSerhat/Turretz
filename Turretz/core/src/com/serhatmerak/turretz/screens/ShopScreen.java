package com.serhatmerak.turretz.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.serhatmerak.turretz.GameMain;
import com.serhatmerak.turretz.Planet;
import com.serhatmerak.turretz.custom_actors.ShopItemPanel;
import com.serhatmerak.turretz.custom_actors.ShopItemPanelListener;
import com.serhatmerak.turretz.helpers.GameInfo;
import com.serhatmerak.turretz.helpers.Gif;
import com.serhatmerak.turretz.helpers.GifBatch;
import com.serhatmerak.turretz.helpers.Weapon;
import com.serhatmerak.turretz.huds.MainHuds;
import com.serhatmerak.turretz.huds.ShopHuds;

import java.util.HashMap;

/**
 * Created by Serhat Merak on 6.10.2018.
 */

public class ShopScreen implements Screen,ShopItemPanelListener {
    private GameMain game;
    private OrthographicCamera camera;
    private StretchViewport viewport;
    private GifBatch batch;

    private ShopHuds shopHuds;

    private ShopItemPanel selectedItemPanel;
    private Array<Circle> circles;
    private HashMap<Circle,Integer> circleDegreeHashMap;
    private ShapeRenderer shapeRenderer;
    private Vector2 worldCenter,circlePos;
    private Planet planet1;


    public ShopScreen(GameMain game) {
        this.game = game;
        batch = game.batch;
        camera = new OrthographicCamera(GameInfo.WIDTH, GameInfo.HEIGHT);
        camera.setToOrtho(false, GameInfo.WIDTH, GameInfo.HEIGHT);
        viewport = new StretchViewport(GameInfo.WIDTH, GameInfo.HEIGHT, camera);

        shopHuds = new ShopHuds(batch, viewport, this);

        circles = new Array<Circle>();
        circleDegreeHashMap = new HashMap<Circle, Integer>();
        //halkaları yaratma
        worldCenter = new Vector2(GameInfo.WIDTH / 2,(GameInfo.HEIGHT / 4 )* 3);
        planet1 = new Planet(Planet.PlanetStyle.PLANET3);
        planet1.setPosition(worldCenter.x - planet1.planetStyle.size / 2,worldCenter.y - planet1.planetStyle.size / 2);
        int weaponCount = planet1.planetStyle.slot;
        for (int i = 0; i < weaponCount; i++) {
            worldCenter = new Vector2(GameInfo.WIDTH / 2,(GameInfo.HEIGHT / 4 )* 3);
            circlePos = new Vector2(GameInfo.WIDTH / 2,(GameInfo.HEIGHT / 4 )* 3 + (90 + planet1.planetStyle.size / 2));
            Circle circle = new Circle();
            circle.setPosition(GameInfo.rotateAround(circlePos,worldCenter,i * (360 / weaponCount)));
            circle.setRadius(100);
            circles.add(circle);
            circleDegreeHashMap.put(circle,i * (360 / weaponCount));
        }

        shapeRenderer = new ShapeRenderer();




        Gdx.input.setCatchBackKey(true);
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        InputProcessor inputProcessor = createInputProcessor();
        inputMultiplexer.addProcessor(inputProcessor);
        inputMultiplexer.addProcessor(shopHuds.stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

    }

    private InputProcessor createInputProcessor() {
        return new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                //Ekrandan parmağımızı çekince seçtiğimiz bir item varsa ona göre işlemler uygular
                if(selectedItemPanel != null) {
                    Vector3 point = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
                    for (Circle circle : circles) {
                        if (circle.contains(point.x, point.y) &&
                                planet1.getWeapon(circleDegreeHashMap.get(circle) / (360 / planet1.planetStyle.slot)) == null) {
                            planet1.addWeapon(new Weapon(selectedItemPanel.weaponStyle)
                                    ,circleDegreeHashMap.get(circle) / (360 / planet1.planetStyle.slot));
                            selectedItemPanel = null;
                            return false;
                        }
                    }
                    selectedItemPanel = null;
                }
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        };
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shopHuds.stage.act();
        shopHuds.stage.draw();



        camera.update();
        batch.setProjectionMatrix(camera.combined);


        if (Gdx.input.isKeyPressed(Input.Keys.BACK) || Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainScreen(game));
        }

        Vector3 point = camera.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(),0));


        batch.setColor(Color.WHITE);
        batch.begin();

        planet1.draw(batch);


        if (selectedItemPanel != null) {
            // seçili item varken elimiz herhangi bir halkanın üstünde mi kontrol eder
            Circle circle = checkPointCircleCollision(point);
            //değilse normal çizer
            if (circle == null)
                batch.draw(selectedItemPanel.textureRegion, point.x - 100, point.y,200,200);
            //halka üzerindeyse o halkaya göre açıyla çizer
            else if (planet1.getWeapon(circleDegreeHashMap.get(circle) / (360 / planet1.planetStyle.slot)) == null){
                batch.draw(new TextureRegion(selectedItemPanel.textureRegion), circle.x - 100, circle.y - 100,100,
                        100, 200, 200,1,1,circleDegreeHashMap.get(circle));
            }
        }
        //Daha önce kayıtlı halkalardaki itemleri çizer
//        for (Circle c:circles) {
//            if (circleWeaponHashMap.get(c) != null){
//                //Bir animasyon mu yoksa sadece resim mi onu kontrol ederi (WeaponTextureOrGif adındaki sınıfı kullanır -aşağıda-)
//                if (circleWeaponHashMap.get(c).gif == null) {
//                    TextureRegion textureRegion = circleWeaponHashMap.get(c).textureRegion;
//                    batch.draw(textureRegion, c.x - 100, c.y - 100,
//                            100, 100,
//                            200, 200, 1, 1, circleDegreeHashMap.get(c));
//                }else {
//                    Gif gif = circleWeaponHashMap.get(c).gif;
//                    batch.draw(gif, c.x - 100, c.y - 100,
//                            100, 100,
//                            200, 200, 1, 1, circleDegreeHashMap.get(c));
//                }
//        }
//        }
        batch.end();

        //item seçtiğimizde elimizi kaydırırken scroll da kaymasın diye item seçiliyken scrollu dokunulmaz yapar
        if (selectedItemPanel != null && shopHuds.upgradeScrollPane.getTouchable() == Touchable.enabled) {
            shopHuds.upgradeScrollPane.setTouchable(Touchable.disabled);
            shopHuds.upgradeScrollPane.cancel();
        }else if (selectedItemPanel == null && shopHuds.upgradeScrollPane.getTouchable() == Touchable.disabled)
            shopHuds.upgradeScrollPane.setTouchable(Touchable.enabled);


        //halkaları çizer
//        shapeRenderer.setProjectionMatrix(camera.combined);
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        for (Circle c :circles) {
//            shapeRenderer.circle(c.x,c.y,c.radius);
//        }
//        shapeRenderer.end();

        //Dolu halkaya dokununca hakladaki itemi siler
        if (Gdx.input.justTouched()){
            for (Circle c:circles) {
                if (c.contains(point.x,point.y) &&
                        planet1.getWeapon(circleDegreeHashMap.get(c) / (360 / planet1.planetStyle.slot)) != null){
                    planet1.removeWeapon(circleDegreeHashMap.get(c) / (360 / planet1.planetStyle.slot));
                    return;
                }
            }
        }
    }

    private Circle checkPointCircleCollision(Vector3 point) {
        for (Circle circle:circles) {
            if (circle.contains(point.x,point.y)){
                return circle;
            }
        }
        return null;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {

    }

    @Override
    public void touchDown(ShopItemPanel shopItemPanel) {
        selectedItemPanel = shopItemPanel;
    }
    
}
