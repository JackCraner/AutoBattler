package com.mygdx.game.CombatLogic.FightLogic.SystemInterface;

import com.mygdx.game.CombatLogic.BattlerFrame;
import com.mygdx.game.CombatLogic.FightLogic.Cast;
import com.mygdx.game.Spells.Conditions.Condition;

public interface CanConditionSystem
{
    public boolean check(Condition c, BattlerFrame caster, BattlerFrame other);
}
