package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.GainManaSystem;

public class GainLoseMana extends IsEffectComponent
{
    private int strength;
    public GainLoseMana(int strength)
    {
        this.strength= strength;
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public String printEffect() {
        return (strength>0?"Increase ":"Decrease ") + getTarget().getName(0) + " Mana by " + getStrength();
    }

    @Override
    public void getExecution(BattlerFrame[] battlers) {
        GainManaSystem.instance.execute(this, battlers);
    }

    @Override
    public IsEffectComponent clone() {
        return new GainLoseMana(strength);
    }
}
