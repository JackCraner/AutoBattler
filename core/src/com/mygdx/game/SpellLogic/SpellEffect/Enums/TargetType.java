package com.mygdx.game.SpellLogic.SpellEffect.Enums;

public enum TargetType
{

    SELF (0, "Your", "Yourself"),
    OTHER(1,"the Opponent's", "the Opponent"),
    ;

    private int value;
    private String name;
    private String name2;
    TargetType(int value, String name, String name2)
    {
        this.value = value;
        this.name = name;
        this.name2 = name2;
    }
    public String getName(int value)
    {
        switch (value)
        {
            case 0:
                return name;
            case 1:
                return name2;
        }
        return "";
    }
    public int getValue() {
        return value;
    }
}
