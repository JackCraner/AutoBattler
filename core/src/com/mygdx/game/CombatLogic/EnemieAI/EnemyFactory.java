package com.mygdx.game.CombatLogic.EnemieAI;

import com.mygdx.game.CombatLogic.Battler;
import com.mygdx.game.CombatLogic.BattlerFrames.ModifierArray;
import com.mygdx.game.SpellLogic.Spell;
import com.mygdx.game.SpellLogic.SpellFactory;

public class EnemyFactory
{
    public static EnemyFactory instance = new EnemyFactory();
    private EnemyFactory()
    {

    }


    public Battler makeEnemy(int turnNumber)
    {

        Battler newEnemy = new Battler(20,50,new ModifierArray(),new ModifierArray(),new Spell[]{});
        return newEnemy;
    }
}
