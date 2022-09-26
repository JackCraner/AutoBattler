package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents;

public class Chance extends IsConditionComponent
{
    private int percentage;
    public Chance(int percentage)
    {
        this.percentage = percentage;
    }

    public int getPercentage() {
        return percentage;
    }

    @Override
    public String print() {
        return percentage + "% Chance";
    }
}
