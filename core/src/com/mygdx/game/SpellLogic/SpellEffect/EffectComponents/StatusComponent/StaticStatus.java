package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent;

public enum StaticStatus
{
    POISON("Poison"),
    BURN("Burn"),
    FREEZE("Freeze"),
    FROZEN("Frozen"),
    BRITTLE("Brittle"),
    BROKEN("Broken"),
    WINDRUSH("Wind Rush"),
    MOONFIRE("Moon Fire"),
    DOUBLEEFFECT("Double Effect"),

    ;
    private String name;
    StaticStatus(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
