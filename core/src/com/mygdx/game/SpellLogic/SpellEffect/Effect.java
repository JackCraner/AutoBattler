package com.mygdx.game.SpellLogic.SpellEffect;

import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IsEffectComponent;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.EffectType;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.TargetType;


import java.util.ArrayList;
import java.util.Iterator;

public class Effect
{

    private ArrayList<IsEffectComponent> effectComponents = new ArrayList<>();
    private TargetType target;
    private EffectType type;
    public Effect(TargetType targetType)
    {
        this(targetType,EffectType.POSITIVE);
    }
    public Effect(TargetType target, EffectType type)
    {
        this.target = target;
        this.type = type;
    }

    public EffectType getType() {
        return type;
    }

    public <T extends IsEffectComponent> T getComponent(Class<T> componentClass)
    {
        for (IsEffectComponent ec: effectComponents)
        {
            if ((componentClass.isAssignableFrom(ec.getClass()))){
                return componentClass.cast(ec);
            }
        }
        return null;

    }
    public void addComponent(IsEffectComponent ec)
    {
        ec.setParent(this);
        this.effectComponents.add(ec);
    }

    public String printEffect()
    {
        String s = "";
        for (int i = 0;i<effectComponents.size();i++)
        {
            s += effectComponents.get(i).printEffect();
            if (i != effectComponents.size() - 1)
            {
                s += " and ";
            }
        }
        return s;
    }

    public Iterator<IsEffectComponent> getComponentIterator()
    {
        return this.effectComponents.iterator();
    }

    public TargetType getTarget() {
        return target;
    }
    public Effect clone()
    {
        Effect newClone = new Effect(target);
        for (IsEffectComponent iec:effectComponents)
        {
            newClone.addComponent(iec.clone());
        }
        return newClone;
    }
}
