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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import com.mygdx.game.CombatLogic.Battler;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Screens.BuyPhase.Components.Deck;
import com.mygdx.game.Screens.BuyPhase.Components.Shop;
import com.mygdx.game.SingleGame;

public class BuyScene extends ScreenAdapter
{
    Stage stage;
    Stage ui;
    Viewport viewport;
    Viewport uiView;
    OrthographicCamera oC;

    Battler player;



    SingleGame game;
    Texture img;
    Image scene;


    Shop shop;
    Deck deck;

    BuySceneMultiplexer handler;

    ParticleEffect poisonParticles;

    Table rootTable;
    public BuyScene(SingleGame g, Battler p)
    {

        //setup
        this.game = g;
        this.player = p;

        rootTable = new Table();
        rootTable.center();
        rootTable.setFillParent(true);
        oC = new OrthographicCamera();

        viewport = new FillViewport(MyGdxGame.gameWidth,MyGdxGame.gameHeight, oC);
        uiView = new ScreenViewport();
        stage = new Stage(viewport);
        ui = new Stage(uiView);
        viewport.apply();
        uiView.apply();
        Gdx.input.setInputProcessor(stage);

        //background
        img = new Texture(Gdx.files.internal("BuyScreen.png"));
        scene = new Image(img);
        scene.setPosition(0,0);
        scene.setSize(MyGdxGame.gameWidth,MyGdxGame.gameHeight);

        //shop = new Shop(player);


        //Finish Button

        deck = new Deck(player,0.5f);
        shop = new Shop(this,player,deck,0.8f);




    }
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.end();
        stage.act(delta);
        ui.act();
        stage.draw();
        ui.draw();



    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void resize(int width, int height) {

        stage.getViewport().update(width,height,true);
        ui.getViewport().update(width,height,true);
        stage.getCamera().position.set(MyGdxGame.gameWidth/2,MyGdxGame.gameHeight/2,0);
        stage.getCamera().update();
        ui.getCamera().update();


        viewport.apply();
        uiView.apply();
    }


    @Override
    public void show() {
        super.show();
        handler = new BuySceneMultiplexer(this,player,stage,ui,deck,shop);

        stage.addActor(scene);
        stage.addActor(shop);
        stage.addActor(deck);

        setUI();
        ui.addActor(rootTable);

        shop.setPosition(MyGdxGame.gameWidth/2,800);
        deck.setPosition(420,150);
        //gui.setPosition((float)MyGdxGame.gameWidth/2,MyGdxGame.gameHeight - 200);

        handler.addProcessor(ui);
        Gdx.input.setInputProcessor(handler);
    }
    @Override
    public void dispose() {
        super.dispose();
        stage.clear();
        stage.dispose();
        img.dispose();
    }


    public void setUI()
    {
        rootTable.clear();
        Table uiTable = new Table();
        Table innerUI = new Table();
        Table bottomButtons = new Table();

        ProgressBar manaBar;
        Label manaLabel;
        Label turnLabel;

        Image heart;
        Label heartCounter;
        Image[] crownCount = new Image[10];

        TextButton finishButton;
        TextButton rollButton;

        float tableWidth = Gdx.graphics.getWidth();
        float singleCell = tableWidth/20;

        System.out.println(player.getCurrentMana() + "  " + player.getMaxMana());
        manaBar = new ProgressBar(0,player.getMaxMana(),1f,false, MyGdxGame.skin);
        manaBar.setColor(Color.BLUE);
        manaBar.setValue(player.getCurrentMana());

        manaLabel = new Label(Integer.toString(player.getCurrentMana()) + " / " + player.getMaxMana(),MyGdxGame.skin,"try");
        manaLabel.setFontScale(Gdx.graphics.getDensity()/1.3f);
        manaLabel.setAlignment(Align.center);

        Stack manaBarStack = new Stack();
        manaBarStack.add(manaBar);
        manaBarStack.add(manaLabel);

        turnLabel = new Label("Turn: \n" + game.getTurnCount(),MyGdxGame.skin,"try");
        turnLabel.setFontScale(Gdx.graphics.getDensity() /2);
        turnLabel.setAlignment(Align.center);

        heart = new Image(new Texture(Gdx.files.local("assets/BuyScreen/Heart.png")));
        heartCounter = new Label(Integer.toString(game.getLivesLeft()),MyGdxGame.skin,"try");
        heartCounter.setAlignment(Align.center);
        heartCounter.setFontScale(Gdx.graphics.getDensity()/1.3f);
        Stack heartStack = new Stack();
        heartStack.add(heart);
        heartStack.add(heartCounter);

        uiTable.add(heartStack).fill().size(singleCell*2).space(30);
        for (int i =0;i <crownCount.length;i++)
        {
            if (i<game.getCrownCount())
            {
                crownCount[i] = new Image(new Texture(Gdx.files.local("assets/BuyScreen/CrownWon.png")));
            }
            else
            {
                crownCount[i] = new Image(new Texture(Gdx.files.local("assets/BuyScreen/Crown.png")));
            }

            innerUI.add(crownCount[i]).size(singleCell).space(10);
        }
        innerUI.row();
        innerUI.add(manaBarStack).colspan(crownCount.length).fill().padTop(50);
        uiTable.add(innerUI).expand();
        uiTable.add(turnLabel).fill().size(singleCell*2).space(30);

        finishButton = new TextButton("Confirm", MyGdxGame.skin);
        finishButton.getLabel().setFontScale(Gdx.graphics.getDensity());
        finishButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.findOpponent();
                return super.touchDown(event, x, y, pointer, button);
            }
        });


        rollButton = new TextButton("Roll", MyGdxGame.skin);
        rollButton.getLabel().setFontScale(Gdx.graphics.getDensity());

        bottomButtons.add(rollButton).size(singleCell*3,singleCell*2);
        bottomButtons.add().expandX();
        bottomButtons.add(finishButton).size(singleCell*3,singleCell*2);

        rootTable.add(uiTable).expandX().padTop(20);
        rootTable.row();
        rootTable.add().expand();
        rootTable.row();
        rootTable.add(bottomButtons).expandX().fill();

        rootTable.pack();
        uiTable.pack();
        innerUI.pack();
        bottomButtons.pack();

        rollButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                handler.rollButtonExecute();
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        manaBar.setName("ManaBar");
        manaLabel.setName("ManaLabel");

    }

    public Table getRootTable() {
        return rootTable;
    }
}
