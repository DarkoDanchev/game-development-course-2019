package com.darko.danchev.generic.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.darko.danchev.generic.game.GenericGame;
import com.darko.danchev.generic.game.assets.Assets;

public class MenuScreen implements Screen {

    private GenericGame genericGame;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Texture menuBackground;
    private BitmapFont font;

    public MenuScreen(GenericGame genericGame){
        this.genericGame = genericGame;
    }

    @Override
    public void show() {
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false,GenericGame.WIDTH,GenericGame.HEIGHT);
        //this.camera.position.set(-0)
        this.camera.update();
        this.menuBackground = genericGame.assets.manager.get(Assets.menu_background, Texture.class);
        this.font = new BitmapFont();

        this.font.getData().scale(8);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0/255f, 51/255f, 102/255f, 1); // 	0, 51, 102
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        camera.update();
        batch.begin();
        batch.draw(menuBackground,0,0);
        drawHighScore(genericGame.highscore,batch);
        batch.end();

        if(Gdx.input.justTouched()){
            genericGame.gameState = GenericGame.GAME_STATE.PLAYING;
            genericGame.setScreen(new GameScreen(genericGame));
        }
    }

    private void drawHighScore(int score,SpriteBatch batch){

        GlyphLayout glyphLayout = new GlyphLayout();
        String item = "Highscore: " + score;
        glyphLayout.setText(font,item);
        float w = glyphLayout.width;
        font.draw(batch, glyphLayout, camera.position.x - w/2, 300);
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
