package com.mygdx.game.Screens.FightPhase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.CombatLogic.Battler;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.HealthComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.ManaComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.FightFrame;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.CombatLogic.CombatManager;
import com.mygdx.game.Screens.FightPhase.Components.BattlerMesh;
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
    BattlerMesh[] battlersMesh;

    CombatManager cE;
    LinkedList<FightFrame> combatLog;

    TextButton combatFinish;
    public FightScene(SingleGame g, Battler p, Battler e)
    {
        this.player = p;
        this.playerMesh = new BattlerMesh(player,1f);
        this.enemy = e;
        this.enemyMesh = new BattlerMesh(enemy,1f);
        this.game = g;


        viewport = new FillViewport(MyGdxGame.gameWidth,MyGdxGame.gameHeight);
        stage = new Stage(viewport);
        viewport.apply();
        Gdx.input.setInputProcessor(stage);

        img = new Texture(Gdx.files.local("assets/BattleField.png"));
        scene = new Image(img);
        scene.setPosition(0,0);
        scene.setSize(MyGdxGame.gameWidth,MyGdxGame.gameHeight);

        combatFinish = new TextButton("Next", MyGdxGame.skin);
        combatFinish.setSize(150,100);
        combatFinish.getLabel().setFontScale(1.5f);
        combatFinish.setPosition(MyGdxGame.gameWidth/2,500);
        combatFinish.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                endOfCombat(newFrame);
                game.returnToShop();
                return true;
            }
        });
        combatFinish.setVisible(false);

        battlersMesh = new BattlerMesh[]{playerMesh, enemyMesh};
    }

    public static float gameSpeed = 0.1f;
    public static int gameTicksInAturn = 20;
    float turnSpeed = gameTicksInAturn*gameSpeed;

    float turnTick = 0;
    float gameTick = 0;


    private boolean combatEnd = false;

    FightFrame newFrame = null;
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
                newFrame = combatLog.pop();
                for (int i = 0; i < battlersMesh.length; i++)
                {
                    battlersMesh[i].newFrame(newFrame.getBattleFrames()[i]);
                }


            }


            turnTick =0;
        }
        if (gameTick>=gameSpeed && !combatEnd)
        {
            for (BattlerMesh b: battlersMesh)
            {
                b.updateCastTime();
            }
            gameTick = 0;
        }

        if (combatEnd)
        {
            combatFinish.setVisible(true);
        }
        else
        {
            gameTick +=delta;
            turnTick +=delta;
        }
        if (combatLog.size() == 0)
        {
            combatEnd = true;

        }


    }



    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(scene);

        stage.addActor(playerMesh);

        stage.addActor(enemyMesh);
        stage.addActor(combatFinish);
        playerMesh.setPosition(500,700);
        enemyMesh.flip(true,false);
        enemyMesh.setPosition(1550,700);



        this.cE = new CombatManager();
        this.combatLog = cE.simulateFight(new Battler[]{player,enemy});

        FightFrame first = combatLog.pop();
        playerMesh.newFrame(first.getBattleFrames()[0]);
        enemyMesh.newFrame(first.getBattleFrames()[1]);
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
    public void endOfCombat(FightFrame finalFrame)
    {

        BattlerFrame playerFrame = finalFrame.getBattleFrames()[0];
        if (finalFrame.getBattleFrames()[1].getComponent(HealthComponent.class).getCurrentHealth() > 0)
        {
            game.loseLife();
        }
        else if (playerFrame.getComponent(HealthComponent.class).getCurrentHealth() > 0 )
        {
            game.winCrown();
        }
        game.nextTurn();
        this.player.setCurrentMana(playerFrame.getComponent(ManaComponent.class).getCurrentMana());
        this.player.setMaxMana(playerFrame.getComponent(ManaComponent.class).getMaxMana());
        this.player.setHealth(playerFrame.getComponent(HealthComponent.class).getMaxHealth());
    }
}
