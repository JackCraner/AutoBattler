package com.mygdx.game.Screens.BuyPhase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Cards.Card;
import com.mygdx.game.Screens.BuyPhase.Components.Deck;
import com.mygdx.game.Screens.BuyPhase.Components.Shop;

public class BuySceneMultiplexer extends InputMultiplexer
{
    Deck d;
    Stage stage;
    Shop shop;

    Card hold;
    float xOffset;
    float yOffset;

    Image bin;

    public BuySceneMultiplexer(Stage s, Deck d, Shop shop)
    {
        this.d= d;
        this.stage = s;
        this.shop = shop;

        bin = new Image(new Texture(Gdx.files.local("assets/Bin.png")));
        bin.setPosition(900,400);
        bin.setSize(100,100);
        stage.addActor(bin);
        bin.setVisible(false);


    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 coord = stage.screenToStageCoordinates(new Vector2(screenX,screenY));
        Actor a = d.hit(coord.x,coord.y,true);
        if (a instanceof Card)
        {

            hold = (Card)d.hit(coord.x,coord.y,true);

            xOffset = coord.x - hold.getX();
            yOffset = coord.y - hold.getY();
            hold.setScale(0.8f);
            d.holdCard(hold);
            bin.setVisible(true);

        }
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector2 coord = stage.screenToStageCoordinates(new Vector2(screenX,screenY));
        if (hold!=null)
        {

            if (coord.x > bin.getX() && coord.x<bin.getX()+bin.getWidth() && coord.y>bin.getY() && coord.y<bin.getY()+ bin.getHeight())
            {
                System.out.println("Bin");
                d.sellCard(hold);
                hold.remove();

            }
            else
            {
                d.placeCard(hold);
                hold.setScale(0.5f);

            }

            bin.setVisible(false);
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

}
