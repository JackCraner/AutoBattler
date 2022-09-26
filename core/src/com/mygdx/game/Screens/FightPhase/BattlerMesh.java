package com.mygdx.game.Screens.FightPhase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.AssetFinder.StaticToIcon;
import com.mygdx.game.Cards.CanCard;

import com.mygdx.game.Cards.SpellTOCard;
import com.mygdx.game.CombatLogic.Battler;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.BattlerState;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.CastComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.EffectListComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.HealthComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.ManaComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerStates;
import com.mygdx.game.CombatLogic.BattlerFrames.EffectOnBattler;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Cards.Card;

import com.mygdx.game.SingleGame;


import java.util.ArrayList;

public class BattlerMesh extends Group
{


    float scale = 1;
    float height = 478 ;
    float width =336;

    Battler host;
    BattlerSprite hostBody;
    Card currentSpell;
    ProgressBar castTime;
    ProgressBar health;
    Label healthLabel;
    ProgressBar mana;
    Label manaLabel;

    StatusRow statusRow;
    boolean isFlip = false;
    float pad = (60 * scale);


    int lastCD = 0;
    BattlerMesh(Battler b)
    {

        this.host = b;
        this.hostBody = new BattlerSprite(scale);
        addActor(hostBody);

        this.statusRow = new StatusRow();


        health = new ProgressBar(0, b.getHealth(),1f,false,MyGdxGame.skin);
        mana = new ProgressBar(0,b.getMaxMana(),1f,false,MyGdxGame.skin);

        castTime = new ProgressBar(0,1,1f,false, MyGdxGame.skin);
        health.setPosition(getPad(pad),-200);
        health.setWidth(width);
        health.setColor(Color.RED);
        healthLabel = new Label(Integer.toString(SingleGame.maxHealth),MyGdxGame.skin,"try");
        mana.setPosition(getPad(pad),-250);
        mana.setWidth(width);
        mana.setColor(Color.BLUE);
        manaLabel = new Label("50",MyGdxGame.skin,"try");
        manaLabel.setPosition(getPad(pad) +mana.getWidth()/2 - manaLabel.getWidth()/2,-250);
        healthLabel.setPosition(getPad(pad) + health.getWidth()/2 - healthLabel.getWidth()/2,-200);
        healthLabel.setAlignment(Align.center);
        manaLabel.setAlignment(Align.center);
        addActor(mana);
        addActor(health);
        statusRow.setPosition(health.getX() + health.getWidth()/2 + 50, health.getY() + 50);
        addActor(healthLabel);
        addActor(manaLabel);
        addActor(statusRow);
    }

    @Override
    public void setPosition(float x, float y)
    {
        super.setPosition(x, y);

    }

    @Override
    protected void positionChanged() {
        super.positionChanged();

    }
    public float getPad(float num)
    {
        return isFlip? -num:num;
    }

    public void newFrame(BattlerFrame bf)
    {
        CanCard s = new SpellTOCard(bf.getComponent(CastComponent.class).getSpell());
        currentSpell = new Card(s,0.8f);
        currentSpell.setPosition(getPad(pad),height + pad );
        currentSpell.remove();

        if (bf.getComponent(CastComponent.class).getCastTimer() == 1)
        {

            if (currentSpell !=null)
            {
                castTime.remove();

            }
            castTime = new ProgressBar(0,(s.getOrangeBox()*FightScene.gameTicksInAturn-1),1f,false, MyGdxGame.skin);

            if (bf.getComponent(BattlerState.class).getState() == BattlerStates.READY)
            {
                castTime.setWidth(hostBody.getWidth());
                castTime.setPosition(getPad(pad),height+ currentSpell.getHeight() + (100 * scale));
                addActor(castTime);
            }


        }
        addActor(currentSpell);
        updateHealthBar(bf);


        statusRow.setStatusRow( bf.getComponent(EffectListComponent.class).getEffectOnBattlers());


    }

    public void updateHealthBar(BattlerFrame bF)
    {
        health.setValue(bF.getComponent(HealthComponent.class).getCurrentHealth());
        mana.setValue(bF.getComponent(ManaComponent.class).getCurrentMana());
        healthLabel.setText(Integer.toString(bF.getComponent(HealthComponent.class).getCurrentHealth()));
        manaLabel.setText(Integer.toString(bF.getComponent(ManaComponent.class).getCurrentMana()));
    }



    public void updateCastTime()
    {
        castTime.setValue(castTime.getValue()+1);
    }



    public void flip(boolean a, boolean b)
    {

        isFlip = a;
        hostBody.flip(a,b);
        health.setPosition(getPad(pad),-200);
        mana.setPosition(getPad(pad),-250);
        manaLabel.setPosition(getPad(pad) +mana.getWidth()/2 - manaLabel.getWidth()/2,-250);
        healthLabel.setPosition(getPad(pad) + health.getWidth()/2 - healthLabel.getWidth()/2,-200);
        if (isFlip)
        {
            statusRow.setPosition(health.getX() - health.getWidth()/2 + 170, health.getY() + 50);
        }
        else
        {
            statusRow.setPosition(health.getX() + health.getWidth()/2 + 50, health.getY() + 50);
        }
    }



}
class StatusRow extends Group
{
    ArrayList<StatusBox> statusBoxes;
    public StatusRow()
    {
        statusBoxes = new ArrayList<>();

    }
    public void setStatusRow(ArrayList<EffectOnBattler> statuses)
    {
        clear();
        statusBoxes.clear();
        int counter =0;
        for (EffectOnBattler s: statuses)
        {
            StatusBox sB = new StatusBox(s);
            sB.setPosition(100 * counter + 10,0);
            statusBoxes.add(sB);
            addActor(sB);
            counter++;
        }
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
    }
    
    private class StatusBox extends Group
    {
        private Image icon;
        private Label stack;
        private float scale = 2;

        public StatusBox(EffectOnBattler status)
        {
            icon = new Image(new Texture(Gdx.files.local("assets/SpellSplash/Status/" + StaticToIcon.converter.get(status.getStatusObject().getStatus_name()) + ".png")));
            icon.setSize(100,100);
            stack = new Label(Integer.toString(status.getStackNumber()),MyGdxGame.skin,"try");
            stack.setSize(80,80);
            stack.setPosition(10,10);
            stack.setFontScale(2);
            stack.setAlignment(Align.center);
            stack.setColor(Color.WHITE);
            addActor(icon);
            addActor(stack);
        }

        @Override
        public void setPosition(float x, float y)
        {
            super.setPosition(x, y);
        }
    }
    
}

