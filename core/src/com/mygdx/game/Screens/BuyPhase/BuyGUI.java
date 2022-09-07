package com.mygdx.game.Screens.BuyPhase;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Characters.Battler;
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
    public BuyGUI(Battler player, SingleGame theGame)
    {
        this.player = player;
        this.game =theGame;
        turnFinish = new TextButton("Confirm", MyGdxGame.skin);
        turnFinish.setSize(150,100);
        turnFinish.getLabel().setFontScale(1.5f);
        turnFinish.setPosition(+300,0);
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
        manaBar.setPosition(-manaBar.getWidth()/2,0);
        manaBar.setValue(player.getMana());
        manaLabel = new Label(Integer.toString(player.getMana()),MyGdxGame.skin,"try");
        manaLabel.setPosition(-manaLabel.getWidth()/2,manaLabel.getHeight()/2+10);
        manaLabel.setFontScale(2);
        manaLabel.setAlignment(Align.center);

        turnLabel = new Label("Turn: \n 1",MyGdxGame.skin,"try");
        turnLabel.setPosition(-300-turnLabel.getWidth(),0);
        turnLabel.setFontScale(1.5f);
        turnLabel.setAlignment(Align.center);

        addActor(turnLabel);
        addActor(turnFinish);
        addActor(manaBar);
        addActor(manaLabel);


    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
    }


}
