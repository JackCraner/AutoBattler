package com.mygdx.game.Spells.Conditions;

import com.mygdx.game.CombatLogic.FightLogic.SystemInterface.CanConditionSystem;
import com.mygdx.game.CombatLogic.FightLogic.Systems.ConditionSystem;

public enum ConditionType
{
    NULL("",ConditionSystem.OnPoison.instance),
    ONFIRE("If Target is Burning ",ConditionSystem.OnPoison.instance),
    ONPOISON("If Target is Poisoned ",ConditionSystem.OnPoison.instance),
    ONBURN("",ConditionSystem.OnPoison.instance),
    ONFREEZE("If Target is Frozen ",ConditionSystem.OnPoison.instance),
    ONUNIQUE("If Spell is Unique ",ConditionSystem.OnPoison.instance),
    ONHP("If you have below %.0f HP ",ConditionSystem.OnPoison.instance),
    ONPERCENTAGE("%.0f%% Chance to ",ConditionSystem.OnPoison.instance),

    ;
    String description;
    CanConditionSystem system;
    ConditionType(String description , CanConditionSystem system)
    {
        this.description = description;
        this.system= system;
    }

    public String getDescription() {
        return description;
    }

    public CanConditionSystem getSystem() {
        return system;
    }
}
