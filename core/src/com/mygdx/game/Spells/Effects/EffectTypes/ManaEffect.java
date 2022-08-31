package com.mygdx.game.Spells.Effects.EffectTypes;

import com.mygdx.game.Screens.FightPhase.CombatSystem;
import com.mygdx.game.Screens.FightPhase.EffectSystem;
import com.mygdx.game.Spells.SpellEffectType;

import java.util.EnumSet;

public enum ManaEffect implements SpellEffectType
{
    MANA("Mana_Gain_Default", "Gain %.0f Mana"),
    POISON("Mana_Gain_Poison", "Gain 1 Mana for every %.0f Poison Stacks"),
;
    private String name;
    private String description;
    ManaEffect(String name, String descript)
    {
        this.description = descript;
        this.name = name;


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
        return CombatSystem.ManaSystem.instance;
    }
}
