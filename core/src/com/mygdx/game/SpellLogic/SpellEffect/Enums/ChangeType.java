package com.mygdx.game.SpellLogic.SpellEffect.Enums;

public enum ChangeType
{
    INCREASE("Increase"),
    DECREASE("Decrease"),

    ;
    private String name;
    ChangeType(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
