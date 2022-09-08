package com.mygdx.game.CombatLogic.FightLogic.Systems;

import com.mygdx.game.CombatLogic.BattlerFrame;
import com.mygdx.game.CombatLogic.FightLogic.Cast;
import com.mygdx.game.CombatLogic.FightLogic.SpellLogic.SpellBuff;
import com.mygdx.game.CombatLogic.FightLogic.SpellLogic.SpellWrapper;

public class BuffSystem
{
    public static BuffSystem instance = new BuffSystem();
    private BuffSystem()
    {}

    public void applyBuffs(BattlerFrame caster)
    {
        Cast currentCast = caster.getCurrentCast();




    }


    private static class ManaCostSystem
    {
        public boolean apply(SpellWrapper sw, SpellBuff buff)
        {
            return true;
        }
    }

}
