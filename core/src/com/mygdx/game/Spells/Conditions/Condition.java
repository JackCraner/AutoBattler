package com.mygdx.game.Spells.Conditions;

import com.mygdx.game.Perks.Perk;
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
    ConditionObject condition;
    CanEffect[] effectOnFail;
    public Condition(CanEffect[] effectsOnPass, ConditionObject condition, CanEffect[] effectsOnFail)
    {
        this.effectOnPass = effectsOnPass;
        this.condition = condition;
        this.effectOnFail = effectsOnFail;

    }
    public Condition(CanEffect[] effects)
    {
        this(effects,new ConditionObject(ConditionType.NULL,0),new CanEffect[]{});
    }


    public ConditionType getCondition() {
        return condition.type;
    }

    public float getValue()
    {
        return condition.value;
    }

    public String getDescription()
    {
        return condition.getDescription();



    }


    public CanEffect[] getEffectOnFail() {
        return effectOnFail;
    }

    public CanEffect[] getEffectOnPass() {
        return effectOnPass;
    }

    public CanEffect[] getEffect(boolean check)
    {
        return check?effectOnPass:effectOnFail;
    }
}
