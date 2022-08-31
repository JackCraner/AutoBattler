package com.mygdx.game.Spells.Effects;

public class Effect extends EffectBase
{

    private int strength;

    public Effect(EffectType type, int strength)
    {
        super(type);
        this.strength = strength;
    }


    public int getStrength() {
        return strength;
    }

    public String getDescription()
    {
        return String.format(getType().getDescription(),strength);
    }

}
