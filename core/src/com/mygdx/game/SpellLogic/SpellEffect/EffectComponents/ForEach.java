package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.ForEachSystem;
import com.mygdx.game.SpellLogic.SpellEffect.Effect;

public class ForEach extends IsEffectComponent
{

    private String determinant;
    private int ratio;
    private Effect effect;
    public ForEach(int ratio, String determinant_name, Effect effect)
    {
        this.ratio = ratio;
        this.determinant = determinant_name;
        this.effect = effect;
    }

    @Override
    public String printEffect() {
        return "For every " + ratio+ " Stacks of " + determinant + " do " + effect.printEffect();
    }

    public int getRatio() {
        return ratio;
    }

    public String getDeterminant()
    {

        return determinant;
    }

    public Effect getEffect() {
        return effect;
    }

    @Override
    public void getExecution(BattlerFrame[] battlers) {
        ForEachSystem.instance.execute(this, battlers);
    }

    @Override
    public IsEffectComponent clone() {
        return new ForEach(ratio,determinant,effect);
    }
}
