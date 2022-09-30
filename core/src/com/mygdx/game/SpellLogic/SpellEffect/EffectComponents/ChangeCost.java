package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.ChangeCostSystem;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.ModifierType;

//APPLY ONSPELL
public class ChangeCost extends IsEffectComponent
{

    private int newCost;
    private ModifierType type;
    public ChangeCost(int newCost, ModifierType type)
    {
        this.newCost = newCost;
        this.type = type;
    }

    public int getNewCost() {
        return newCost;
    }

    public ModifierType getType() {
        return type;
    }

    @Override
    public String printEffect() {
        return (type==ModifierType.ABSOLUTE?"Change cost of spells to " + newCost: (newCost>0?"Increase ":"Decrease ") + " cost of spells by" + newCost);
    }

    @Override
    public void getExecution(BattlerFrame[] battlers) {
        ChangeCostSystem.instance.execute(this, battlers);
    }

    @Override
    public IsEffectComponent clone() {
        return new ChangeCost(newCost,type);
    }
}
