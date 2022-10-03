package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.ApplyStatusSystem;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement.IntFormat;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.StatusObject;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.TickTypes.DurationBased;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.TickTypes.OnSpellBased;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.TickTypes.TickBased;


public class ApplyStatus extends IsEffectComponent
{

    StatusObject statusObject;
    private IntFormat strength;

    public ApplyStatus(IntFormat strength, StatusObject statusObject)
    {
        this.strength = strength;
        this.statusObject = statusObject;
    }
    public ApplyStatus(int strength, StatusObject statusObject)
    {
        this(new IntFormat(strength), statusObject);

    }

    public int getStrength(BattlerFrame[] battlers) {
        return strength.execute(battlers);
    }

    public StatusObject getStatusObject() {
        return statusObject;
    }

    @Override
    public String printEffect() {
        if (statusObject.getType() instanceof TickBased)
        {
            return "Apply " + strength.print() + " Stacks of " + statusObject.getStatus_name() + " to " + getTarget().getName(1);
        }
        else if (statusObject.getType() instanceof DurationBased)
        {
            return "For the next " + strength.print() + " turns " + statusObject.getStatusEffect().printEffect();
        }
        else if (statusObject.getType() instanceof OnSpellBased)
        {
            return "The next " + strength.print() + " spells have " + statusObject.getStatusEffect().printEffect();
        }
        return "";
    }

    @Override
    public void getExecution(BattlerFrame[] battlers, BattlerFrame[] newBattlers)
    {
        ApplyStatusSystem.instance.execute(this, battlers,newBattlers );
    }

    @Override
    public IsEffectComponent clone() {
        return new ApplyStatus(strength,statusObject);
    }
}
