package com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents;

import com.mygdx.game.CombatLogic.BattlerFrames.BattlerStates;


public class BattlerState extends BattleFrameComponent
{
    private BattlerStates state;
    public BattlerState()
    {
        this.state =BattlerStates.READY;
    }

    public BattlerState(BattlerStates state)
    {
        this.state = state;
    }

    public BattlerStates getState() {
        return state;
    }

    public void setState(BattlerStates state) {
        this.state = state;
    }

    @Override
    public BattleFrameComponent clone() {
        return new BattlerState(state);
    }

    @Override
    public String printComponent() {
        return "Current State:: " + getState();
    }
}
