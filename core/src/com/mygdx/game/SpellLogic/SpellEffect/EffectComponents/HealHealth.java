package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;

public class HealHealth extends IsEffectComponent
{
    private float strength;
    public HealHealth(float strength) {
        this.strength = strength;
    }

    public float getStrength() {
        return strength;
    }

    @Override
    public String printEffect() {
        return "Heal " +getTarget().name() + " By " + getStrength();
    }

    @Override
    public void getExecution(BattlerFrame[] battlers) {

    }

    @Override
    public IsEffectComponent clone() {
        return new HealHealth(strength);
    }
}
