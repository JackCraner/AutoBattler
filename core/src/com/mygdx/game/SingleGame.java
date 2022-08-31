package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Characters.Battler;
import com.mygdx.game.Characters.CharacterTypes;
import com.mygdx.game.Screens.BuyPhase.BuyScene;
import com.mygdx.game.Screens.FightPhase.FightScene;

public class SingleGame extends ScreenAdapter
{


    Battler player;
    MyGdxGame parent;
    ScreenAdapter currentScreen;
    public SpriteBatch batch;
    public SingleGame(MyGdxGame parent)
    {
        this.parent = parent;
        this.batch = parent.batch;
        this.player = new Battler(CharacterTypes.PLAYER);
        parent.setScreen(new BuyScene(this,player));
    }




    public void findOpponent()
    {
        //generate enemy pass into fight Scene
        Battler enemy = new Battler(CharacterTypes.ENEMY);
        parent.setScreen(new FightScene(this,player,enemy));
    }
    public void returnToShop()
    {
        //p.reset();
       //parent.setScreen(new BuyScene(this,p));
    }

}

