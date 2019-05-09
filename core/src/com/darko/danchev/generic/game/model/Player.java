package com.darko.danchev.generic.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.darko.danchev.generic.game.GenericGame;


public class Player extends Image {

    private GenericGame genericGame;
    private World physicsWorld;
    private Body body;

    public Player(GenericGame genericGame, World physicsWorld, Texture appearance,float x, float y,
                  float width, float height){
        super(appearance);
        this.setX(x);
        this.setY(y);
        this.setOrigin(x,y);
        this.setWidth(width);
        this.setHeight(height);
        this.genericGame = genericGame;
        this.physicsWorld = physicsWorld;
        this.initBody();
    }

    private void initBody(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(),getY());
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = physicsWorld.createBody(bodyDef);

        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(getWidth(),getHeight());

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = bodyShape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.0f;

        body.createFixture(fixtureDef);

        bodyShape.dispose();
    }

    @Override
    public void act(float delta){
        this.setPosition(body.getPosition().x + getWidth() / 2,body.getPosition().y + getHeight() / 2);
        this.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
        System.out.println(getY() + " " + GenericGame.WORLD_HEIGHT);

        if(getY() > GenericGame.WORLD_HEIGHT){
            setY(-getHeight());
        }
    }

    public void jump(){
        body.setLinearVelocity(body.getLinearVelocity().x,0);
        body.applyForceToCenter(0, 750f, true);
    }

}
