package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents;

import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IntReplacement.IntFormat;

public class Inequality extends IsConditionComponent
{
    private IntFormat para1;
    private IntFormat para2;
    public Inequality(IntFormat num1, IntFormat num2)
    {
        this.para1 = num1;
        this.para2 = num2;
    }

    public IntFormat getPara1() {
        return para1;
    }

    public IntFormat getPara2() {
        return para2;
    }

    @Override
    public String print() {
        return para1.print() + " Greater than " + para2.print();
    }
}
