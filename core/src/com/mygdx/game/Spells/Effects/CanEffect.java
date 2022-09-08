package com.mygdx.game.Spells.Effects;

import com.mygdx.game.Spells.Effects.EffectTypes.CanEffectType;

public interface CanEffect
{

    public float getStrength();
    public String getDescription();
    public TargetTypes getTarget();
    public CanEffectType getType();
}
