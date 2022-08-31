package com.mygdx.game.Screens.BuyPhase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Characters.Battler;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screens.FightPhase.FightScene;
import com.mygdx.game.SingleGame;

public class BuyScene extends ScreenAdapter
{
    Stage stage;
    Viewport viewport;
    OrthographicCamera oC;

    Battler player;

    TextButton turnFinish;
    ProgressBar manaBar;
    Label manaLabel;

    SingleGame game;
    Texture img;
    Image scene;


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

        viewport = new StretchViewport(MyGdxGame.gameWidth,MyGdxGame.gameHeight, oC);
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
        turnFinish = new TextButton("Confirm", MyGdxGame.skin);
        turnFinish.setSize(150,100);
        turnFinish.getLabel().setFontScale(1.5f);
        turnFinish.setPosition(MyGdxGame.gameWidth-200,MyGdxGame.gameHeight-200);
        turnFinish.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                game.findOpponent();
                return true;
            }
        });

        manaBar = new ProgressBar(0,50,1f,false, MyGdxGame.skin);
        manaBar.setColor(Color.BLUE);
        manaBar.setSize(670,100);
        manaBar.setPosition((((float)MyGdxGame.gameWidth-200 )/2) - manaBar.getWidth()/2,MyGdxGame.gameHeight - 200);
        manaBar.setValue(player.getMana());
        manaLabel = new Label(Integer.toString(player.getMana()),MyGdxGame.skin,"try");
        manaLabel.setPosition((((float)MyGdxGame.gameWidth-200 )/2) - manaLabel.getWidth()/2,MyGdxGame.gameHeight - 170);
        manaLabel.setFontScale(2);
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

    @Override
    public void show() {
        super.show();
        stage.addActor(scene);
        stage.addActor(deck);
        stage.addActor(shop);
        stage.addActor(manaBar);
        stage.addActor(manaLabel);
        stage.addActor(turnFinish);

        shop.setPosition(10,700);
        deck.setPosition(10,30);
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
