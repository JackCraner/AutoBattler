package com.mygdx.game.Spells.Effects.EffectTypes;

import com.mygdx.game.CombatLogic.FightLogic.SystemInterface.CanBuffSystem;
import com.mygdx.game.CombatLogic.FightLogic.SystemInterface.CanEffectSystem;
import com.mygdx.game.CombatLogic.FightLogic.Systems.BuffSystem;
import com.mygdx.game.CombatLogic.FightLogic.Systems.CombatSystem;
import com.mygdx.game.Spells.Effects.CanEffect;
import com.mygdx.game.Spells.Effects.EffectTypes.CanEffectType;

public enum DamageTypes implements CanBuff
{
    FIRE ("Fire"),
    WIND ("Wind"),
    ICE ("Ice"),
    NATURE ("Nature"),
    ARCANE ("Arcane"),
    ROCK ("Rock"),
    ;
    String name;
    DamageTypes(String name)
    {
        this.name = name;

    }

    @Override
    public String getDescription() {
        return name + " Damage";
    }

    public String getName() {
        return name;
    }

    @Override
    public CanEffectSystem getSystem() {
        return CombatSystem.DamageSystem.instance;
    }

    @Override
    public CanBuffSystem getBuffSystem() {
        return BuffSystem.DamageBuffSystem.instance;
    }
}
