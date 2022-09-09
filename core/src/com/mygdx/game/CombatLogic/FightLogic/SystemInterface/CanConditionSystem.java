package com.mygdx.game.CombatLogic.FightLogic.SystemInterface;

import com.mygdx.game.CombatLogic.BattlerFrame;
import com.mygdx.game.CombatLogic.FightLogic.Cast;

public interface CanConditionSystem
{
    public boolean check(Cast c, BattlerFrame caster, BattlerFrame other);
}
