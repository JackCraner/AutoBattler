package com.mygdx.game.SpellLogic.SpellEffect.EffectComponents.ConditionComponents;




import java.util.ArrayList;
import java.util.Iterator;

public class ConditionObject
{
    private ArrayList<IsConditionComponent> conditionComponents = new ArrayList<>();
    public ConditionObject()
    {

    }
    public <T extends IsConditionComponent> T getComponent(Class<T> componentClass)
    {
        for (IsConditionComponent ec: conditionComponents)
        {
            if ((componentClass.isAssignableFrom(ec.getClass()))){
                return componentClass.cast(ec);
            }
        }
        return null;

    }
    public void addComponent(IsConditionComponent ec)
    {
        this.conditionComponents.add(ec);
    }
    public Iterator<IsConditionComponent> getComponentIterator()
    {
        return this.conditionComponents.iterator();
    }
    public String print()
    {
        String s = "If";
        String end =" then ";
        Iterator<IsConditionComponent> i = conditionComponents.iterator();
        if (conditionComponents.size() == 1  && getComponent(Chance.class) != null)
        {
            return getComponent(Chance.class).print() + " to ";
        }
        while(i.hasNext())
        {
            IsConditionComponent nextComponent = i.next();
            if (nextComponent instanceof Chance)
            {
                end = " and " + nextComponent.print() + " to ";
            }
            else
            {
                s += i.next().print();
                if (i.hasNext())
                {
                    s += " and ";
                }
            }

        }
        return s+end;
    }
}
