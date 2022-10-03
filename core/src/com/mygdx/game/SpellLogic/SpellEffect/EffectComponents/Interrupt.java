package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;

import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.InterruptSystem;

public class Interrupt extends IsEffectComponent{

    public Interrupt()
    {

    }



    @Override
    public String printEffect() {
        return "Interrupt " + getTarget().getName(1) + " Current Cast";
    }

    @Override
    public void getExecution(BattlerFrame[] battlers) {
        InterruptSystem.instance.execute(this,battlers);
    }

    @Override
    public IsEffectComponent clone() {
        return new Interrupt();
    }
}
