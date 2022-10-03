package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.DealDamageSystem;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement.IntFormat;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.DamageTypes;

public class DealDamage extends IsEffectComponent
{
    private DamageTypes type;
    private IntFormat strength;
    public DealDamage(IntFormat strength, DamageTypes type)
    {
        this.strength = strength;
        this.type = type;
    }
    public DealDamage(IntFormat strength)
    {
        this(strength,DamageTypes.NEUTRAL);
    }
    public DealDamage(int strength, DamageTypes type) {
        this(new IntFormat(strength),type);
    }
    public DealDamage(int strength)
    {
        this(new IntFormat(strength),DamageTypes.NEUTRAL);
    }

    public DamageTypes getType() {
        return type;
    }

    public int getStrength(BattlerFrame[] battlers) {
        return strength.execute(battlers);
    }

    @Override
    public String printEffect() {
        return "Deal " +strength.print() + (type==DamageTypes.NEUTRAL?"":" "+type.name()) + " Damage to " + getTarget().getName(1);
    }

    @Override
    public void getExecution(BattlerFrame[] battlers)
    {
        DealDamageSystem.instance.execute(this, battlers);
    }

    @Override
    public IsEffectComponent clone() {
        return new DealDamage(strength,type);
    }


}
