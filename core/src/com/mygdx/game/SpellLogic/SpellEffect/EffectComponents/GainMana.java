package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.GainManaSystem;

public class GainMana extends IsEffectComponent
{
    private int strength;
    public GainMana(int strength)
    {
        this.strength= strength;
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public String printEffect() {
        return null;
    }

    @Override
    public void getExecution(BattlerFrame[] battlers) {
        GainManaSystem.instance.execute(this, battlers);
    }

    @Override
    public IsEffectComponent clone() {
        return new GainMana(strength);
    }
}
