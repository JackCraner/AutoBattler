package com.mygdx.game.CombatLogic.ShopSystem;

import com.mygdx.game.Cards.SpellTOCard;
import com.mygdx.game.CombatLogic.Battler;
import com.mygdx.game.SpellLogic.Spell;
import com.mygdx.game.SpellLogic.SpellFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ShopFunction
{
    private static List<SpellFactory>[] spellsSorted = sortSpells();


    private static List<SpellFactory>[] sortSpells()
    {
        List<SpellFactory>[] sortedSpellList = new List[]{new LinkedList(),new LinkedList(), new LinkedList(), new LinkedList(), new LinkedList(), new LinkedList()};
        for (SpellFactory spell:SpellFactory.values())
        {
            sortedSpellList[spell.getSpell().getManaCost()].add(spell);
        }
        return sortedSpellList;
    }



    public static Spell[] getShopSpells(int roundCount)
    {
        Spell[] shopSpells = new Spell[3];
        for (int i = 0; i<3;i++)
        {
            int shopTier = getShopTier(roundCount);
            int nextMana = getRandomSpellCost(shopTierProbabilities(shopTier));
            Spell randomSpell = getRandomSpellOfCost(shopTier + nextMana);
            shopSpells[i] = randomSpell;
        }


        return shopSpells;
    }

    public static boolean buyShopSpell(Spell s, Battler purchaser)
    {
        int buyPremium = 5;
        if (purchaser.getSpellList().getList().size() < 10)
        {
            if (purchaser.getCurrentMana() >= s.getManaCost()+ buyPremium)
            {
                purchaser.setCurrentMana(purchaser.getCurrentMana() - (s.getManaCost()+buyPremium));
                purchaser.getSpellList().addItem(s);
                return  true;
            }
        }

        return false;

    }



    private static int getShopTier(int roundCount)
    {
        return (int)(Math.floor((float)roundCount/2)<=5?Math.floor((float)roundCount/2):5);
    }
    private static int[] shopTierProbabilities(int shopTier)
    {
        switch (shopTier)
        {
            case 0:
                return new int[]{70,25,5,0,0,0};
            case 1:
                return new int[]{50,30,10,10,0,0};
            case 2:
                return new int[]{30,40,15,10,5,0};
            case 3:
                return new int[]{15,25,25,20,10,5};
            case 4:
                return new int[]{10,15,30,20,15,10};
            case 5:
                return new int[]{10,15,20,30,15,10};

        }
        return new int[]{100,0,0,0,0,0};
    }
    static Random random = new Random();
    private static int getRandomSpellCost(int[] shopTierProbabilities)
    {
        int number = random.nextInt(100);
        int bound = 0;
        for (int i =0;i <shopTierProbabilities.length;i++)
        {
            bound += shopTierProbabilities[i];
            if (number <=bound)
            {
                return i;
            }
        }
        return 0;
    }
    private static Spell getRandomSpellOfCost(int cost)
    {
        int ran = random.nextInt(spellsSorted[cost].size());
        return spellsSorted[cost].get(ran).getSpell();


    }

}
