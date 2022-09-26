package com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents;

public class ManaComponent extends BattleFrameComponent
{

    private int currentMana;
    private int maxMana;
    public ManaComponent(int maxMana,int currentMana)
    {
        this.maxMana = maxMana;
        this.currentMana = currentMana;
    }
    public int getCurrentMana() {
        return currentMana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    @Override
    public BattleFrameComponent clone() {
        return new ManaComponent(maxMana,currentMana);
    }

    @Override
    public String printComponent() {
        return "Current Mana: " + getCurrentMana() + "     Max Mana: " + getMaxMana();
    }
}
