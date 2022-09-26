package com.mygdx.game.CombatLogic.EffectSystems;


import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.CastComponent;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ChangeCost;

public class ChangeCostSystem implements IsEffectSystem<ChangeCost> {
    public static ChangeCostSystem instance = new ChangeCostSystem();

    private ChangeCostSystem()
    {

    }


    @Override
    public void execute(ChangeCost effect, BattlerFrame[] battlers)
    {

        battlers[effect.getTarget().getValue()].getComponent(CastComponent.class).getSpell().setManaCost(effect.getNewCost());
    }
}
