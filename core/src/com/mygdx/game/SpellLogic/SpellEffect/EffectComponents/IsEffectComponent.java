package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.SpellLogic.SpellEffect.Effect;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.TargetType;

public abstract class IsEffectComponent
{
    Effect parent;
    public IsEffectComponent()
    {

    }

    public void setParent(Effect parent)
    {
        this.parent = parent;
    }

    public Effect getParent()
    {
        return parent;
    }
    public TargetType getTarget()
    {
        return parent.getTarget();
    }

    public abstract String printEffect();


    public abstract void getExecution(BattlerFrame[] battlers, BattlerFrame[] newBattlers);
    public abstract IsEffectComponent clone();
}
