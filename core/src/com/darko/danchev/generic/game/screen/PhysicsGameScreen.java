package com.darko.danchev.generic.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.darko.danchev.generic.game.GenericGame;
import com.darko.danchev.generic.game.model.Ball;
import com.darko.danchev.generic.game.model.Wall;

public class PhysicsGameScreen implements Screen {

    private GenericGame genericGame;
    private World world;
    private OrthographicCamera camera;
    private Stage stage;
    private Ball ball;
    private Wall wall;

    public PhysicsGameScreen(GenericGame genericGame){
        this.genericGame = genericGame;
        this.world = new World(new Vector2(0,-9.82f), false);
        float ratio = (float)Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();
        this.stage = new Stage(new StretchViewport(GenericGame.WORLD_HEIGHT / ratio,GenericGame.WORLD_HEIGHT));
        this.camera = (OrthographicCamera)stage.getCamera();
        this.ball = new Ball(genericGame,world,new Texture("ball/redball.png"),3f,18f,1f);
        this.wall = new Wall(genericGame,world,new Texture("rectangle/wall.jpg"),-0.5f,1f,6f,1f);
        this.stage.addActor(ball);
        this.stage.addActor(wall);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //(39, 39, 39)
        Gdx.gl.glClearColor(39/255f, 39/255f, 39/255f, 1); // 	0, 51, 102
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        camera.update();
        world.step(delta,6,2);
        stage.draw();

        if(Gdx.input.justTouched()){
            ball.jump();
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
