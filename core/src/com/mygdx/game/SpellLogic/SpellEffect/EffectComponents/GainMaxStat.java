package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.GainMaxStatSystem;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.StatType;

public class GainMaxStat extends IsEffectComponent
{
    private int strength;
    private StatType type;
    public GainMaxStat(StatType type,int strength)
    {
        this.strength = strength;
        this.type = type;
    }

    public int getStrength() {
        return strength;
    }

    public StatType getType() {
        return type;
    }

    @Override
    public String printEffect() {
        return (strength>0?"Increase ": "Decrease ") + getTarget().getName(0) + " " + type.name() + " by" + strength;
    }

    @Override
    public void getExecution(BattlerFrame[] battlers) {
        GainMaxStatSystem.instance.execute(this, battlers);
    }

    @Override
    public IsEffectComponent clone() {
        return new GainMaxStat(type,strength);
    }
}
