package com.mygdx.game.Screens.BuyPhase;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.Characters.Battler;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Spells.Spell;

import java.util.Random;

public class Shop extends Group
{

    float scale = 0.8f;

    int shopSize =3;
    Card card1;
    Card card2;
    Card card3;


    Random rand;


    TextButton rollShopButton;
    Battler player;
    Deck deck;
    BuyScene parent;
    public Shop(BuyScene parent, Battler p, Deck d)
    {
        this.player = p;
        this.parent = parent;
        this.rand = new Random();
        this.deck = d;
        setShop();

        rollShopButton = new TextButton("Roll", MyGdxGame.skin);
        rollShopButton.setSize(150,100);
        rollShopButton.getLabel().setFontScale(1.5f);
        rollShopButton.setPosition(450,-200);
        rollShopButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (spendMana(5))
                {
                    rollShop();
                }


                return super.touchDown(event, x, y, pointer, button);
            }
        });
        addActor(rollShopButton);
        //setBounds(getX(),getY(),getWidth(),getHeight());

    }

    private Spell getRandomSpell()
    {
        Spell[] spells = Spell.values();
        return spells[rand.nextInt(spells.length)];

    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
    }

    private void setShop()
    {
        card1 = new Card(getRandomSpell(),scale);
        card1.setPosition(0,0);
        card1.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                buyCard(card1);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        card2 = new Card(getRandomSpell(),scale);
        card2.setPosition(card2.getWidth(),0);
        card2.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                buyCard(card2);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        card3 = new Card(getRandomSpell(),scale);
        card3.setPosition(card3.getWidth()*2,0);
        card3.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                buyCard(card3);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        addActor(card1);
        addActor(card2);
        addActor(card3);
    }
    private void rollShop()
    {

        removeActor(card1);
        removeActor(card2);
        removeActor(card3);
        setShop();


    }
    private void buyCard(Card c)
    {
        if (spendMana(c.getSpell().getManaCost()))
        {
            player.getSpellDeck().addSpell(c.getSpell());
            deck.setDeck();
            rollShop();
        }


    }
    private boolean spendMana(int cost)
    {
        //return true;

        if (player.getMana() >= cost)
        {
            player.setMana(player.getMana() - cost);
            updateMana();
            return true;
        }
        return false;

    }

    public void updateMana()
    {
        parent.manaLabel.setText(Integer.toString(player.getMana()));
        parent.manaBar.setValue(player.getMana());
    }
    @Override
    public Actor hit(float x, float y, boolean touchable) {
        return super.hit(x, y, touchable);
    }
}
