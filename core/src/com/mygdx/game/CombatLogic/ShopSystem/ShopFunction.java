package com.mygdx.game.CombatLogic.ShopSystem;

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
    private static int getShopTier(int roundCount)
    {
        return (int)(Math.floor((float)roundCount/2)<=5?Math.floor((float)roundCount/2):5);
    }
    private static int[] shopTierProbabilities(int shopTier)
    {
        switch (shopTier)
        {
            case 0:
            case 1:
            case 2:
            case 3:
                return new int[]{60,30,10};
            case 4:
                return new int[]{70,30,0};
            case 5:
                return new int[]{100,0,0};

        }
        return new int[]{100,0,0};
    }
    static Random random = new Random();
    private static int getRandomSpellCost(int[] shopTierProbabilities)
    {
        int number = random.nextInt(100);
        if (number < shopTierProbabilities[0])
        {
            return 0;
        }
        else if (number < shopTierProbabilities[0]+shopTierProbabilities[1])
        {
            return 1;
        }
        else
        {
            return 2;
        }
    }
    private static Spell getRandomSpellOfCost(int cost)
    {
        System.out.println(cost);
        if (cost>0)
        {
            int ranCost = random.nextInt(cost);
            int ran = random.nextInt(spellsSorted[ranCost].size());
            return spellsSorted[ranCost].get(ran).getSpell();
        }
        else
        {
            int ran = random.nextInt(spellsSorted[cost].size());
            return spellsSorted[cost].get(ran).getSpell();
        }

    }

}
