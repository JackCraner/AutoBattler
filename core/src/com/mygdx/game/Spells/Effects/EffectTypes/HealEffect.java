package com.mygdx.game.Spells.Effects.EffectTypes;

import com.mygdx.game.CombatLogic.FightLogic.SystemInterface.CanEffectSystem;

public enum HealEffect implements CanEffectType {
    HEAL("Heal","Heal for %.0f"),
    POISONHEAL("Poison Heal", "Heal equal to enemy Poison Stacks"),
    ;
    private String name;
    private String description;
    HealEffect(String name,String description)
    {
        this.name= name;
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public CanEffectSystem getSystem() {
        return null;
    }
}
