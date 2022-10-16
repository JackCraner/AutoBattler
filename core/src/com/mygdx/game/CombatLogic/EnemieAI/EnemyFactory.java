package com.mygdx.game.CombatLogic.EnemieAI;

import com.mygdx.game.CombatLogic.Battler;
import com.mygdx.game.CombatLogic.BattlerFrames.ModifierArray;
import com.mygdx.game.CombatLogic.ShopSystem.ShopFunction;
import com.mygdx.game.SpellLogic.Spell;
import com.mygdx.game.SpellLogic.SpellFactory;

import java.util.Random;

public class EnemyFactory
{

    private EnemyFactory()
    {

    }

    static Random  ran = new Random();
    public static Battler makeEnemy(int roundNumber)
    {

        int randomNumberOfSpells = ran.nextInt(9);
        Spell[] enemySpells = new Spell[randomNumberOfSpells + 1];
        for (int i = 0; i<randomNumberOfSpells+1;i++)
        {
            enemySpells[i] = ShopFunction.getShopSpells(roundNumber)[0];
        }
        Battler newEnemy = new Battler(100,50,new ModifierArray(),new ModifierArray(),enemySpells);
        return newEnemy;
    }
}
