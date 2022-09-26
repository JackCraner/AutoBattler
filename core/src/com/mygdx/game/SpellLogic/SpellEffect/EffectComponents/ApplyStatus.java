package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.ApplyStatusSystem;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.StatusObject;



public class ApplyStatus extends IsEffectComponent
{

    StatusObject statusObject;
    private int strength;
    public ApplyStatus(int strength, StatusObject statusObject)
    {
        this.strength = strength;
        this.statusObject = statusObject;
    }

    public int getStrength() {
        return strength;
    }

    public StatusObject getStatusObject() {
        return statusObject;
    }

    @Override
    public String printEffect() {
        return "Apply " + getStrength() + " Stacks of " + statusObject.getStatus_name();
    }

    @Override
    public void getExecution(BattlerFrame[] battlers)
    {
        ApplyStatusSystem.instance.execute(this, battlers);
    }

    @Override
    public IsEffectComponent clone() {
        return new ApplyStatus(strength,statusObject);
    }
}
