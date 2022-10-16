package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent;

public enum StaticStatus
{
    POISON("Poison", "OnPoison"),
    BURN("Burn","Burn"),
    FREEZE("Freeze", "8"),
    FROZEN("Frozen","Freeze"),
    BRITTLE("Brittle", "Brittle"),
    BROKEN("Broken", "Brittle"),
    WINDRUSH("Wind Rush", "29"),
    MOONFIRE("Moon Fire", "7"),
    DOUBLEEFFECT("Double Effect", "null"),
    CURSE("Curse","17"),
    ;
    private String name;
    private String icon;
    StaticStatus(String name, String icon)
    {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }
    public String getIcon()
    {
        return "assets/SpellSplash/Status/" + icon + ".png";
    }
}
