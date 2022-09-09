package com.mygdx.game.Spells;

public enum DamageTypes
{
    FIRE ("Fire"),
    WIND ("Wind"),
    ICE ("Ice"),
    NATURE ("Nature"),
    ARCANE ("Arcane"),
    ROCK ("Rock"),
    ;
    String name;
    DamageTypes(String name)
    {
        this.name = name;

    }

    public String getName() {
        return name;
    }
}
