package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;

import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.RepeatSystem;
import com.mygdx.game.SpellLogic.SpellEffect.Effect;

//APPLY ONSPELL
public class RepeatN extends IsEffectComponent
{

    private int numberOfRepeats;
    private Effect effect;
    public RepeatN(int num, Effect effect)
    {
        this.numberOfRepeats = num;
        this.effect = effect;
    }
    public RepeatN(int num)
    {
        this(num,null);
    }

    public Effect getEffect() {
        return effect;
    }

    public int getNumberOfRepeats() {
        return numberOfRepeats;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    @Override
    public String printEffect() {
        return null;
    }

    @Override
    public void getExecution(BattlerFrame[] battlers) {
        RepeatSystem.instance.execute(this, battlers);
    }

    @Override
    public IsEffectComponent clone() {
        return new RepeatN(numberOfRepeats,effect);
    }
}
