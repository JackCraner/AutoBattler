package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.GainManaSystem;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement.IntFormat;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.ChangeType;

public class GainLoseMana extends IsEffectComponent
{
    private IntFormat strength;
    private ChangeType type;
    public GainLoseMana(IntFormat strength, ChangeType type)
    {
        this.strength = strength;
        this.type = type;
    }
    public GainLoseMana(int strength)
    {
        this(new IntFormat(Math.abs(strength)),(strength>0?ChangeType.INCREASE:ChangeType.DECREASE));
    }

    public int getStrength(BattlerFrame[] battlers) {
        return strength.execute(battlers);
    }

    public ChangeType getType() {
        return type;
    }

    @Override
    public String printEffect() {
        return type.getName() + getTarget().getName(0) + " Mana by " + strength.print();
    }

    @Override
    public void getExecution(BattlerFrame[] battlers, BattlerFrame[] newBattlers) {
        GainManaSystem.instance.execute(this, battlers,newBattlers );
    }

    @Override
    public IsEffectComponent clone() {
        return new GainLoseMana(strength,type);
    }
}
