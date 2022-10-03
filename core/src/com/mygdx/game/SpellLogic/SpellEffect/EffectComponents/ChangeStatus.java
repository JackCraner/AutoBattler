package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.BattlerFrames.BattlerStates;
import com.mygdx.game.CombatLogic.EffectSystems.ChangeStatusSystem;

/**
 * Should only be used in a StatusObject effect with a ApplyStatus otherwise the stun will last forever
 */
public class ChangeStatus extends IsEffectComponent{


    private BattlerStates newState;
    public ChangeStatus(BattlerStates newState)
    {
        this.newState = newState;
    }

    public BattlerStates getNewState() {
        return newState;
    }

    @Override
    public String printEffect() {
        return newState.name() + getTarget().getName(0) ;
    }

    @Override
    public void getExecution(BattlerFrame[] battlers, BattlerFrame[] newBattlers)
    {
        ChangeStatusSystem.instance.execute(this, battlers,newBattlers );
    }

    @Override
    public IsEffectComponent clone() {
        return new ChangeStatus(newState);
    }
}
