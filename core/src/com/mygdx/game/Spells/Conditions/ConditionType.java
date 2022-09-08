package com.mygdx.game.Spells.Conditions;

import com.mygdx.game.CombatLogic.FightLogic.SystemInterface.CanConditionSystem;
import com.mygdx.game.CombatLogic.FightLogic.Systems.ConditionSystem;

public enum ConditionType
{
    ONFIRE(ConditionSystem.OnPoison.instance),
    ONPOISON(ConditionSystem.OnPoison.instance),
    ONBURN(ConditionSystem.OnPoison.instance),
    ONFREEZE(ConditionSystem.OnPoison.instance),
    ONUNIQUE(ConditionSystem.OnPoison.instance),

    ;
    CanConditionSystem system;
    ConditionType(CanConditionSystem system)
    {
        this.system= system;
    }

    public CanConditionSystem getSystem() {
        return system;
    }
}
