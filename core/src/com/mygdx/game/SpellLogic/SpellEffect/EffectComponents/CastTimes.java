package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.CastNumberSystem;

public class CastTimes extends IsEffectComponent
{
    private int numOfCasts;
    public CastTimes(int numOfCasts)
    {
        this.numOfCasts = numOfCasts;
    }

    @Override
    public String printEffect() {
        return "Spell can be cast " + numOfCasts + " times";
    }

    public int getNumOfCasts() {
        return numOfCasts;
    }

    @Override
    public void getExecution(BattlerFrame[] battlers)
    {
        CastNumberSystem.instance.execute(this, battlers);
    }

    @Override
    public IsEffectComponent clone() {
        return new CastTimes(numOfCasts);
    }
}
