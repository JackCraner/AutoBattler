package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame extends Game {
	SpriteBatch batch;

	public static int gameWidth = 1080;
	public static int gameHeight = 1920;

	SingleGame currentGame;
	public static Skin skin;


	@Override
	public void create () {
		batch = new SpriteBatch();


		skin = new Skin(Gdx.files.internal("plain-james/skin/plain-james-ui.json"));

		this.currentGame = new SingleGame(this);
	}

	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
