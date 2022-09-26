package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.TickTypes;


import com.mygdx.game.SpellLogic.SpellEffect.Effect;

public class DurationBased implements IsTickType
{
    Effect whenFinished;
    public DurationBased(Effect whenFinished)
    {
        this.whenFinished = whenFinished;
    }

    public Effect getWhenFinished() {
        return whenFinished;
    }
}
