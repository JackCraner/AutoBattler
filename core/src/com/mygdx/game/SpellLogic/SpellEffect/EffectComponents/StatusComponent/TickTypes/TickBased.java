package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.StatusComponent.TickTypes;

public class TickBased implements IsTickType
{
    private int speed;
    private boolean doesDecrease;
    public TickBased(int speed, boolean doesDecrease)
    {
        this.speed = speed;
        this.doesDecrease =doesDecrease;
    }

    public int getSpeed() {
        return speed;
    }
    public boolean getDoesDecrease()
    {
        return doesDecrease;
    }
}
