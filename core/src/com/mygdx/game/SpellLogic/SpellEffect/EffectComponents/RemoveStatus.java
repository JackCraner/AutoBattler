package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;

import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.RemoveStatusSystem;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.TargetType;

public class RemoveStatus extends IsEffectComponent
{

    private int strength; //-1 = all stacks
    private String statusName; //"Random" will do a random one
    public RemoveStatus(int strength, String statusName)
    {
        this.statusName = statusName;
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public String getStatusName() {
        return statusName;
    }

    @Override
    public String printEffect() {
        return "Cleanse " + (strength==-1?"All":strength) + " Stacks of " + (statusName=="Random"?"a Random Status":statusName) + (getTarget()== TargetType.OTHER?" Off " + getTarget().getName(1):"");
    }

    @Override
    public void getExecution(BattlerFrame[] battlers)
    {
        RemoveStatusSystem.instance.execute(this, battlers);
    }

    @Override
    public IsEffectComponent clone() {
        return new RemoveStatus(strength,statusName);
    }
}
