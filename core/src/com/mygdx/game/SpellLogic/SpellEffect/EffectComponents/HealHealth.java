package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.HealSystem;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement.IntFormat;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.ChangeType;

public class HealHealth extends IsEffectComponent
{
    private IntFormat strength;
    private ChangeType type;
    public HealHealth(IntFormat strength, ChangeType type)
    {
        this.strength = strength;
        this.type = type;
    }

    public ChangeType getType() {
        return type;
    }

    public HealHealth(int strength) {
        this(new IntFormat(strength),(strength>0?ChangeType.INCREASE:ChangeType.DECREASE));
    }

    public float getStrength(BattlerFrame[] battlers) {
        return strength.execute(battlers);
    }

    @Override
    public String printEffect()
    {
        return "Heal " + getTarget().getName(1) + " By " + strength.print();

    }

    @Override
    public void getExecution(BattlerFrame[] battlers, BattlerFrame[] newBattlers) {
        HealSystem.instance.execute(this,battlers,newBattlers );
    }

    @Override
    public IsEffectComponent clone() {
        return new HealHealth(strength,type);
    }
}
