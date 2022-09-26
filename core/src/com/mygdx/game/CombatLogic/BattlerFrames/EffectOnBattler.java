package com.mygdx.game.CombatLogic.BattlerFrames;


import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.StatusObject;

public class EffectOnBattler
{
    StatusObject statusObject;
    int stackNumber;
    int cd;
    public EffectOnBattler(StatusObject statusObject, int stackNumber)
    {
        this(statusObject,stackNumber,1);

    }
    private EffectOnBattler(StatusObject statusObject, int stackNumber, int cd)
    {
        this.statusObject = statusObject;
        this.stackNumber = stackNumber;
        this.cd = cd;
    }

    public int getStackNumber() {
        return stackNumber;
    }

    public int getCd() {
        return cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    public void setStackNumber(int stackNumber) {
        this.stackNumber = stackNumber;
    }

    public StatusObject getStatusObject() {
        return statusObject;
    }
    public EffectOnBattler clone()
    {

        return  new EffectOnBattler(statusObject,stackNumber,cd);
    }
    public String print()
    {
        return "Effect: " + statusObject.getStatus_name()+ " Stack: " + stackNumber + " CD:" + cd;
    }
}
