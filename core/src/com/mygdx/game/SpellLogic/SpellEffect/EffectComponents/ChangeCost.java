package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.ChangeCostSystem;

//APPLY ONSPELL
public class ChangeCost extends IsEffectComponent
{

    private int newCost;
    public ChangeCost(int newCost)
    {
        this.newCost = newCost;
    }

    public int getNewCost() {
        return newCost;
    }

    @Override
    public String printEffect() {
        return null;
    }

    @Override
    public void getExecution(BattlerFrame[] battlers) {
        ChangeCostSystem.instance.execute(this, battlers);
    }

    @Override
    public IsEffectComponent clone() {
        return new ChangeCost(newCost);
    }
}
