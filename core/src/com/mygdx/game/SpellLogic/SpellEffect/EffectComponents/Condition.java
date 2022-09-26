package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.ConditionSystem;
import com.mygdx.game.SpellLogic.SpellEffect.Effect;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents.ConditionObject;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.TargetType;

public class Condition extends IsEffectComponent
{

    private ConditionObject type;
    private Effect onFail;
    private Effect onSuccess;
    public Condition(ConditionObject type, Effect onSuccess)
    {
        this(type,onSuccess,new Effect(TargetType.SELF));
    }
    public Condition(ConditionObject type,Effect onSuccess, Effect onFail)
    {

        this.type = type;
        this.onSuccess = onSuccess;
        this.onFail = onFail;
    }

    public ConditionObject getType() {
        return type;
    }

    public Effect getOnFail()
    {
        return onFail;
    }

    public Effect getOnSuccess() {
        return onSuccess;
    }

    public boolean hasOnFail()
    {
        return onFail!=null;
    }
    public Effect getEffect(boolean result)
    {
        return result?getOnSuccess():getOnFail();
    }
    @Override
    public String printEffect() {
        return "If " + type.print() + "Then " + onSuccess.printEffect() +   " Else do " + (hasOnFail()?onFail.printEffect():"");
    }

    @Override
    public void getExecution(BattlerFrame[] battlers)
    {
        ConditionSystem.instance.execute(this, battlers);
    }

    @Override
    public IsEffectComponent clone() {
        return new Condition(type,onSuccess,onFail);
    }
}
