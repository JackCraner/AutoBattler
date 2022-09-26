package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent;


import com.mygdx.game.SpellLogic.SpellEffect.Effect;
import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.TickTypes.IsTickType;

public class StatusObject
{

    private String status_name;
    private Effect statusEffect;
    private IsTickType type;
    public StatusObject(String name, IsTickType type, Effect statusEffect)
    {
        this.status_name = name;
        this.type= type;

        this.statusEffect =statusEffect;
    }


    public Effect getStatusEffect() {
        return statusEffect;
    }

    public String getStatus_name() {
        return status_name;
    }

    public IsTickType getType() {
        return type;
    }
}

/**
 * Statuses -> Burn,Poison,Freeze (Set Effect)
 * DamageBuff -> Type ; strength
 *
 *
 *
 */