package com.mygdx.game.Spells.Effects;

import com.mygdx.game.Spells.Effects.EffectTypes.CanEffectType;

public class Effect implements CanEffect
{

    private float strength;
    private TargetTypes target;
    private CanEffectType type;
    public Effect(CanEffectType type, float strength, TargetTypes target)
    {
        this.type = type;
        this.strength = strength;
        this.target = target;
    }


    public float getStrength() {
        return strength;
    }

    public TargetTypes getTarget() {
        return target;
    }



    public String getDescription()
    {
        return String.format(getType().getDescription(),strength);
    }

    public CanEffectType getType() {
        return type;
    }
}
