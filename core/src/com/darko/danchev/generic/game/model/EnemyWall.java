package com.darko.danchev.generic.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.darko.danchev.generic.game.GenericGame;
import com.darko.danchev.generic.game.assets.Assets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyWall {

    private GenericGame genericGame;
    private World physicsWorld;
    private List<Enemy> enemyList;
    private float x;
    private boolean scored;
    private Stage stage;

    public EnemyWall(GenericGame genericGame, World physicsWorld,Stage stage,float x){
        this.genericGame = genericGame;
        this.physicsWorld = physicsWorld;
        this.stage = stage;
        this.x = x;
        this.scored = false;
        initWall();
    }

    private void initWall(){
        enemyList = new ArrayList<Enemy>(5);
        Random random = new Random();
        int index = random.nextInt(5);
        for(int i = 0; i < 5; i++){
            if(i != index) {
                Enemy enemy = new Enemy(genericGame, physicsWorld,
                        genericGame.assets.manager.get(Assets.enemy, Texture.class),
                        x, i * 4 + 2, 2, 4);
                enemyList.add(enemy);
            }
        }
        for(Enemy e: enemyList){
            stage.addActor(e);
        }

    }

    public float getX(){
        return x;
    }

    public float getWidth() {
        return enemyList.get(0).getWidth();
    }

    public boolean getScored(){
        return scored;
    }

    public void score(){
        scored = true;
    }

}
