package com.mygdx.game.Spells.Effects.EffectTypes;

import com.mygdx.game.CombatLogic.FightLogic.SystemInterface.CanEffectSystem;

public enum ManaEffect implements CanEffectType
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
    public CanEffectSystem getSystem() {
        return null;
    }
}
