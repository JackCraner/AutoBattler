package com.mygdx.game.Spells.Effects.EffectTypes;

import com.mygdx.game.Screens.FightPhase.CombatSystem;
import com.mygdx.game.Screens.FightPhase.EffectSystem;
import com.mygdx.game.Spells.SpellEffectType;

public enum HealEffect implements SpellEffectType {
    HEAL("Heal","Heal for %d"),
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
    public EffectSystem getSystem() {
        return CombatSystem.HealSystem.instance;
    }
}
