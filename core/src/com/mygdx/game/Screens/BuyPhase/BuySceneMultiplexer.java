package com.mygdx.game.Screens.BuyPhase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.Cards.Card;
import com.mygdx.game.CombatLogic.Battler;
import com.mygdx.game.Screens.BuyPhase.Components.Deck;
import com.mygdx.game.Screens.BuyPhase.Components.Shop;

public class BuySceneMultiplexer extends InputMultiplexer
{
    BuyScene scene;
    Battler player;
    Deck d;
    Stage stage;
    Stage ui;
    Shop shop;

    Card hold;
    float xOffset;
    float yOffset;



    public BuySceneMultiplexer(BuyScene scene,Battler player,Stage s, Stage ui,Deck d, Shop shop)
    {
        this.d= d;
        this.stage = s;
        this.shop = shop;
        this.ui = ui;
        this.player = player;
        this.scene = scene;



    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 coord = stage.screenToStageCoordinates(new Vector2(screenX,screenY));


        if (d.hit(coord.x,coord.y,true) !=null)
        {

            Actor a = d.hit(coord.x,coord.y,true);
            if (a instanceof Card)
            {

                hold = (Card)a;

                xOffset = coord.x - hold.getX();
                yOffset = coord.y - hold.getY();
                hold.setScale(1.3f);
                d.holdCard(hold);
                d.setBinVisibility(true);

            }
        }


        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector2 coord = stage.screenToStageCoordinates(new Vector2(screenX,screenY));


        if (shop.hit(coord.x,coord.y,true)!=null)
        {
            Actor a = shop.hit(coord.x,coord.y,true);

            if (a instanceof Card)
            {
                shop.buyCard(player,(Card)a);
                d.setDeck();
                updateManaBar();
            }
        }

        if (hold!=null)
        {

            Actor a = d.hit(coord.x,coord.y,true);
            if (a instanceof Image)
            {

                d.sellCard(hold);
                hold.remove();

            }
            else
            {
                d.placeCard(hold);
                hold.setScale(0.5f);

            }

            d.setBinVisibility(false);

            hold=null;
        }


        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 coord = stage.screenToStageCoordinates(new Vector2(screenX,screenY));

        if (hold != null)
        {

            hold.setPosition(coord.x - xOffset,coord.y -yOffset);

            for (int i = 0;i<d.getDeckCards().size();i++)
            {

                if (d.getDeckCards().get(i).getX() > hold.getX())
                {

                    d.pushDeck(i);
                    return true;
                }
            }
            d.pushDeck(d.getDeckCards().size());

        }


        return super.touchDragged(screenX, screenY, pointer);
    }

    private void updateManaBar()
    {
        ProgressBar bar = scene.getRootTable().findActor("ManaBar");
        Label label = scene.getRootTable().findActor("ManaLabel");

        bar.setValue(player.getCurrentMana());
        label.setText(Integer.toString(player.getCurrentMana())+ " / " + player.getMaxMana());

    }

    public void confirmButtonExecute()
    {
        scene.game.findOpponent();
    }
    public void rollButtonExecute()
    {

        shop.rollShop(player,true);
        updateManaBar();
    }

}
