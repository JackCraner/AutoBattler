package com.mygdx.game.Screens.BuyPhase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

import com.mygdx.game.CombatLogic.Battler;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.SingleGame;

public class BuyGUI extends Group
{

    Battler player;
    SingleGame game;
    TextButton turnFinish;
    public ProgressBar manaBar;
    public Label manaLabel;
    public Label turnLabel;


    private Image heart;
    private Label heartCounter;
    private Image[] crownCount = new Image[10];
    public BuyGUI(Battler player, SingleGame theGame)
    {
        int secondLayer = -80;
        this.player = player;
        this.game =theGame;
        turnFinish = new TextButton("Confirm", MyGdxGame.skin);
        turnFinish.setSize(150,100);
        turnFinish.getLabel().setFontScale(1.5f);
        turnFinish.setPosition(+300,secondLayer);
        turnFinish.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                game.findOpponent();
                return true;
            }
        });

        manaBar = new ProgressBar(0,50,1f,false, MyGdxGame.skin);
        manaBar.setColor(Color.BLUE);
        manaBar.setSize(500,100);
        manaBar.setPosition(-manaBar.getWidth()/2,secondLayer);
        manaBar.setValue(player.getCurrentMana());
        manaLabel = new Label(Integer.toString(player.getCurrentMana()),MyGdxGame.skin,"try");
        manaLabel.setPosition(-manaLabel.getWidth()/2,manaLabel.getHeight()/2+10 + secondLayer);
        manaLabel.setFontScale(2);
        manaLabel.setAlignment(Align.center);

        turnLabel = new Label("Turn: \n" + game.getTurnCount(),MyGdxGame.skin,"try");
        turnLabel.setPosition(-300-turnLabel.getWidth(),secondLayer);
        turnLabel.setFontScale(1.5f);
        turnLabel.setAlignment(Align.center);


        heart = new Image(new Texture(Gdx.files.local("assets/BuyScreen/Heart.png")));
        heart.setPosition(-490,0);
        heart.setSize(180,180);
        heartCounter = new Label(Integer.toString(game.getLivesLeft()),MyGdxGame.skin,"try");
        heartCounter.setPosition(-415,65);
        heartCounter.setAlignment(Align.center);
        heartCounter.setFontScale(1.3f);

        for (int i =0;i <crownCount.length;i++)
        {
            if (i<3)
            {
                crownCount[i] = new Image(new Texture(Gdx.files.local("assets/BuyScreen/CrownWon.png")));
            }
            else
            {
                crownCount[i] = new Image(new Texture(Gdx.files.local("assets/BuyScreen/Crown.png")));
            }
            crownCount[i].setSize(70,70);
            crownCount[i].setPosition(-300 + (i * 80),70);
            addActor(crownCount[i]);
        }

        addActor(turnLabel);
        addActor(turnFinish);
        addActor(manaBar);
        addActor(manaLabel);
        addActor(heart);
        addActor(heartCounter);


    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
    }


}
