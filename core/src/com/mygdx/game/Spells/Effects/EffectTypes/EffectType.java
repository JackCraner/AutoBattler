package com.mygdx.game.Spells.Effects.EffectTypes;

import com.mygdx.game.Screens.FightPhase.CombatEngine;
import com.mygdx.game.Screens.FightPhase.CombatSystem;
import com.mygdx.game.Screens.FightPhase.EffectSystem;
import com.mygdx.game.Spells.SpellEffectType;

public enum EffectType implements SpellEffectType {


    COUNTERSPELL("CounterSpell","Block the Enemies next %.0f spells", CombatSystem.CounterSpellSystem.instance),
    POISON("POISON","Apply %.0f Stacks of Poison", CombatSystem.PoisonSystem.instance),
    BURN("Burn", "Apply %.0f Stacks of Burn",CombatSystem.BurnSystem.instance),
    EXPUNGE("Expunge","Poisons tick faster for %.0f seconds", CombatSystem.ExpungeSystem.instance);

    private String description;
    private String name;
    private EffectSystem system;
    EffectType(String name, String description, EffectSystem system)
    {
        this.description = description;
        this.name = name;
        this.system = system;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    @Override
    public EffectSystem getSystem() {
        return system;
    }
}
