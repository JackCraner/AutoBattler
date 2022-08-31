package com.mygdx.game.Spells.Effects;

import com.mygdx.game.Spells.SpellEffectType;

public enum EffectType implements SpellEffectType {
    DAMAGE("Damage","Deal %d Damage"),
    HEAL("Heal","Heal for %d"),
    POISONHEAL("Poison Heal", "Heal equal to enemy Poison Stacks"),
    COUNTERSPELL("CounterSpell","Cancel the Enemies next %d spells"),
    POISON("POISON","Apply %d Stacks of Poison"),
    BURN("Burn", "Apply %d Stacks of Burn"),
    EXPUNGE("Expunge","Poisons tick faster for %d seconds");

    private String description;
    private String name;
    EffectType(String name, String description)
    {
        this.description = description;
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
