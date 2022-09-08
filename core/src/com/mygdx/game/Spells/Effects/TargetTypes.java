package com.mygdx.game.Spells.Effects;

public enum TargetTypes
{
    OTHER(1, "Enemy"),
    SELF(0, "Yourself"),
    ;
    int index;
    String name;
    TargetTypes(int index, String name)
    {
        this.index = index;
        this.name = name;
    }

    public int getValue() {
        return index;
    }

    public String getName() {
        return name;
    }
}
