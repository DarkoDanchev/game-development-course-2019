package com.darko.danchev.generic.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.darko.danchev.generic.game.GenericGame;

public class MenuScreen implements Screen {

    private GenericGame genericGame;
    private SpriteBatch batch;

    public MenuScreen(GenericGame genericGame){
        this.genericGame = genericGame;
    }

    @Override
    public void show() {
        this.batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0/255f, 51/255f, 102/255f, 1); // 	0, 51, 102
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.end();

        if(Gdx.input.justTouched()){
            genericGame.gameState = GenericGame.GAME_STATE.PLAYING;
            genericGame.setScreen(new GameScreen(genericGame));
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

    }
}
