package com.darko.danchev.generic.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.darko.danchev.generic.game.assets.Assets;
import com.darko.danchev.generic.game.screen.GameScreen;

public class GenericGame extends Game {

	public static float WIDTH = 2520; //pixels
	public static float HEIGHT = 4160;

	public static float WORLD_HEIGHT = 20; // the unit is meters

	public Assets assets;

	@Override
	public void create () {
		this.assets = new Assets();
		this.assets.load(); // ASYNC
		while (!this.assets.manager.update()){
			System.out.println("Loading: " + this.assets.manager.getLoadedAssets());
		}
		this.setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		this.assets.dispose();
	}
}
