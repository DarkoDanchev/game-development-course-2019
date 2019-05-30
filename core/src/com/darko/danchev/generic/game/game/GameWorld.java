package com.darko.danchev.generic.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.darko.danchev.generic.game.GenericGame;
import com.darko.danchev.generic.game.assets.Assets;
import com.darko.danchev.generic.game.listener.B2dContactListener;
import com.darko.danchev.generic.game.model.EnemyWall;
import com.darko.danchev.generic.game.model.Player;
import com.darko.danchev.generic.game.screen.MenuScreen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameWorld {

    private GenericGame genericGame;
    private World physicsWorld;
    private Player player;
    private Stage stage;
    private List<EnemyWall> enemyWalls;
    private float worldWidth;
    private int score;

    //private SpriteBatch batch;
    //private Box2DDebugRenderer debugRenderer;

    public GameWorld(GenericGame genericGame){
        this.genericGame = genericGame;
        this.physicsWorld = new World(new Vector2(0,-9.8f),false);
        this.physicsWorld.setContactListener(new B2dContactListener());
        this.player = new Player(genericGame,physicsWorld,genericGame.assets.manager.get(Assets.player, Texture.class),
                0.25f,GenericGame.WORLD_HEIGHT / 2,1,1);
        float ratio = (float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();
        this.worldWidth = GenericGame.WORLD_HEIGHT / ratio;
        this.stage = new Stage(new StretchViewport(worldWidth,GenericGame.WORLD_HEIGHT));

        this.stage.addActor(player);

        this.initWalls();

        this.score = 0;



        //this.batch = new SpriteBatch();
        //this.debugRenderer = new Box2DDebugRenderer();

    }

    public void render(){
        this.stage.draw();
        physicsWorld.step(Gdx.graphics.getDeltaTime(),6,2);

        /*this.batch.begin();
        this.debugRenderer.render(physicsWorld,stage.getCamera().combined);
        this.batch.end();*/
    }

    public void update(){
        this.stage.act();
        if(!this.player.getTransition()) {
            this.stage.getCamera().position.x = player.getX() + 5;
        }
        if(Gdx.input.justTouched()){
            this.player.jump();
        }
        this.regenerateWall();
        this.updateScore();
        if(genericGame.gameState == GenericGame.GAME_STATE.MENU){
            if(genericGame.highscore < score){
                genericGame.highscore = score;
                genericGame.updateHighscore(score);
            }
            genericGame.setScreen(new MenuScreen(genericGame));
        }
    }

    private void initWalls(){
        enemyWalls = new ArrayList<EnemyWall>(8);
        EnemyWall first = new EnemyWall(genericGame,physicsWorld,stage,15);
        enemyWalls.add(first);
        for(int i = 1; i < 8; i++){
            EnemyWall enemyWall = new EnemyWall(genericGame,physicsWorld,stage,enemyWalls.get(i -1).getX() + 15);
            enemyWalls.add(enemyWall);
        }
    }

    private void regenerateWall(){
        if(enemyWalls.get(0).getX() < stage.getCamera().position.x - worldWidth / 2){
            enemyWalls.remove(0);
        }
        if(enemyWalls.size() == 7){
            EnemyWall enemyWall = new EnemyWall(genericGame,physicsWorld,stage,enemyWalls.get(enemyWalls.size() -1).getX() + 15);
            enemyWalls.add(enemyWall);
        }
    }

    private void updateScore()
    {
        if(player.getX() > enemyWalls.get(0).getX() && !enemyWalls.get(0).getScored()){
            enemyWalls.get(0).score();
            this.score++;
        }
    }

    public int getScore(){
        return this.score;
    }
}
