package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents;


import com.mygdx.game.CombatLogic.BattlerFrames.BattlerFrame;
import com.mygdx.game.CombatLogic.EffectSystems.ApplyStatusSystem;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.StatusObject;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.TickTypes.DurationBased;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.TickTypes.OnSpellBased;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.TickTypes.TickBased;


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
        if (statusObject.getType() instanceof TickBased)
        {
            return "Apply " + strength + " Stacks of " + statusObject.getStatus_name() + " to " + getTarget().getName(1);
        }
        else if (statusObject.getType() instanceof DurationBased)
        {
            return "For the next " + strength + " turns " + statusObject.getStatusEffect().printEffect();
        }
        else if (statusObject.getType() instanceof OnSpellBased)
        {
            return "The next " + (strength>1?strength + " spells have ": " spell has ") + statusObject.getStatusEffect().printEffect();
        }
        return "";
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
