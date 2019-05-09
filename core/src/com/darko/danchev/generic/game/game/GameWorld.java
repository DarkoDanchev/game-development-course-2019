package com.darko.danchev.generic.game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.darko.danchev.generic.game.GenericGame;
import com.darko.danchev.generic.game.assets.Assets;
import com.darko.danchev.generic.game.model.Player;

public class GameWorld {

    private GenericGame genericGame;
    private World physicsWorld;
    private Player player;
    private Stage stage;

    public GameWorld(GenericGame genericGame){
        this.genericGame = genericGame;
        this.physicsWorld = new World(new Vector2(0f,-9.8f),false);

        this.player = new Player(genericGame,physicsWorld,genericGame.assets.manager.get(Assets.player, Texture.class),
                0.25f,GenericGame.WORLD_HEIGHT / 2,1,1);
        float ratio = (float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();
        this.stage = new Stage(new StretchViewport(GenericGame.WORLD_HEIGHT / ratio,GenericGame.WORLD_HEIGHT));
        this.stage.addActor(player);
    }

    public void render(){
        this.stage.draw();
        physicsWorld.step(Gdx.graphics.getDeltaTime(),6,2);
    }

    public void update(){
        this.stage.act();
        this.stage.getCamera().position.x = player.getX();
        if(Gdx.input.justTouched()){
            System.out.println("Jump");
            this.player.jump();
        }
    }
}
