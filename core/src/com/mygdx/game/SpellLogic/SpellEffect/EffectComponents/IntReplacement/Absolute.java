package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement;

public class Absolute implements IsIntFormat
{
    private int number;
    public Absolute(int number)
    {
        this.number =number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String print() {
        return Integer.toString(number);
    }
}
