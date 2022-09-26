package com.mygdx.game.CombatLogic.BattlerFrames.BattleFrameComponents;

import com.mygdx.game.CombatLogic.BattlerFrames.ModifierArray;
import com.mygdx.game.SpellLogic.SpellEffect.Enums.DamageTypes;


public class BuffArray extends BattleFrameComponent{

    private ModifierArray buffArray;
    public BuffArray()
    {
        buffArray = new ModifierArray();
    }
    private BuffArray(ModifierArray defaultArray)
    {
        buffArray = defaultArray;
    }
    public int getBuffFor(DamageTypes typ)
    {
        return buffArray.getModifierFor(typ);
    }
    public void setBuffFor(DamageTypes type,int newStrength)
    {
        buffArray.setModifierFor(type,newStrength);
    }



    @Override
    public BattleFrameComponent clone() {
        return new BuffArray(buffArray.clone());
    }

    @Override
    public String printComponent() {
        String s = "BUFF::  ";
        for (DamageTypes t: DamageTypes.values())
        {
            s += t.name() + ": " + getBuffFor(t) + " | ";
        }
        return s;
    }
}
