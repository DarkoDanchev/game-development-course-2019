package com.darko.danchev.generic.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.darko.danchev.generic.game.GenericGame;

public class GameScreen implements Screen {

    private GenericGame genericGame;
    private SpriteBatch batch;
    private Sprite img;
    private Texture apple;
    private TextureRegion redApple;
    private TextureRegion greenApple;
    private TextureRegion blueApple;
    private TextureAtlas appleAtlas;
    private BitmapFont font;
    private  int score;

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
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0/255f, 51/255f, 102/255f, 1); // 	0, 51, 102
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //batch.draw(img, 20, 20,200,200);
        //img.draw(batch);
        //batch.draw(apple,(float)Gdx.graphics.getWidth() /2,(float)Gdx.graphics.getHeight() / 2,apple.getWidth() / 10f,apple.getHeight() / 10f);
        batch.draw(redApple,0,0,300,300);
        batch.draw(greenApple,0,300,300,300);
        batch.draw(blueApple,0,600,300,300);

        font.draw(batch,"This is my first text with Bitmap Font: " + score,20,1000);
        //texture1.draw
        //texture2.draw
        //texture3.draw
        batch.end();

        if(Gdx.input.justTouched()){
            score++;
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
        font.dispose();
    }
}
