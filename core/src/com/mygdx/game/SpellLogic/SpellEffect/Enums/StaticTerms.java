package com.mygdx.game.SpellLogic.SpellEffect.Enums;

public enum StaticTerms
{
    BURN("Burn", "Every 3 turns deal damage equal to number of stacks"),
    UNIQUE("Unique", "Only 1 Copy of the Spell"),
    CHANNEL("Channel", "Spell Potency increases after each consecutive cast"),
    CURSE("Curse", "Spells have +1 Mana Cost and +1 Cast Time")
    ;
    private String name;
    private String description;
    StaticTerms(String name,String description)
    {
        this.name= name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
