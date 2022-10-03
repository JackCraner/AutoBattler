package com.mygdx.game.CombatLogic.EffectSystems;


import com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents.BattlerState;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ChangeStatus;

public class ChangeStatusSystem implements IsEffectSystem<ChangeStatus>{
    public static ChangeStatusSystem instance = new ChangeStatusSystem();

    @Override
    public void execute(ChangeStatus effect, BattlerFrame[] battlers, BattlerFrame[] newBattlers)
    {

        BattlerState battlerComponent = newBattlers[effect.getTarget().getValue()].getComponent(BattlerState.class);
        battlerComponent.setState(effect.getNewState());
    }
}
