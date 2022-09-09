package com.mygdx.game.Spells.Effects;

import com.mygdx.game.CombatLogic.FightLogic.SystemInterface.CanEffectSystem;
import com.mygdx.game.CombatLogic.FightLogic.Systems.BuffSystem;
import com.mygdx.game.Spells.Effects.EffectTypes.CanBuff;
import com.mygdx.game.Spells.Effects.EffectTypes.CanEffectType;

public class BuffEffect implements CanEffect {

    CanBuff affected;
    TargetTypes target;
    float strength;
    float duration;
    public BuffEffect(CanBuff affected, float strength, float duration, TargetTypes target)
    {
        this.affected = affected;
        this.strength = strength;
        this.duration = duration;
        this.target = target;
    }


    @Override
    public float getStrength() {
        return strength;
    }


    @Override
    public String getDescription()
    {
        return String.format("Buff %s By %.0f%% for %.0f rounds",affected.getName(),strength,duration);

    }

    @Override
    public TargetTypes getTarget() {
        return target;
    }

    @Override
    public CanEffectType getType() {
        return affected;
    }

    @Override
    public CanEffectSystem getSystem() {
        return affected.getBuffSystem();
    }
}
