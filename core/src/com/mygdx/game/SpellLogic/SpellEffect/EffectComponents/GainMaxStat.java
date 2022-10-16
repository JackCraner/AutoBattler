package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.GainMaxStatSystem;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement.IntFormat;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.ChangeType;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.StatType;

public class GainMaxStat extends IsEffectComponent
{
    private IntFormat strength;
    private StatType type;
    private ChangeType strengthType;

    public GainMaxStat(StatType type, IntFormat strength, ChangeType strengthType)
    {
        this.type = type;
        this.strength = strength;
        this.strengthType = strengthType;
    }
    public GainMaxStat(StatType type,int strength)
    {
        this(type,new IntFormat(Math.abs(strength)),(strength>0?ChangeType.INCREASE:ChangeType.DECREASE));
    }

    public ChangeType getStrengthType() {
        return strengthType;
    }

    public int getStrength(BattlerFrame[] battlers) {
        return strength.execute(battlers);
    }

    public StatType getType() {
        return type;
    }

    @Override
    public String printEffect() {
        return strengthType.getName() + " " + getTarget().getName(0) +" Max " + type.name() + " Permanently by " + strength.print();
    }

    @Override
    public void getExecution(BattlerFrame[] battlers, BattlerFrame[] newBattlers) {
        GainMaxStatSystem.instance.execute(this, battlers,newBattlers );
    }

    @Override
    public IsEffectComponent clone() {
        return new GainMaxStat(type,strength,strengthType);
    }
}
