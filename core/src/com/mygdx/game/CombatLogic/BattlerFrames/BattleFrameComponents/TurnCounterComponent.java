package com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents;

public class TurnCounterComponent extends BattleFrameComponent{

    private int turnCounter;
    public TurnCounterComponent(int turnCounter)
    {
        this.turnCounter = turnCounter;
    }

    public void setTurnCounter(int turnCounter) {
        this.turnCounter = turnCounter;
    }

    public int getTurnCounter() {
        return turnCounter;
    }

    @Override
    public BattleFrameComponent clone() {
        return new TurnCounterComponent(turnCounter);
    }

    @Override
    public String printComponent() {
        return "Current Turn: " + getTurnCounter();
    }
}
