package com.mygdx.game.Screens.BuyPhase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.mygdx.game.CombatLogic.Battler;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screens.BuyPhase.Components.Deck;
import com.mygdx.game.Screens.BuyPhase.Components.Shop;
import com.mygdx.game.SingleGame;

public class BuyScene extends ScreenAdapter
{
    Stage stage;
    Viewport viewport;
    OrthographicCamera oC;

    Battler player;



    SingleGame game;
    Texture img;
    Image scene;

    BuyGUI gui;
    Shop shop;
    Deck deck;

    BuySceneMultiplexer handler;

    ParticleEffect poisonParticles;
    public BuyScene(SingleGame g, Battler p)
    {

        //setup
        this.game = g;
        this.player = p;
        oC = new OrthographicCamera();

        viewport = new FillViewport(MyGdxGame.gameWidth,MyGdxGame.gameHeight, oC);
        stage = new Stage(viewport);
        viewport.apply();


        Gdx.input.setInputProcessor(stage);

        //background
        img = new Texture(Gdx.files.internal("BuyScreen.png"));
        scene = new Image(img);
        scene.setPosition(0,0);
        scene.setSize(MyGdxGame.gameWidth,MyGdxGame.gameHeight);

        //shop = new Shop(player);


        //Finish Button
        gui = new BuyGUI(player,game);
        deck = new Deck(player);
        shop = new Shop(this,player,deck);



    }
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.end();
        stage.act(delta);
        stage.draw();




    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void resize(int width, int height) {

        stage.getViewport().update(width,height,true);
        game.batch.setProjectionMatrix(stage.getCamera().combined);
    }

    public BuyGUI getGui() {
        return gui;
    }

    @Override
    public void show() {
        super.show();
        stage.addActor(scene);
        stage.addActor(deck);
        stage.addActor(shop);
        stage.addActor(gui);

        shop.setPosition(80,700);
        deck.setPosition(80,150);
        gui.setPosition((float)MyGdxGame.gameWidth/2+30,MyGdxGame.gameHeight - 300);
        handler = new BuySceneMultiplexer(stage,deck,shop);
        handler.addProcessor(stage);
        Gdx.input.setInputProcessor(handler);
    }
    @Override
    public void dispose() {
        super.dispose();
        stage.clear();
        stage.dispose();
        img.dispose();
    }
}
