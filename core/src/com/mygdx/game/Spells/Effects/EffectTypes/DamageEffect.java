package com.mygdx.game.Spells.Effects.EffectTypes;

import com.mygdx.game.Screens.FightPhase.CombatSystem;
import com.mygdx.game.Screens.FightPhase.EffectSystem;
import com.mygdx.game.Spells.SpellEffectType;

public enum DamageEffect implements SpellEffectType {

    DAMAGE("Damage","Deal %d Damage"),
    ;
    private String name;
    private String description;
    DamageEffect(String name, String description)
    {
        this.name = name;
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
        return CombatSystem.DamageSystem.instance;
    }
}
