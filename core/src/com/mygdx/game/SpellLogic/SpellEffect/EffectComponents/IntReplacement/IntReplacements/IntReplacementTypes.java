package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement.IntReplacements;

public enum IntReplacementTypes
{
    CurrentHealth("%s Current Health"),
    CurrentMana("%s Current Mana"),
    MaxMana("%s Max Mana"),
    MaxHealth("%s Max Health"),
    LastSpellMana("%s Mana Cost of Last Spell"),
    NumberOfSpells("Number of spells %s has"),
    NumberOfCasts("Number of spells %s has Casted"),
    TurnNumber("Current Turn Number %s"),

    ;
    String description;
    IntReplacementTypes(String description)
    {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
