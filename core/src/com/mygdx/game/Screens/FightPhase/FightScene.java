package com.mygdx.game.Screens.FightPhase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Characters.Battler;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.SingleGame;

import java.util.LinkedList;

public class FightScene extends ScreenAdapter
{
    Stage stage;
    Viewport viewport;
    OrthographicCamera oC;

    SingleGame game;
    Texture img;
    Image scene;

    Battler player;
    Battler enemy;

    BattlerMesh playerMesh;
    BattlerMesh enemyMesh;
    CombatEngine cE;
    LinkedList<FightFrame> combatLog;
    public FightScene(SingleGame g, Battler p, Battler e)
    {
        this.player = p;
        this.playerMesh = new BattlerMesh(player);
        this.enemy = e;
        this.enemyMesh = new BattlerMesh(enemy);
        this.game = g;


        viewport = new StretchViewport(MyGdxGame.gameWidth,MyGdxGame.gameHeight);
        stage = new Stage(viewport);
        viewport.apply();
        Gdx.input.setInputProcessor(stage);

        img = new Texture(Gdx.files.local("assets/BattleField.png"));
        scene = new Image(img);
        scene.setPosition(0,0);
        scene.setSize(MyGdxGame.gameWidth,MyGdxGame.gameHeight);




    }

    public static float gameSpeed = 0.1f;
    public static int gameTicksInAturn = 20;
    float turnSpeed = gameTicksInAturn*gameSpeed;

    float turnTick = 0;
    float gameTick = 0;



    @Override
    public void render(float delta) {


        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();


        game.batch.end();
        stage.act(delta);
        stage.draw();



        if (turnTick >= turnSpeed)
        {
            if (combatLog.size() > 0)
            {
                FightFrame newFrame = combatLog.pop();
                playerMesh.newFrame(newFrame.getPlayer());
                enemyMesh.newFrame(newFrame.getEnemy());


            }
            turnTick =0;
        }
        if (gameTick>=gameSpeed)
        {
            playerMesh.updateCastTime();
            enemyMesh.updateCastTime();
            gameTick = 0;
        }


        gameTick +=delta;
        turnTick +=delta;

    }



    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(scene);

        stage.addActor(playerMesh);

        stage.addActor(enemyMesh);
        playerMesh.setPosition(-30,640);
        enemyMesh.flip(true,false);
        enemyMesh.setPosition(780,640);



        this.cE = new CombatEngine(player,enemy);
        this.combatLog = cE.getFightSequence();

        FightFrame first = combatLog.pop();
        playerMesh.newFrame(first.getPlayer());
        enemyMesh.newFrame(first.getEnemy());
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
    public void dispose() {
        super.dispose();
        stage.clear();
        stage.dispose();
        img.dispose();
    }
}
