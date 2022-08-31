package com.mygdx.game.Spells.Effects;

import com.mygdx.game.Spells.Effects.EffectTypes.EffectType;
import com.mygdx.game.Spells.SpellEffectType;

public class Effect extends EffectBase
{

    private float strength;

    public Effect(SpellEffectType type, float strength)
    {
        super(type);
        this.strength = strength;
    }


    public float getStrength() {
        return strength;
    }

    public String getDescription()
    {
        return String.format(getType().getDescription(),strength);
    }

}
