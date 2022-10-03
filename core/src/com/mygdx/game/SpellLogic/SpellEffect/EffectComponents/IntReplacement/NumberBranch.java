package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement;

public class NumberBranch
{
    private Symbols symbol;
    private IsIntFormat number;
    public NumberBranch(Symbols symbol, IsIntFormat number)
    {
        this.symbol = symbol;
        this.number = number;
    }
    public NumberBranch(Symbols symbol, int number)
    {
       this(symbol,new Absolute(number));
    }

    public IsIntFormat getNumber() {
        return number;
    }

    public Symbols getSymbol() {
        return symbol;
    }
    public String print()
    {
        return symbol.getSymbol() + " " + number.print();
    }
}
