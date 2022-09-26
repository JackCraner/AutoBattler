package com.mygdx.game.SpellLogic.SpellEffect;

import com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.IsEffectComponent;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.TargetType;


import java.util.ArrayList;
import java.util.Iterator;

public class Effect
{

    private ArrayList<IsEffectComponent> effectComponents = new ArrayList<>();
    private TargetType target;

    public Effect(TargetType targetType)
    {
        this.target = targetType;
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
        for (IsEffectComponent e: effectComponents)
        {
            s += e.printEffect() + " : ";
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
