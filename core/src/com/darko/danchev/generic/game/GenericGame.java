package com.darko.danchev.generic.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.darko.danchev.generic.game.assets.Assets;
import com.darko.danchev.generic.game.screen.SplashScreen;

public class GenericGame extends Game {

	public enum GAME_STATE{
		PLAYING,
		MENU
	}

	public static float WIDTH = 2520; //pixels
	public static float HEIGHT = 4160;

	public static float WORLD_HEIGHT = 20; // the unit is meters

	public Assets assets;
	public GAME_STATE gameState;
	public int highscore;
	private Preferences preferences;

	@Override
	public void create () {
		this.assets = new Assets();
		this.setScreen(new SplashScreen(this));
		this.preferences = Gdx.app.getPreferences("highscorePreferences");
		if(preferences.contains("highscore")) {
			this.highscore = preferences.getInteger("highscore");
		} else {
			updateHighscore(0);
			this.highscore = 0;
		}
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

	public void updateHighscore(int newHighscore){
		preferences.putInteger("highscore",newHighscore);
		preferences.flush();
	}
}
