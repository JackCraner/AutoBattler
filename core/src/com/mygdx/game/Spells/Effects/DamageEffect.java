package com.mygdx.game.Spells.Effects;

import com.mygdx.game.CombatLogic.FightLogic.SystemInterface.CanEffectSystem;
import com.mygdx.game.CombatLogic.FightLogic.Systems.CombatSystem;
import com.mygdx.game.Spells.DamageTypes;
import com.mygdx.game.Spells.Effects.EffectTypes.CanEffectType;

public class DamageEffect implements CanEffect, CanEffectType
{
    DamageTypes damageType;
    float strength;
    TargetTypes targetTypes;

    public DamageEffect(DamageTypes damageType, float strength, TargetTypes targetTypes)
    {
        this.strength = strength;
        this.targetTypes = targetTypes;
        this.damageType = damageType;
    }

    public DamageTypes getDamageType() {
        return damageType;
    }


    @Override
    public String getName() {
        return "Damage_" + damageType.name();
    }

    @Override
    public CanEffectSystem getSystem() {
        return CombatSystem.DamageSystem.instance;
    }

    @Override
    public float getStrength() {
        return strength;
    }

    @Override
    public String getDescription() {
        return String.format("Deal %.0f " + damageType.getName() + " Damage to " + targetTypes.getName(),strength);
    }

    @Override
    public TargetTypes getTarget() {
        return targetTypes;
    }

    @Override
    public CanEffectType getType() {
        return this;
    }
}