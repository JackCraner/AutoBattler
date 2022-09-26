package com.mygdx.game.Screens.BuyPhase.Components;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.game.Cards.CanCard;
import com.mygdx.game.Cards.SpellTOCard;
import com.mygdx.game.CombatLogic.Battler;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Cards.Card;
import com.mygdx.game.SpellLogic.Spell;

import java.util.ArrayList;

public class Deck extends Group
{
    Battler player;
    float overlap =100;

    ArrayList<Card> deckCards;

    public Deck(Battler p)
    {

        this.player = p;
        setDeck();
        setBounds(0,0, MyGdxGame.gameWidth,300);

    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        return super.hit(x, y, touchable);
    }

    public void setDeck()
    {

        deckCards = new ArrayList<>();
        clear();

        Spell[] spellList = player.getSpellList().getList().toArray(new Spell[0]);

        for (int i = 0; i < spellList.length;i++)
        {
            Card c = new Card(new SpellTOCard(spellList[i]),0.5f);
            c.setPosition(overlap * i,0);

            deckCards.add(c);
            addActor(c);
        }


    }
    public void orderPlayerSpells()
    {
        ArrayList<Spell> spellList = new ArrayList<>();
        for (Card c: deckCards)
        {
            spellList.add(((SpellTOCard) c.getCardItem()).getSpell());
        }
        player.setSpellList(spellList);
    }
    private int currentIndex;
    public void pushDeck(int index)
    {
        if (index != currentIndex)
        {
            formDeck();
            currentIndex = index;
            for (int i = 0;i <deckCards.size();i++)
            {

                if (index >= 0 && i>=index)
                {
                    deckCards.get(i).setPosition(deckCards.get(i).getX() + 300,deckCards.get(i).getY());
                }




            }
        }

    }
    public void formDeck()
    {
        for (int i = 0;i <deckCards.size();i++)
        {
            deckCards.get(i).setZIndex(i);
           deckCards.get(i).setPosition(overlap*i,0);
        }
    }
    public void holdCard(Card c)
    {
        currentIndex = deckCards.indexOf(c);
        c.setZIndex(deckCards.size());
        deckCards.remove(c);
    }
    public void placeCard(Card c)
    {
        deckCards.add(currentIndex,c);
        orderPlayerSpells();
        formDeck();
    }
    public void sellCard(Card c)
    {
        deckCards.remove(c);
        orderPlayerSpells();
    }




    public ArrayList<Card> getDeckCards() {
        return deckCards;
    }




    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        setDeck();
    }
}
