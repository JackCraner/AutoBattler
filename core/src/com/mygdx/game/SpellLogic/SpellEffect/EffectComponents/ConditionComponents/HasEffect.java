package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents;


import com.mygdx.game.SpellLogic.SpellEffect.Enums.TargetType;

public class HasEffect extends IsConditionComponent
{
    private int num;
    private String requiredEffect;
    private TargetType target;
    public HasEffect(int requiredStackNumber, String requiredEffect, TargetType target)
    {
        this.num = requiredStackNumber;
        this.requiredEffect = requiredEffect;
        this.target = target;
    }

    public int getNum() {
        return num;
    }

    public TargetType getTarget() {
        return target;
    }

    public String getRequiredEffect() {
        return requiredEffect;
    }

    @Override
    public String print() {
        return  target.getName(0) + " has " +(num>1?"atleast " + num + " Stack of ":"")+ requiredEffect;
    }
}
