package com.mygdx.game.CombatLogic.BattlerFrames;

import com.mygdx.game.SpellLogic.SpellEffect.Enums.DamageTypes;


import java.util.HashMap;
import java.util.Map;

public class ModifierArray
{

    private HashMap<DamageTypes,Integer> modifierValues = new HashMap<>();



    public ModifierArray()
    {
        for (DamageTypes dt: DamageTypes.values())
        {
            modifierValues.put(dt,0);
        }
    }

    public int getModifierFor(DamageTypes type)
    {
        return modifierValues.get(type);
    }
    public void setModifierFor(DamageTypes type, int strength)
    {
        modifierValues.put(type,strength);
    }


    public ModifierArray clone()
    {

        ModifierArray newArray = new ModifierArray();
        for (DamageTypes type: DamageTypes.values())
        {
            newArray.setModifierFor(type,getModifierFor(type));

        }

        return newArray;
    }
    public String print()
    {
        String s = "";

        for (Map.Entry<DamageTypes, Integer> entry: modifierValues.entrySet())
        {
            s += entry.getKey().name() + " Modifier " + entry.getValue() + "\n";
        }
        return s;
    }

}

