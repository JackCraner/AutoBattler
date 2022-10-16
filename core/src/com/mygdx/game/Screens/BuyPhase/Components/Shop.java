package com.mygdx.game.Screens.BuyPhase.Components;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.Cards.CanCard;
import com.mygdx.game.Cards.SpellTOCard;

import com.mygdx.game.CombatLogic.Battler;
import com.mygdx.game.CombatLogic.ShopSystem.ShopFunction;
import com.mygdx.game.Screens.BuyPhase.BuyScene;
import com.mygdx.game.Cards.Card;
import com.mygdx.game.SpellLogic.Spell;
import com.mygdx.game.SpellLogic.SpellFactory;


import java.util.Random;

public class Shop extends Group
{

    float scale;

    int shopSize =3;
    Card cards[];

    Table shopTable;


    Random rand;


    Battler player;
    Deck deck;
    BuyScene parent;
    public Shop(BuyScene parent, Battler p, Deck d,float scale)
    {
        this.player = p;
        this.shopTable = new Table();
        this.cards = new Card[shopSize];
        this.parent = parent;
        this.rand = new Random();
        this.deck = d;
        this.scale = scale;
        setShop();



    }

    private CanCard getRandomSpell()
    {
        return new SpellTOCard( SpellFactory.values()[rand.nextInt(SpellFactory.values().length)].getSpell());


    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
    }

    private void setShop()
    {
        Spell[] spells = ShopFunction.getShopSpells(parent.getParent().getTurnCount());
        for (int i = 0;i  <cards.length;i++)
        {
            cards[i] = new Card(new SpellTOCard(spells[i]),scale);
            shopTable.add(cards[i]).space(50);
        }
        addActor(shopTable);
    }
    public void rollShop(Battler b,boolean pay)
    {
        if (pay)
        {
            if (b.getCurrentMana() >=5)
            {
                shopTable.clear();
                setShop();
                b.setCurrentMana(b.getCurrentMana() - 5);
            }
        }
        else
        {
            shopTable.clear();
            setShop();
        }




    }
    public void buyCard(Battler p ,Card c)
    {
        if (c.getCardItem() instanceof SpellTOCard)
        {
            Spell s = ((SpellTOCard)c.getCardItem()).getSpell();
            if (ShopFunction.buyShopSpell(s,p))
            {
                rollShop(p,false);
            }
        }

    }


    @Override
    public Actor hit(float x, float y, boolean touchable) {
        return super.hit(x-getX(), y-getY(), touchable);
    }
}
