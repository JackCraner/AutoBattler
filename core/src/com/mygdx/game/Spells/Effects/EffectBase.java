package com.mygdx.game.Spells.Effects;


import com.mygdx.game.Spells.SpellEffectType;

abstract public class EffectBase
{
    SpellEffectType type;

    public EffectBase(SpellEffectType type)
    {

        this.type = type;
    }

    public SpellEffectType getType() {
        return type;
    }

    abstract public String getDescription();
}
