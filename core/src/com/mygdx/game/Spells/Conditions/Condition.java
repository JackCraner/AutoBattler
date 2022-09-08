package com.mygdx.game.Spells.Conditions;

import com.mygdx.game.Spells.Effects.CanEffect;

public class Condition
{
    /**
     * If has debuff x?
     * If has buff x?
     * If health > x
     * If health < x
     * If unique
     */

    CanEffect[] effectOnPass;
    ConditionType condition;
    CanEffect[] effectOnFail;
    public Condition(CanEffect[] effectsOnPass, ConditionType condition, CanEffect[] effectsOnFail)
    {
        this.effectOnPass = effectsOnPass;
        this.condition = condition;
        this.effectOnFail = effectsOnFail;

    }

    public ConditionType getCondition() {
        return condition;
    }

    public CanEffect[] getEffectOnFail() {
        return effectOnFail;
    }

    public CanEffect[] getEffectOnPass() {
        return effectOnPass;
    }
}
