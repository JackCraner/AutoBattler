package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.game.CombatLogic.Battler;
import com.mygdx.game.CombatLogic.BattlerFrames.ModifierArray;
import com.mygdx.game.Screens.BuyPhase.BuyScene;
import com.mygdx.game.Screens.FightPhase.FightScene;
import com.mygdx.game.SpellLogic.Spell;
import com.mygdx.game.SpellLogic.SpellFactory;

public class SingleGame extends ScreenAdapter
{


    Battler player;
    MyGdxGame parent;

    public static int maxHealth = 100;
    public static int maxMana = 50;
    ScreenAdapter currentScreen;
    public SpriteBatch batch;

    private int livesLeft;
    private int turnCount;
    private int crownCount;
    public SingleGame(MyGdxGame parent)
    {
        this.parent = parent;
        this.batch = parent.batch;
        this.player = new Battler(20,50,new ModifierArray(),new ModifierArray(),new Spell[]{});
        player.getSpellList().addItem(SpellFactory.FIRE0_1.getSpell());
        livesLeft = 3;
        parent.setScreen(new BuyScene(this,player));
    }

    public int getLivesLeft() {
        return livesLeft;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public int getCrownCount() {
        return crownCount;
    }

    public void loseLife()
    {
        livesLeft --;
    }
    public void nextTurn()
    {
        System.out.println("Test " + turnCount);
        turnCount++;

    }
    public void winCrown()
    {
        crownCount++;
    }


    public void findOpponent()
    {
        //generate enemy pass into fight Scene
        Battler enemy = new Battler(20,50,new ModifierArray(),new ModifierArray(),new Spell[]{SpellFactory.FIRE0_1.getSpell()});
        parent.setScreen(new FightScene(this,player,enemy));
    }
    public void returnToShop()
    {
        //player.setMana(player.getMana() + 10);
       parent.setScreen(new BuyScene(this,player));
    }

}

