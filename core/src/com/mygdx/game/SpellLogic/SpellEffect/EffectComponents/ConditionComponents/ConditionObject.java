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
        String s = "";
        for (IsConditionComponent cc:conditionComponents)
        {
            s += cc.print() + "\n";
        }
        return s;
    }
}
