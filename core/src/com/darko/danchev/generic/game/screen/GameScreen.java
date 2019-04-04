package com.darko.danchev.generic.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.darko.danchev.generic.game.GenericGame;

public class GameScreen implements Screen {

    private GenericGame genericGame;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Stage stage;
    private Image image1;
    private Image image2;
    private Sprite img;
    private Texture apple;
    private TextureAtlas appleAtlas;
    private TextureRegion redApple;
    private TextureRegion greenApple;
    private TextureRegion blueApple;
    private BitmapFont font;
    private int score;


    /*
        R: 0 - 1f
        G: 0 - 1f
        B: 0 - 1f

        123, 150, 180
        R: 123 / 255f
        G: 150 /255f
        B: 180 /255f
     */

    public GameScreen(GenericGame genericGame) {
        this.genericGame = genericGame;
    }

    @Override
    public void show() {
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false,GenericGame.WIDTH,GenericGame.HEIGHT );
        this.camera.position.set(camera.viewportWidth * 0.5f,camera.viewportHeight * 0.5f,0);
        this.stage = new Stage(new StretchViewport(GenericGame.WIDTH,GenericGame.HEIGHT));
        Texture imgTexture = new Texture("img/badlogic.jpg");
        this.img = new Sprite(imgTexture);
        this.img.setPosition(20,20);
        this.apple = new Texture("img/apple.png");
        this.appleAtlas = new TextureAtlas("apple/apple.atlas");
        this.redApple = appleAtlas.findRegion("red_apple");
        this.greenApple = appleAtlas.findRegion("green_apple");
        this.blueApple = appleAtlas.findRegion("blue_apple");
        this.font = new BitmapFont();
        this.font.getData().scale(1.4f);
        this.score = 0;

        this.image1 = new Image(new Texture("apple/red_apple.jpg"));
        this.image2 = new Image(new Texture("apple/blue_apple.jpg"));
        this.image1.setPosition(GenericGame.WIDTH / 2, GenericGame.HEIGHT / 2);
        this.image2.setPosition(GenericGame.WIDTH / 2,GenericGame.HEIGHT / 2 + image1.getWidth());
        this.stage.addActor(image1);
        this.stage.addActor(image2);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0/255f, 51/255f, 102/255f, 1); // 	0, 51, 102
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        moveCamera();
        camera.update();
        stage.act();
        batch.begin();
        //batch.draw(img, 20, 20,200,200);
        //img.draw(batch);
        //batch.draw(apple,(float)Gdx.graphics.getWidth() /2,(float)Gdx.graphics.getHeight() / 2,apple.getWidth() / 10f,apple.getHeight() / 10f);
        batch.draw(redApple,0,0,300,300);
        batch.draw(greenApple,0,300,300,300);
        batch.draw(blueApple,0,600,300,300);
        font.draw(batch,"This is my first Bitmap Font: " + score,20,1000);
        //texture1.draw
        //texture2.draw
        //texture3.draw
        batch.end();

        stage.draw();

        if(Gdx.input.justTouched()){
            score++;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.N)){
            genericGame.setScreen(new PhysicsGameScreen(genericGame));
        }
    }

    private void moveCamera(){
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            camera.translate(new Vector2(-450*Gdx.graphics.getDeltaTime(),0));
            if(!(image2.getX() + image2.getWidth() > stage.getCamera().viewportWidth)){
                System.out.println("MOVING: " + stage.getCamera().viewportWidth);
                stage.getCamera().translate(new Vector3(-450*Gdx.graphics.getDeltaTime(),0,0));
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            camera.translate(new Vector2(450*Gdx.graphics.getDeltaTime(),0));
            stage.getCamera().translate(new Vector3(450*Gdx.graphics.getDeltaTime(),0,0));

        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            camera.translate(new Vector2(0,450*Gdx.graphics.getDeltaTime()));
            stage.getCamera().translate(new Vector3(0,450*Gdx.graphics.getDeltaTime(),0));

        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            camera.translate(new Vector2(0,-450*Gdx.graphics.getDeltaTime()));
            stage.getCamera().translate(new Vector3(0,-450*Gdx.graphics.getDeltaTime(),0));

        }
    }

    @Override
    public void resize(int width, int height) {

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
        batch.dispose();
        appleAtlas.dispose();
    }
}
