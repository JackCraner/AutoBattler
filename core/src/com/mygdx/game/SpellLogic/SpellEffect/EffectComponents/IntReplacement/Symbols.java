package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement;

public enum Symbols
{
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("X"),
    DIVIDE("/"),


    ;
    String symbol;
    Symbols(String symbol)
    {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
