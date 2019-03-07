package com.darko.danchev.generic.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.darko.danchev.generic.game.GenericGame;

public class GameScreen implements Screen {

    private GenericGame genericGame;
    private SpriteBatch batch;
    private Sprite img;
    private Texture apple;

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
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0/255f, 51/255f, 102/255f, 1); // 	0, 51, 102
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //batch.draw(img, 20, 20,200,200);
        img.draw(batch);
        batch.draw(apple,(float)Gdx.graphics.getWidth() /2,(float)Gdx.graphics.getHeight() / 2,apple.getWidth() / 10f,apple.getHeight() / 10f);
        //texture1.draw
        //texture2.draw
        //texture3.draw
        batch.end();
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
    }
}
