package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.DealDamageSystem;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.DamageTypes;

public class DealDamage extends IsEffectComponent
{
    private DamageTypes type;
    private float strength;
    public DealDamage(float strength, DamageTypes type) {
        this.strength= strength;
        this.type = type;
    }

    public DamageTypes getType() {
        return type;
    }

    public float getStrength() {
        return strength;
    }

    @Override
    public String printEffect() {
        return "Deal " + getStrength() + " " + type.name() + " Damage to" + getType();
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
