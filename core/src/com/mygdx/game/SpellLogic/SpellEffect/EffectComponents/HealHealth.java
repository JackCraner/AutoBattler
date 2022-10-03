package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.HealSystem;

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
    public String printEffect()
    {
        if (strength==-1)
        {
            return "Set " + getTarget().getName(0) + " Health to Full";
        }
        return "Increase " + getTarget().getName(0) + " Health by " + getStrength();
    }

    @Override
    public void getExecution(BattlerFrame[] battlers) {
        HealSystem.instance.execute(this,battlers);
    }

    @Override
    public IsEffectComponent clone() {
        return new HealHealth(strength);
    }
}
