package com.mygdx.game.SpellLogic.SpellEffect.Enums;

public enum TargetType
{

    SELF (0),
    OTHER(1),
    BOTH(2),
    ;

    private int value;
    TargetType(int value)
    {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
